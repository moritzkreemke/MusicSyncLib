package com.moritz.musicsyncapp.model.track;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class LocalTrack implements ITrack{


    private String name;
    private String artist;
    private String uri;


    public LocalTrack(String name, String artist, String uri) {
        this.name = name;
        this.artist = artist;
        this.uri = uri;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public int getDuration() {
        try {
            //just to satify the compiler
            Class.forName("javax.sound.sampled.AudioSystem");
            var audio =  AudioSystem.getAudioFileFormat(new File(uri));
            return audio.getFrameLength();
        } catch (ClassNotFoundException | IOException | UnsupportedAudioFileException e) {
            return -1;
        }
    }
}
