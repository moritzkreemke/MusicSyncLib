package com.moritz.musicsyncapp.controller.p2pnetwork.events;

import com.moritz.musicsyncapp.model.client.IClient;

public interface P2PNetworkControllerConnectingEvent {

    void onSuccessfulConnected ();
    void onFailure (int i);

}
