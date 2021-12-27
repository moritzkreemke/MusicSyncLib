package com.moritz.musicsyncapp.controller.session;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.session.ISession;
import com.moritz.musicsyncapp.model.track.ITrack;

import java.beans.PropertyChangeListener;

public interface ISessionController {

    ISession getSession();
    void playTrack (ITrack track);

    void addSessionChangeListener(PropertyChangeListener listener);

    void removeSessionChangeListener(PropertyChangeListener listener);

}
