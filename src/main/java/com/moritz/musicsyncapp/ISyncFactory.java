package com.moritz.musicsyncapp;

import com.moritz.musicsyncapp.controller.p2pnetwork.IP2PNetworkController;
import com.moritz.musicsyncapp.controller.playlist.IPlaylistController;
import com.moritz.musicsyncapp.controller.sound.ISoundController;

public interface ISyncFactory {

    ISoundController getLocalSoundController();
    IPlaylistController getPlaylistController();
    IP2PNetworkController getNetworkController(String type);


}
