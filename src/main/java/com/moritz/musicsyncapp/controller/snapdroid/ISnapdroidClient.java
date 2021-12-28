package com.moritz.musicsyncapp.controller.snapdroid;

import java.net.InetAddress;

public interface ISnapdroidClient {

    void connect (InetAddress addr, int port, String ID);
    void close();

}
