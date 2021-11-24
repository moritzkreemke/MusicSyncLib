package com.moritz.musicsyncapp.controller.sound;

import com.moritz.musicsyncapp.model.track.IPlayableTrack;


public interface ISoundController {

    void play(IPlayableTrack track);
    void stop();

}
