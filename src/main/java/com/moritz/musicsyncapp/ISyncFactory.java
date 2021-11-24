package com.moritz.musicsyncapp;

import com.moritz.musicsyncapp.controller.playlist.IPlaylistController;
import com.moritz.musicsyncapp.controller.sound.ISoundController;

public interface ISyncFactory {

    ISoundController getLocalSoundController();
    IPlaylistController getPlaylistController();


}
