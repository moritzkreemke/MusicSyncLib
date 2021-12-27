package com.moritz.musicsyncapp.controller.commuication.server;

import com.moritz.musicsyncapp.controller.commuication.events.OnReciveMessageEvent;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.IMessage;
import com.moritz.musicsyncapp.model.commuication.ISendableMessage;

import java.io.Closeable;

public interface ICommunicationServer extends Closeable {

    void start (int port);

    void sendMessage (ISendableMessage sendableMessage);


}
