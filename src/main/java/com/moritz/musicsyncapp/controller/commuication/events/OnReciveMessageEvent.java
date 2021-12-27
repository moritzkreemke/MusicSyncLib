package com.moritz.musicsyncapp.controller.commuication.events;

import com.moritz.musicsyncapp.model.commuication.ISendableMessage;

public interface OnReciveMessageEvent {

    void onReceiveMessage(ISendableMessage message);

}
