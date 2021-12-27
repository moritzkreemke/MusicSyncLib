package com.moritz.musicsyncapp.model.session;

import com.moritz.musicsyncapp.model.client.IClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionBuilder {

    public static SessionBuilder get ()
    {
        return new SessionBuilder();
    }
    public static SessionBuilder get(ISession session)
    {
        SessionBuilder sb = new SessionBuilder();
        sb.clients = Arrays.asList(session.getClients());
        sb.isOwner = session.isOwner();
        return sb;
    }

    public ISession build () {
        SessionImpl session = new SessionImpl(false);
        session.setClients(clients);
        session.setOwner(isOwner);
        return session;
    }

    private boolean isOwner;
    private List<IClient> clients = new ArrayList<>();

    public SessionBuilder setOwner(boolean owner) {
        isOwner = owner;
        return this;
    }

    public SessionBuilder setClients(List<IClient> clients) {
        this.clients = clients;
        return this;
    }
}
