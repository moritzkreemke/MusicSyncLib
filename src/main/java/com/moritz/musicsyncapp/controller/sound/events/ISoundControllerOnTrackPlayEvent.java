package com.moritz.musicsyncapp.controller.sound.events;

import com.moritz.musicsyncapp.model.track.ITrack;

public interface ISoundControllerOnTrackPlayEvent {


    void onTrackPlayEvent (ITrack track);

}
