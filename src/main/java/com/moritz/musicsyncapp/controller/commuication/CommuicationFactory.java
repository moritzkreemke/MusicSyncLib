package com.moritz.musicsyncapp.controller.commuication;

import com.moritz.musicsyncapp.controller.commuication.client.CommunicationClientImpl;
import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.controller.commuication.server.CommunicationServerImpl;
import com.moritz.musicsyncapp.controller.commuication.server.ICommunicationServer;

public class CommuicationFactory {

    private static CommuicationFactory instance;

    public static CommuicationFactory get() {
        if(instance == null)
            instance = new CommuicationFactory();

        return instance;
    }

    private ICommunicationServer communicationServer;
    public ICommunicationServer getServer () {
        if(communicationServer == null)
            communicationServer = new CommunicationServerImpl();
        return communicationServer;
    }

    private ICommunicationClient communicationClient;
    public ICommunicationClient getClient () {
        if(communicationClient == null)
            communicationClient = new CommunicationClientImpl();
        return communicationClient;
    }

}
