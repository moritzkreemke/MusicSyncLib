package com.moritz.musicsyncapp.controller.commuication.events;

public interface OnConnectEvent {

    void success();
    void onFailure (int i);

}
