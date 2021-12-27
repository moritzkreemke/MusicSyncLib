package com.moritz.musicsyncapp.controller.commuication;

import com.moritz.musicsyncapp.model.commuication.IMessage;
import org.json.JSONObject;

public class TestMessage implements IMessage {

    public TestMessage () {}

    private String name = "Test";

    @Override
    public String toJson() {
        JSONObject object = new JSONObject();
        object.put("name", name);

        return object.toString();
    }

    @Override
    public IMessage fromJson(String json) {
        JSONObject object = new JSONObject(json);
        name = object.getString("name");
        return this;
    }

}
