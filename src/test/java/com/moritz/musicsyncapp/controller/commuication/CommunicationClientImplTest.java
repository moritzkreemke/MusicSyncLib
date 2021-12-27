package com.moritz.musicsyncapp.controller.commuication;

import com.moritz.musicsyncapp.Log;
import com.moritz.musicsyncapp.controller.commuication.client.CommunicationClientImpl;
import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.controller.commuication.events.OnConnectEvent;
import org.junit.jupiter.api.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class CommunicationClientImplTest {

    public static final String TAG = CommunicationClientImplTest.class.toString();
    public static final int PORT = 8080;

    private static ServerSocket serverSocket;
    private static AtomicBoolean test_running = new AtomicBoolean(false);

    private interface ClientMessageReceived {
        void newMessgae (String message);
    }

    private static ClientMessageReceived clientMessageReceivedEvent;

    @BeforeAll
    static void setUpAll() {
        try {
            serverSocket = new ServerSocket(PORT);
            test_running.set(true);
            Random rnd = new Random();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    while (test_running.get()) {
                        try {
                            Socket client = serverSocket.accept();
                            int id = Math.abs(rnd.nextInt());
                            Log.d(TAG, "Client connected with ID " + id);
                            Executors.newSingleThreadExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        DataInputStream inStream = new DataInputStream(client.getInputStream());
                                        while (test_running.get()) {
                                            if(clientMessageReceivedEvent != null) {
                                                clientMessageReceivedEvent.newMessgae(inStream.readUTF());
                                            }
                                            Log.d(TAG, "Client (" + id + "): " +  inStream.readUTF());
                                        }
                                    } catch (IOException e) {
                                        Log.e(TAG, "Error Client (" + id + ")", e);
                                    }
                                }
                            });
                        } catch (IOException e) {
                            fail(e);
                        }
                    }
                }
            });
        } catch (IOException e) {
            fail(e);
        }

    }

    @AfterAll
    static void tearDownAll() {
        test_running.set(false);
    }

    @BeforeEach
    void setUp () {
        clientMessageReceivedEvent = null;
    }

    @Test
    void connect_toInvalidAdress() {

        //Tescase 1: connect to invalid address
        ICommunicationClient communicationClient = new CommunicationClientImpl();
        final OnConnectEvent callback = mock(OnConnectEvent.class);

        communicationClient.connect(new InetSocketAddress("129.8.7.2", 9999).getAddress(), PORT, 1, "1", callback);
        verify(callback, only()).onFailure(1);

    }

    @Test
    void connect_toCorrectAddress ()
    {
        //Tescase 1: connect to invalid address
        ICommunicationClient communicationClient = new CommunicationClientImpl();
        final OnConnectEvent callback = mock(OnConnectEvent.class);

        communicationClient.connect(new InetSocketAddress("127.0.0.1", 9999).getAddress(), PORT, 1, "1", callback);
        verify(callback, only()).success();
    }

    @Test
    void addOnReviveMessageListener() {
    }

    @Test
    void close() {

    }
}