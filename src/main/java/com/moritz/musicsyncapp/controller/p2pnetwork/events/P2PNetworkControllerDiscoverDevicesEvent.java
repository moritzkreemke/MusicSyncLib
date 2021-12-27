package com.moritz.musicsyncapp.controller.p2pnetwork.events;

import com.moritz.musicsyncapp.model.device.IDevice;

public interface P2PNetworkControllerDiscoverDevicesEvent {

    void onDevicesFound (IDevice[] devices);
    void onFailure (int errorCode);
    void onFinished ();

}
