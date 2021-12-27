package com.moritz.musicsyncapp.controller.p2pnetwork.events;

import com.moritz.musicsyncapp.model.device.IDevice;

public interface P2PNetworkControllerDevicesFoundEvent {

    void onDevicesFound (IDevice[] devices);

}
