package com.moritz.musicsyncapp.model.client;

import com.moritz.musicsyncapp.model.device.IDevice;

public interface IClient {

    IClient ANY = new ClientImpl("ANY");
    IClient EVENT = new ClientImpl("EVENT");

    String getID();

}
