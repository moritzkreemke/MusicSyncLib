package com.moritz.musicsyncapp.model.commuication.events;

import com.moritz.musicsyncapp.model.commuication.IMessage;
import org.json.JSONObject;

public class EventMessage implements IMessage {

    public static final String EVENT_CLIENTS_UPDATED_TYPE = "EVENT_CLIENTS_UPDATED";
    public static  EventMessage EVENT_CLIENTS_UPDATED = new EventMessage(EVENT_CLIENTS_UPDATED_TYPE);

    private String name;
    public String getName () {
        return name;
    }

    public EventMessage() {
    }

    public EventMessage(String name) {
        this.name = name;
    }

    @Override
    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        return jsonObject.toString();
    }

    @Override
    public IMessage fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        name = jsonObject.getString("name");
        return this;
    }
}
