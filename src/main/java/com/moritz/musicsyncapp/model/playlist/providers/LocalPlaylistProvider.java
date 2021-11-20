package com.moritz.musicsyncapp.model.playlist.providers;

import com.moritz.musicsyncapp.Constants;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.playlist.PlaylistBuilder;
import com.moritz.musicsyncapp.model.track.ITrack;
import com.moritz.musicsyncapp.model.track.LocalTrack;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class LocalPlaylistProvider implements IPlaylistProvider{

    private CharSequence path;

    public LocalPlaylistProvider(CharSequence path) {
        this.path = path;
    }

    @Override
    public IPlaylist[] getAllPlaylists() {

        File[] files = new File(path.toString()).listFiles(Constants.SUPPORTED_MUSIC_FILES_FILTER());

        PlaylistBuilder playlistBuilder = PlaylistBuilder.create(getName().toString().toLowerCase(), "local");
        for (File file : files) {
            ITrack track = new LocalTrack(file.getName(), "moritz", file.getAbsolutePath());
            playlistBuilder.addTrack(track);
        }
        IPlaylist result = playlistBuilder.build();

        if(result.getTracks().length == 0)
            return new IPlaylist[0];
        else
            return new IPlaylist[] {result};
    }

    @Override
    public String getName() {
        return "LOCAL";
    }
}
