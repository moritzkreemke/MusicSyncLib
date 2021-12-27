package com.moritz.musicsyncapp.controller.commuication.client;

import com.moritz.musicsyncapp.controller.commuication.events.OnConnectEvent;
import com.moritz.musicsyncapp.controller.commuication.events.OnReciveMessageEvent;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.IMessage;

import java.io.Closeable;
import java.net.InetAddress;

public interface ICommunicationClient extends Closeable {


    void connect(InetAddress inetAddress, int port, int retires,String id, OnConnectEvent event);

    void sendMessage (IMessage message, IClient receiver);

    void addOnReviveMessageListener(OnReciveMessageEvent onReciveMessageEvent);


}
