package com.moritz.musicsyncapp.model.commuication;


import com.moritz.musicsyncapp.model.client.IClient;
import org.json.JSONObject;

public interface ISendableMessage {

    String SENDER_KEY = "sender";
    String RECIEVER_KEY = "receiver";
    String TYPE_KEY = "type";
    String MESSAGE_KEY = "message";


    IClient getSender();
    IClient getReciver();
    
    default String getType () { 
        return getMessage().getClass().getName().replace("class ", "");
    }
    IMessage getMessage();

    default String toJSON () {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SENDER_KEY, getSender().getID());
        jsonObject.put(RECIEVER_KEY, getReciver().getID());
        jsonObject.put(TYPE_KEY, getType());
        jsonObject.put(MESSAGE_KEY, getMessage().toJson());

        return jsonObject.toString();
    }

}
