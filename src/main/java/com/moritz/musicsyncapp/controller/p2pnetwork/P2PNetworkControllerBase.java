package com.moritz.musicsyncapp.controller.p2pnetwork;

import com.moritz.musicsyncapp.controller.p2pnetwork.events.IP2PNetworkControllerDevicesFoundChangedEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class P2PNetworkControllerBase implements IP2PNetworkController {

    private List<IP2PNetworkControllerDevicesFoundChangedEvent> networkControllerDevicesFoundChangedEvents;

    public P2PNetworkControllerBase() {
        this.networkControllerDevicesFoundChangedEvents = new ArrayList<>();
    }

    @Override
    public void addOnDevicesFoundChangeListener(IP2PNetworkControllerDevicesFoundChangedEvent event) {
        networkControllerDevicesFoundChangedEvents.add(event);
    }

    abstract void onDevicesFoundTrigger ();
}
