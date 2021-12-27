package com.moritz.musicsyncapp.controller.p2pnetwork;


import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.device.IDevice;
import com.moritz.musicsyncapp.model.session.ISession;


public interface IP2PNetworkController {



    void connectDevice(IDevice iDevice);
    void disconnect();
    void discoverDevices();
    IDevice[] getDevices();
    boolean isOwner();

    void sendMessage (byte[] message, IClient reciver);


}
