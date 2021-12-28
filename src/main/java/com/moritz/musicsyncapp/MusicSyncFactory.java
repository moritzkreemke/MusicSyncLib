package com.moritz.musicsyncapp;

import com.moritz.musicsyncapp.controller.commuication.client.CommunicationClientImpl;
import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.controller.commuication.server.CommunicationServerImpl;
import com.moritz.musicsyncapp.controller.commuication.server.ICommunicationServer;
import com.moritz.musicsyncapp.controller.p2pnetwork.IP2PNetworkController;
import com.moritz.musicsyncapp.controller.playlist.IPlaylistController;
import com.moritz.musicsyncapp.controller.playlist.PlaylistControllerImpl;
import com.moritz.musicsyncapp.controller.session.ISessionController;
import com.moritz.musicsyncapp.controller.session.SessionControllerImpl;
import com.moritz.musicsyncapp.controller.snapdroid.ISnapdroidClient;
import com.moritz.musicsyncapp.controller.snapdroid.ISnapdroidServer;
import com.moritz.musicsyncapp.controller.sound.ISoundController;


public class MusicSyncFactory implements ISyncFactory {

    private static MusicSyncFactory instance;
    public static MusicSyncFactory getInstance()
    {
        if(instance == null)
            instance = new MusicSyncFactory();
        return instance;
    }

    private MusicSyncFactory() {
    }

    public IPlaylistController getPlaylistController ()
    {
        return new PlaylistControllerImpl();
    }

    @Override
    public ISoundController getLocalSoundController() {
        return null;
    }

    @Override
    public IP2PNetworkController getNetworkController(String type) {
        return null;
    }

    private ISessionController sessionController;

    @Override
    public ISessionController getSessionController() {
        if(sessionController == null) {
            sessionController = new SessionControllerImpl(getCommuicationClient());
        }
        return sessionController;
    }


    private ICommunicationClient communicationClient;
    @Override
    public ICommunicationClient getCommuicationClient() {
        if(communicationClient == null)
            communicationClient = new CommunicationClientImpl();
        return communicationClient;
    }
    private ICommunicationServer communicationServer;
    @Override
    public ICommunicationServer getCommuicationServer() {
        if(communicationServer == null)
            communicationServer = new CommunicationServerImpl();
        return communicationServer;
    }

    @Override
    public ISnapdroidServer getSnapdroidServer() {
        return null;
    }

    @Override
    public ISnapdroidClient getSnapdroidClient() {
        return null;
    }
}