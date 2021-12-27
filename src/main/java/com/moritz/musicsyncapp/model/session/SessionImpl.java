package com.moritz.musicsyncapp.model.session;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.playlist.SessionClientPlaylistImpl;


import java.util.ArrayList;
import java.util.List;


 public class SessionImpl implements ISession{


    private List<IClient> clients;
    private IPlaylist playlist;


     public SessionImpl() {
        this.clients = new ArrayList<>();
        this.playlist = new SessionClientPlaylistImpl();
    }

    public SessionImpl(List<IClient> clients) {
        this();
         this.clients = clients;
    }
     public SessionImpl(List<IClient> clients, IPlaylist playlist) {
         this(clients);
         this.playlist = playlist;
     }

     protected void setClients(List<IClient> clients) {
         this.clients = clients;
     }
     @Override
    public IClient[] getClients() {
        return clients.toArray(new IClient[clients.size()]);
    }


    @Override
    public IPlaylist getSessionPlaylist() {
        return playlist;
    }


     @Override
    public boolean exits() {
        return clients.size() > 1;
    }

 }
