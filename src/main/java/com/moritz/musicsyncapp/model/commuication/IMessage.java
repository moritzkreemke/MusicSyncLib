package com.moritz.musicsyncapp.model.commuication;

import java.io.Serializable;

public interface IMessage extends Serializable {

    String toJson();
    IMessage fromJson (String json);

}
