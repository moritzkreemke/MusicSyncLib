package com.moritz.musicsyncapp.controller.commuication.server;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.CommuicationFactory;
import com.moritz.musicsyncapp.model.commuication.IMessage;
import com.moritz.musicsyncapp.model.commuication.ISendableMessage;
import com.moritz.musicsyncapp.model.commuication.events.EventMessage;
import com.moritz.musicsyncapp.model.commuication.messages.AvailableClientsChanged;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommunicationServerImpl implements ICommunicationServer {

    private static AtomicBoolean started = new AtomicBoolean(false);

    private static ServerSocket serverSocket;
    private static final ServerClientsList clientsList = new ServerClientsList();


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
