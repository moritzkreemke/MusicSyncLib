package com.moritz.musicsyncapp.controller.commuication.client;

import com.moritz.musicsyncapp.Log;
import com.moritz.musicsyncapp.controller.commuication.events.OnConnectEvent;
import com.moritz.musicsyncapp.controller.commuication.events.OnReciveMessageEvent;
import com.moritz.musicsyncapp.model.client.ClientImpl;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.CommuicationFactory;
import com.moritz.musicsyncapp.model.commuication.IMessage;
import com.moritz.musicsyncapp.model.commuication.ISendableMessage;
import com.moritz.musicsyncapp.model.commuication.events.EventMessage;
import com.moritz.musicsyncapp.model.commuication.messages.AvailableClientsChanged;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommunicationClientImpl implements ICommunicationClient {
    public static final String TAG = CommunicationClientImpl.class.toString();
    private static final int TIMEOUT = 500;

    public static final String CONNECTED_CHANGED_ACTION = "connected";
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private static AtomicBoolean connected = new AtomicBoolean(false);
    private Socket socket;
    private IClient client;
    private List<OnReciveMessageEvent> onReciveMessageEventList = new ArrayList<>();

    private DataInputStream inputStream;
    private DataOutputStream outputStream;


    @Override
    public synchronized void connect(InetAddress inetAddress, int port, int retires, String id, OnConnectEvent event) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                connectInternal(inetAddress, port, retires, id, event);
            }
        });
    }


    public void connectInternal(InetAddress inetAddress, int port, int retires, String id, OnConnectEvent event) {

        try {
            synchronized (this) {
                if(connected.get()) {
                    Log.d(TAG, "already connected");
                    event.onFailure(2);
                    return;
                }
            }
            socket = new Socket();
            socket.bind(null);

            Log.d(TAG, "try connecting to: " + inetAddress + " with port " + port);
            socket.connect(new InetSocketAddress(inetAddress, port), TIMEOUT);
            connected.set(true);
            client = new ClientImpl(id);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            pcs.firePropertyChange(CONNECTED_CHANGED_ACTION, false, true);
            if(event != null)
                event.success();

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (connected.get()) {
                        try {
                            String reciveMsg = inputStream.readUTF();
                            ISendableMessage sendableMessage = CommuicationFactory.createMessage(reciveMsg);

                            for (OnReciveMessageEvent onReciveMessageEvent : onReciveMessageEventList) {
                                try {
                                    onReciveMessageEvent.onReceiveMessage(sendableMessage);
                                } catch (Throwable ignore) {};
                            }
                        } catch (IOException e) {
                            if(socket.isClosed()) {
                                connected.set(false);
                                Log.d(TAG, "run: disconnected");
                            }
                            e.printStackTrace();
                        }


                    }
                }
            });

        } catch (IOException e) {
            Log.d(TAG, "error, connecting");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            retires--;
            if(retires > 0) {
                connect(inetAddress, port, retires, id, event);
            } else {
                if(event != null)
                    event.onFailure(1);
            }
        }

    }

    @Override
    public void sendMessage(IMessage message, IClient receiver) {
        if(inputStream == null) {
            Log.d(TAG, "input stream is null");
            return;
        }

        String jsonMessage = CommuicationFactory.createMessage(message, client, receiver).toJSON();
        try {
            outputStream.writeUTF(jsonMessage);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void addOnReviveMessageListener(OnReciveMessageEvent onReciveMessageEvent) {
        onReciveMessageEventList.add(onReciveMessageEvent);
    }

    @Override
    public void close() throws IOException {
        if(socket != null) {
            connected.set(false);
            socket.close();
        }
    }

    @Override
    public void addOnConnectedChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(CONNECTED_CHANGED_ACTION, listener);
    }
}
