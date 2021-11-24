package com.moritz.musicsyncapp.model.track;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.InputStream;

public class LocalTrack implements IPlayableTrack{


    private String name;
    private String artist;
    private String uri;
    private int duration;


    public LocalTrack(String name, String artist, String uri, int duration) {
        this.name = name;
        this.artist = artist;
        this.uri = uri;
        this.duration = duration;
    }

    public LocalTrack(String name, String artist, String uri) {
        this(name, artist, uri, -1);
        try {
            //just to satify the compiler
            Class.forName("javax.sound.sampled.AudioSystem");
            var audio =  AudioSystem.getAudioFileFormat(new File(uri));
            duration = audio.getFrameLength();
        } catch (Throwable ignore) {}
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
        return duration;
    }

    @Override
    public InputStream getStream() {
        return null;
    }
}
