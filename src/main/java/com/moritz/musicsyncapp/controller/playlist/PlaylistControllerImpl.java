package com.moritz.musicsyncapp.controller.playlist;

import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.playlist.providers.IPlaylistProvider;
import com.moritz.musicsyncapp.model.playlist.providers.LocalPlaylistProvider;

public class PlaylistControllerImpl implements IPlaylistController {



    @Override
    public IPlaylist[] getPlaylistFromFilePath(CharSequence path) {
        IPlaylistProvider provider = new LocalPlaylistProvider(path);
        return provider.getAllPlaylists();
    }
}
