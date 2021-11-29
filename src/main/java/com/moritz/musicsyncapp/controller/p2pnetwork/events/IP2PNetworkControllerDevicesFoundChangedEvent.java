package com.moritz.musicsyncapp.controller.p2pnetwork.events;

import com.moritz.musicsyncapp.model.device.IDevice;

public interface IP2PNetworkControllerDevicesFoundChangedEvent {
    void onDevicesFound(IDevice[] devices);
}
