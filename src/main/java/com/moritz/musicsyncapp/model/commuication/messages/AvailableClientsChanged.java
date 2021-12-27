package com.moritz.musicsyncapp.model.commuication.messages;

import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.IMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvailableClientsChanged implements IMessage {


    private List<IClient> clientList = new ArrayList<>();

    public AvailableClientsChanged () {

    }

    public AvailableClientsChanged(List<IClient> clientList) {
        this.clientList = clientList;
    }

    public List<IClient> getClientList() {
        return clientList;
    }

    @Override
    public String toJson() {

        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < clientList.size(); i++) {
            JSONObject clientJSON = new JSONObject();
            clientJSON.put("ID", clientList.get(i).getID());
            jsonObject.append("clients", clientJSON);
        }

        return jsonObject.toString();
    }

    @Override
    public IMessage fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("clients");
        for (int i = 0; i < jsonArray.length(); i++) {
            clientList.add(new ClientImpl(jsonArray.getJSONObject(i).getString("ID")));
        }

        return this;
    }

    public class ClientImpl implements IClient  {

        private String ID;
        private ClientImpl(String ID) {
            this.ID = ID;
        }

        @Override
        public String getID() {
            return ID;
        }
    }
}
