package com.moritz.musicsyncapp.controller.commuication.server;

import com.moritz.musicsyncapp.model.client.IClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class ServerClientsList extends CopyOnWriteArrayList<IClient> {


    public IClient[] getClients (String id) {
        if(id.equals("ANY"))
            return toArray(new IClient[size()]);
        //return (IClient[]) stream().filter(client -> client.equals(id)).toArray();
        List<IClient> result = new ArrayList<>();
        for(IClient client : this) {
            if(client.getID().equals(id))
                result.add(client);
        }
        return result.toArray(new IClient[result.size()]);
    }

}
