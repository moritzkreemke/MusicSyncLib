package com.moritz.musicsyncapp.model.client;

import com.moritz.musicsyncapp.model.device.IDevice;

public interface IClient {

    String getAddress();
    IDevice getDevice();

}
