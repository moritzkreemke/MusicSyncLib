package com.moritz.musicsyncapp.controller.snapdroid;

import com.moritz.musicsyncapp.model.track.IPlayableTrack;

public interface ISnapdroidServer {


    void start ();
    void playTrack (IPlayableTrack playableTrack);
    void shutdown ();
}
