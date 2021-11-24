package com.moritz.musicsyncapp.model.track;

import java.io.InputStream;

public interface IPlayableTrack extends ITrack{

    InputStream getStream();


}
