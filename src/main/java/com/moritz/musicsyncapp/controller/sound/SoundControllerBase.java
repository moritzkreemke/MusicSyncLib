package com.moritz.musicsyncapp.controller.sound;

import com.moritz.musicsyncapp.controller.sound.events.ISoundControllerOnTrackPlayEvent;
import com.moritz.musicsyncapp.model.track.IPlayableTrack;

import java.util.ArrayList;
import java.util.List;

public abstract class SoundControllerBase implements ISoundController {

    private List<ISoundControllerOnTrackPlayEvent> onTrackPlayEvents = new ArrayList<>();

    @Override
    public void addOnTrackPlayEvent(ISoundControllerOnTrackPlayEvent event) {
        onTrackPlayEvents.add(event);
    }

    @Override
    public void play(IPlayableTrack track) {
        for (ISoundControllerOnTrackPlayEvent onTrackPlayEvent : onTrackPlayEvents) {
            onTrackPlayEvent.onTrackPlayEvent(track);
        }
    }
}
