package com.moritz.musicsyncapp.model.session;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.track.ITrack;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
 class SessionImpl implements ISession{

    private List<IClient> clients;
    private boolean isOwner;

    public SessionImpl(boolean isOwner) {
        this.clients = new ArrayList<>();
        this.isOwner = isOwner;
    }

     protected void setClients(List<IClient> clients) {
         this.clients = clients;
     }
     @Override
    public IClient[] getClients() {
        return clients.toArray(new IClient[clients.size()]);
    }

     protected void setOwner(boolean owner) {
         isOwner = owner;
     }
     @Override
    public boolean isOwner() {
        return isOwner;
    }

    @Override
    public IPlaylist getSessionPlaylist() {
        return null;
    }

    @Override
    public void playTrack(ITrack track) {

    }

    @Override
    public boolean exits() {
        return clients.size() != 0;
    }
}
