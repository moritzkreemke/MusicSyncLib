package com.moritz.musicsyncapp.controller.session;

import com.moritz.musicsyncapp.controller.commuication.client.ICommunicationClient;
import com.moritz.musicsyncapp.model.client.IClient;
import com.moritz.musicsyncapp.model.commuication.ISendableMessage;
import com.moritz.musicsyncapp.model.commuication.events.EventMessage;
import com.moritz.musicsyncapp.model.commuication.messages.AvailableClientsChanged;
import com.moritz.musicsyncapp.model.commuication.messages.PlaylistChangedMessage;
import com.moritz.musicsyncapp.model.commuication.messages.RequestPlaylistMessage;
import com.moritz.musicsyncapp.model.playlist.IPlaylist;
import com.moritz.musicsyncapp.model.session.ISession;
import com.moritz.musicsyncapp.model.session.SessionImpl;
import com.moritz.musicsyncapp.model.track.ITrack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SessionControllerImpl implements ISessionController {

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public static final String SESSION_CHANGED_ACTION = "session";

    private ICommunicationClient communicationClient;
    private ISession session;



    public SessionControllerImpl(ICommunicationClient communicationClient) {
        this.communicationClient = communicationClient;
        communicationClient.addOnConnectedChangeListener(evt -> {
            if(evt.getNewValue().equals(true)) {
                onConnectedToServer();
            }
        });
        communicationClient.addOnReviveMessageListener(message -> onReciveMessage(message));
        setSession(new SessionImpl());
    }

    private void onConnectedToServer () {
        communicationClient.sendMessage(EventMessage.EVENT_CLIENTS_UPDATED, IClient.EVENT);
        communicationClient.sendMessage(new RequestPlaylistMessage(), IClient.SERVER);
    }
    private void onReciveMessage (ISendableMessage message) {
        if (message.getMessage() instanceof AvailableClientsChanged) {
            AvailableClientsChanged availableClientsChanged = (AvailableClientsChanged) message.getMessage();
            List<IClient> clientList = availableClientsChanged.getClientList();
            // setClients(clientList.toArray(new IClient[clientList.size()]));
            setSession(new SessionImpl(clientList));
        } else if(message.getMessage() instanceof PlaylistChangedMessage) {
            PlaylistChangedMessage playlistChangedMessage = (PlaylistChangedMessage) message.getMessage();
            setSession(new SessionImpl(List.of(getSession().getClients()), playlistChangedMessage.getPlaylist()));
        }
    }


    private void setSession(ISession session) {
        pcs.firePropertyChange(SESSION_CHANGED_ACTION, this.session, session);
        this.session = session;
    }
    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public void playTrack(ITrack track) {

    }

    @Override
    public void addSessionChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(SESSION_CHANGED_ACTION, listener);
    }

    @Override
    public void removeSessionChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(SESSION_CHANGED_ACTION, listener);
    }
}
