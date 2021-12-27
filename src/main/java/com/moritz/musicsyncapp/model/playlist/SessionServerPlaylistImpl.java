package com.moritz.musicsyncapp.model.playlist;

import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.model.track.ITrack;

import java.util.ArrayList;
import java.util.List;

public class SessionServerPlaylistImpl implements IPlaylist {



    private List<ITrack> tracks = new ArrayList<>();


    @Override
    public String getProvider() {
        return "network_server";
    }

    @Override
    public String getName() {
        return "no name";
    }

    @Override
    public ITrack[] getTracks() {
        return tracks.toArray(new ITrack[tracks.size()]);
    }

    @Override
    public void addTrack(ITrack track) {
        tracks.add(track);
    }

    @Override
    public void setTracks(ITrack[] tracks, boolean append) {
        if(!append)
            setTracks(List.of(tracks));
        else {
            for (ITrack track : tracks) {
                addTrack(track);
            }
        }
    }

    void setTracks(List<ITrack> tracks) {
        this.tracks = tracks;
    }
}
