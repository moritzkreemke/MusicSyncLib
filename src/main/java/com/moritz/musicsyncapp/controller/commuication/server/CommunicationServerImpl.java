package com.moritz.musicsyncapp.controller.commuication.server;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.CommuicationFactory;
import com.moritz.musicsyncapp.model.commuication.IMessage;
import com.moritz.musicsyncapp.model.commuication.ISendableMessage;
import com.moritz.musicsyncapp.model.commuication.events.EventMessage;
import com.moritz.musicsyncapp.model.commuication.messages.AvailableClientsChanged;
import com.moritz.musicsyncapp.model.commuication.messages.PlaylistChangedMessage;
import com.moritz.musicsyncapp.model.commuication.messages.RequestPlaylistMessage;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.playlist.SessionClientPlaylistImpl;
import com.moritz.musicsyncapp.model.playlist.SessionServerPlaylistImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommunicationServerImpl implements ICommunicationServer {

    private static AtomicBoolean started = new AtomicBoolean(false);

    private static ServerSocket serverSocket;
    private static final ServerClientsList clientsList = new ServerClientsList();
    private static IPlaylist playlist = new SessionServerPlaylistImpl();


    @Override
    public synchronized void start(int port) {
        if (started.get())
            return;


        try {
            serverSocket = new ServerSocket(port);
            started.set(true);

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (started.get()) {

                            Socket client = serverSocket.accept();
                            ServerClient serverClient = new ServerClient(client.getInputStream(), client.getOutputStream(), CommunicationServerImpl.this);
                            clientsList.add(serverClient);
                            serverClient.run();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {

        }
    }

    @Override
    public void sendMessage(ISendableMessage sendableMessage) {
        if(sendableMessage.getReciver().getID().equals(IClient.EVENT.getID())) {
            evaulateEvents(sendableMessage);
            return;
        } else if(sendableMessage.getReciver().getID().equals(IClient.SERVER.getID())) {
            evaluateServerMessages(sendableMessage);
            return;
        }
        IClient[] recipiends = clientsList.getClients(sendableMessage.getReciver().getID());
        for (int i = 0; i < recipiends.length; i++) {
            ServerClient serverClient = (ServerClient) recipiends[i];
            serverClient.sendMessage(sendableMessage);
        }
    }

    private void evaulateEvents (ISendableMessage message) {
        if(message.getMessage() instanceof EventMessage) {
            EventMessage eventMessage = (EventMessage) message.getMessage();
            switch (eventMessage.getName()) {
                case EventMessage.EVENT_CLIENTS_UPDATED_TYPE:
                    event_clients_update();
                    break;
            }
        }
    }
    private void evaluateServerMessages (ISendableMessage message)
    {
        if(message.getMessage() instanceof RequestPlaylistMessage) {
            IClient[] clients = clientsList.getClients(message.getSender().getID());
            for (int i = 0; i < clients.length; i++) {
                ServerClient serverClient = (ServerClient) clients[i];
                PlaylistChangedMessage messageAnswer = new PlaylistChangedMessage();
                messageAnswer.setPlaylist(playlist);
                ISendableMessage answer = CommuicationFactory.createMessage(messageAnswer, IClient.SERVER, message.getSender());
                serverClient.sendMessage(answer);
            }
        } else if (message.getMessage() instanceof PlaylistChangedMessage) {
            PlaylistChangedMessage playlistChangedMessage = (PlaylistChangedMessage) message.getMessage();
            IPlaylist clientPlaylist = playlistChangedMessage.getPlaylist();
            playlist = new SessionServerPlaylistImpl();
            playlist.setTracks(clientPlaylist.getTracks(), false);

            IClient[] clients = clientsList.getClients(IClient.ANY.getID());
            for (int i = 0; i < clients.length; i++) {
                ServerClient serverClient = (ServerClient) clients[i];
                PlaylistChangedMessage messageAnswer = new PlaylistChangedMessage();
                messageAnswer.setPlaylist(playlist);
                ISendableMessage answer = CommuicationFactory.createMessage(messageAnswer, IClient.SERVER, message.getSender());
                serverClient.sendMessage(answer);
            }
        }
    }

    private void event_clients_update () {
        IClient[] recipient = clientsList.getClients(IClient.ANY.getID());
        IMessage message = new AvailableClientsChanged(clientsList);
        for (int i = 0; i < recipient.length; i++) {
            ServerClient serverClient = (ServerClient) recipient[i];
            serverClient.sendMessage(CommuicationFactory.createMessage(message, IClient.EVENT, recipient[i]));
        }
    }


    @Override
    public void close() throws IOException {
        started.set(false);
        serverSocket.close();
    }
}
