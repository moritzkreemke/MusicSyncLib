package com.moritz.musicsyncapp.controller.commuication.server;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.CommuicationFactory;
import com.moritz.musicsyncapp.model.commuication.ISendableMessage;

import java.io.*;
import java.util.concurrent.Executors;

class ServerClient implements IClient {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private ICommunicationServer communicationServer;

    private String id;


    public ServerClient(InputStream inStream, OutputStream outStream, ICommunicationServer communicationServer) {
        this.inputStream = new DataInputStream(inStream);
        this.outputStream = new DataOutputStream(outStream);
        this.communicationServer = communicationServer;


    }

    public void run () {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        String strMsg = inputStream.readUTF();
                        ISendableMessage sendableMessage =  CommuicationFactory.createMessage(strMsg);
                        id = sendableMessage.getSender().getID();
                        communicationServer.sendMessage(sendableMessage);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void sendMessage (ISendableMessage message) {
        try {
            outputStream.writeUTF(message.toJSON());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getID() {
        return id;
    }
}
