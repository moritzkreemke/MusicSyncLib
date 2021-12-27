package com.moritz.musicsyncapp.model.client;

import com.moritz.musicsyncapp.model.device.IDevice;

public class AnyClient implements IClient{


    @Override
    public String getID() {
        return "ANY";
    }
}
