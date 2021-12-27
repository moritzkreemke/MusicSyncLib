package com.moritz.musicsyncapp.model.commuication;

import com.moritz.musicsyncapp.model.client.ClientImpl;
import com.moritz.musicsyncapp.model.client.IClient;

class SendableMessageImpl implements ISendableMessage{

    private IClient sender;
    private IClient reciver;
    private IMessage message;

    public SendableMessageImpl(IClient sender, IClient reciver, IMessage message) {
        this.sender = sender;
        this.reciver = reciver;
        this.message = message;
    }

    @Override
    public IClient getSender() {
        return sender;
    }

    @Override
    public IClient getReciver() {
        return reciver;
    }

    @Override
    public IMessage getMessage() {
        return message;
    }
}
