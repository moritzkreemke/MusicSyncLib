package com.moritz.musicsyncapp.model.commuication.messages;

import com.moritz.musicsyncapp.model.commuication.IMessage;
import org.json.JSONObject;

public class RequestPlaylistMessage implements IMessage {


    public RequestPlaylistMessage() {

    }

    @Override
    public String toJson() {
        return new JSONObject().toString();
    }

    @Override
    public IMessage fromJson(String json) {
        return new RequestPlaylistMessage();
    }
}
