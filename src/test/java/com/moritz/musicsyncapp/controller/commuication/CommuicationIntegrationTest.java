package com.moritz.musicsyncapp.controller.commuication;

import com.moritz.musicsyncapp.MusicSyncFactory;
import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.controller.commuication.events.OnReciveMessageEvent;
import com.moritz.musicsyncapp.controller.commuication.server.ICommunicationServer;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.IMessage;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.mockito.Mockito.*;

public class CommuicationIntegrationTest {


    @Test
    void createServerAndConnect ( ) throws UnknownHostException {
        ICommunicationServer server = null;
        ICommunicationClient client = null;

        int port = 8080;

        server.start(8080);

        client.connect(InetAddress.getByName("127.0.0.1"), 8080, 1, "1", null);


        OnReciveMessageEvent reciveMessageEvent = mock(OnReciveMessageEvent.class);
        client.addOnReviveMessageListener(reciveMessageEvent);

        IMessage testMessage = new TestMessage();
        client.sendMessage(testMessage, IClient.ANY);

        verify(reciveMessageEvent, only()).onReceiveMessage(null);


    }

}
