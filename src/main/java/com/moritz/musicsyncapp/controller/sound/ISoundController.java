package com.moritz.musicsyncapp.controller.sound;

import com.moritz.musicsyncapp.controller.sound.events.ISoundControllerOnTrackPlayEvent;
import com.moritz.musicsyncapp.model.track.IPlayableTrack;


public interface ISoundController {

    void addOnTrackPlayEvent(ISoundControllerOnTrackPlayEvent event);
    void play(IPlayableTrack track);
    void stop();

}
