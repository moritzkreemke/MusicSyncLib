package com.moritz.musicsyncapp.model.device;

import java.util.ArrayList;
import java.util.List;

public interface IDevice {

    String getID ();
    String getDisplayName();
    int getStatus();

    static IDevice getDeviceById(IDevice[] devices, String id) {
        for (IDevice device : devices) {
            if(device.getID().equals(id))
                return device;
        }
        return null;
    }

    static IDevice[] filterByStatus (IDevice[] devices, int status) {
        List<IDevice> deviceList = new ArrayList<>();
        for (IDevice device : devices) {
            if(device.getStatus() == status)
                deviceList.add(device);
        }
        return deviceList.toArray(new IDevice[deviceList.size()]);
    }

}
