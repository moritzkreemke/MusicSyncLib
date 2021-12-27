package com.moritz.musicsyncapp.model.commuication.messages;

import com.moritz.musicsyncapp.model.commuication.IMessage;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.playlist.SessionClientPlaylistImpl;
import com.moritz.musicsyncapp.model.track.ITrack;
import com.moritz.musicsyncapp.model.track.NetworkTrack;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaylistChangedMessage implements IMessage {

    private IPlaylist playlist;

    public PlaylistChangedMessage() {
    }

    public void setPlaylist(IPlaylist playlist) {
        this.playlist = playlist;
    }

    public IPlaylist getPlaylist() {
        return playlist;
    }

    @Override
    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", playlist.getName());
        jsonObject.put("provider", playlist.getProvider());

        JSONArray jsonArray = new JSONArray();
        for (ITrack track : playlist.getTracks()) {
            JSONObject jsonTrack = new JSONObject();
            jsonTrack.put("name", track.getName());
            jsonTrack.put("artist", track.getArtist());
            jsonTrack.put("duration", track.getDuration());
            jsonTrack.put("uri", track.getUri());
            jsonArray.put(jsonTrack);
        }
        jsonObject.put("tracks", jsonArray);
        return jsonObject.toString();
    }

    @Override
    public IMessage fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        String provider = jsonObject.getString("provider");

        List<ITrack> tracks = new ArrayList<>();
        JSONArray trackArr = jsonObject.getJSONArray("tracks");
        for (int i = 0; i < trackArr.length(); i++) {
            JSONObject trackObj = trackArr.getJSONObject(i);
            String trackName = trackObj.getString("name");
            String artistName = trackObj.getString("artist");
            int duration = trackObj.getInt("duration");
            String trackUri = trackObj.getString("uri");
            NetworkTrack networkTrack = new NetworkTrack(trackName, artistName, trackUri, duration);
            tracks.add(networkTrack);
        }
        PlaylistChangedMessage playlistChangedMessage = new PlaylistChangedMessage();
        SessionClientPlaylistImpl networkPlaylist = new SessionClientPlaylistImpl();
        networkPlaylist.setTracksWithoutTrigger(tracks);
        playlistChangedMessage.setPlaylist(networkPlaylist);
        return playlistChangedMessage;
    }
}
