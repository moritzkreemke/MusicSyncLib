package com.moritz.musicsyncapp.controller.commuication.events;

import com.moritz.musicsyncapp.model.client.IClient;

public interface OnNewConnection {

    void OnNewConnection (IClient client);

}
