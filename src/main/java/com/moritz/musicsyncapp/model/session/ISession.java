package com.moritz.musicsyncapp.model.session;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.track.ITrack;

import java.beans.PropertyChangeListener;

public interface ISession {

    IClient[] getClients();
    boolean exits();
    IPlaylist getSessionPlaylist();


}
