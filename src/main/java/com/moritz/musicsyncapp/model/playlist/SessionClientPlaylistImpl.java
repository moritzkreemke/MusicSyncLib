package com.moritz.musicsyncapp.model.playlist;


import com.moritz.musicsyncapp.MusicSyncFactory;
import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.messages.PlaylistChangedMessage;
import com.moritz.musicsyncapp.model.track.ITrack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class SessionClientPlaylistImpl implements IPlaylist{


    private ICommunicationClient communicationClient;
    private List<ITrack> tracks = new ArrayList<>();

    public SessionClientPlaylistImpl() {
        this.communicationClient = MusicSyncFactory.getInstance().getCommuicationClient();
    }

    public SessionClientPlaylistImpl(ICommunicationClient communicationClient) {
        this.communicationClient = communicationClient;
    }

    @Override
    public String getProvider() {
        return "network_client";
    }

    @Override
    public String getName() {
        return "Session Playlist";
    }

    @Override
    public ITrack[] getTracks() {
        return tracks.toArray(new ITrack[tracks.size()]);
    }

    @Override
    public void addTrack(ITrack track) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                addTracksAsync(track);
            }
        });
    }

    private void addTracksAsync (ITrack track) {
        tracks.add(track);
        PlaylistChangedMessage playlistChangedMessage = new PlaylistChangedMessage();
        playlistChangedMessage.setPlaylist(this);
        communicationClient.sendMessage(playlistChangedMessage, IClient.SERVER);
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
        PlaylistChangedMessage playlistChangedMessage = new PlaylistChangedMessage();
        playlistChangedMessage.setPlaylist(this);
        communicationClient.sendMessage(playlistChangedMessage, IClient.SERVER);
    }

    public void setTracksWithoutTrigger (List<ITrack> tracks) {
        this.tracks = tracks;
    }

}
