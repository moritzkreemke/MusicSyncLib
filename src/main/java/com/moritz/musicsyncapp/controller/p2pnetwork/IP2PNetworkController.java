package com.moritz.musicsyncapp.controller.p2pnetwork;

import com.moritz.musicsyncapp.controller.p2pnetwork.events.IP2PNetworkControllerDevicesFoundChangedEvent;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.device.IDevice;


public interface IP2PNetworkController {


    void addOnDevicesFoundChangeListener(IP2PNetworkControllerDevicesFoundChangedEvent event);
    IDevice[] findDevices();
    IClient connectDevice(IDevice device);

    void sendMessage (byte[] message, IClient reciver);


}
