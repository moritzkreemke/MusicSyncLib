package com.moritz.musicsyncapp;

import com.moritz.musicsyncapp.controller.p2pnetwork.IP2PNetworkController;
import com.moritz.musicsyncapp.controller.playlist.IPlaylistController;
import com.moritz.musicsyncapp.controller.playlist.PlaylistControllerImpl;
import com.moritz.musicsyncapp.controller.sound.ISoundController;


public class MusicSyncFactory implements ISyncFactory {

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
}