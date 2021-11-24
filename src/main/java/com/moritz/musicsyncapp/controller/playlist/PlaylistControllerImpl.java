package com.moritz.musicsyncapp.controller.playlist;

import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.playlist.providers.IPlaylistProvider;
import com.moritz.musicsyncapp.model.playlist.providers.LocalPlaylistProvider;
import jdk.jshell.spi.ExecutionControl;

public class PlaylistControllerImpl implements IPlaylistController {



    @Override
    public IPlaylist[] getPlaylistFromFilePath(CharSequence path) {
        IPlaylistProvider provider = new LocalPlaylistProvider(path);
        return provider.getAllPlaylists();
    }

    @Override
    public IPlaylist[] getSystemPlaylists() {
        return new IPlaylist[0];
    }

}
