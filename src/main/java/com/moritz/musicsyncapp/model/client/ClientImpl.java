package com.moritz.musicsyncapp.model.client;

public class ClientImpl implements IClient {

    private String ID;

    public ClientImpl(String ID) {
        this.ID = ID;
    }

    @Override
    public String getID() {
        return ID;
    }
}
