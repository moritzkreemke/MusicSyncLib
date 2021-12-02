package com.moritz.musicsyncapp.controller.p2pnetwork;

import com.moritz.musicsyncapp.controller.p2pnetwork.events.IP2PNetworkControllerDevicesFoundChangedEvent;
import com.moritz.musicsyncapp.controller.p2pnetwork.events.P2PNetworkControllerClientConnectedEvent;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.device.IDevice;

import java.util.ArrayList;
import java.util.List;

public abstract class P2PNetworkControllerBase implements IP2PNetworkController {

    private List<IP2PNetworkControllerDevicesFoundChangedEvent> networkControllerDevicesFoundChangedEvents;
    private List<P2PNetworkControllerClientConnectedEvent> clientConnectedEvents;

    public P2PNetworkControllerBase() {
        this.networkControllerDevicesFoundChangedEvents = new ArrayList<>();
        this.clientConnectedEvents = new ArrayList<>();
    }

    @Override
    public void addOnDevicesFoundChangeListener(IP2PNetworkControllerDevicesFoundChangedEvent event) {
        networkControllerDevicesFoundChangedEvents.add(event);
    }

    @Override
    public void addOnClientConnectedListener(P2PNetworkControllerClientConnectedEvent event) {
        clientConnectedEvents.add(event);
    }

    void onDevicesFoundTrigger (IDevice[] devices) {
        for (IP2PNetworkControllerDevicesFoundChangedEvent networkControllerDevicesFoundChangedEvent : networkControllerDevicesFoundChangedEvents) {
            networkControllerDevicesFoundChangedEvent.onDevicesFound(devices);
        }
    }

    void onClientConnectedTrigger (IClient client)
    {
        for (P2PNetworkControllerClientConnectedEvent clientConnectedEvent : clientConnectedEvents) {
            clientConnectedEvent.onClientConnected(client);
        }
    }
}
