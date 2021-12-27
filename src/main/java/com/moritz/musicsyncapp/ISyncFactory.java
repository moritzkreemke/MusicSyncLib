package com.moritz.musicsyncapp;

import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.controller.commuication.server.ICommunicationServer;
import com.moritz.musicsyncapp.controller.p2pnetwork.IP2PNetworkController;
import com.moritz.musicsyncapp.controller.playlist.IPlaylistController;
import com.moritz.musicsyncapp.controller.session.ISessionController;
import com.moritz.musicsyncapp.controller.sound.ISoundController;

public interface ISyncFactory {

    ISoundController getLocalSoundController();
    IPlaylistController getPlaylistController();
    IP2PNetworkController getNetworkController(String type);
    ISessionController getSessionController ();
    ICommunicationClient getCommuicationClient ();
    ICommunicationServer getCommuicationServer();


}
