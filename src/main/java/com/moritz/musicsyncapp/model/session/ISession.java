package com.moritz.musicsyncapp.model.session;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.track.ITrack;

public interface ISession {

    IClient[] getClients();
    boolean exits();
    boolean isOwner();
    IPlaylist getSessionPlaylist();
    void playTrack (ITrack track);

}
