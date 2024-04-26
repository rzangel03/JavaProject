package org.example.trackartist.dao;



import org.example.trackartist.classes.MediaType;
import org.example.trackartist.classes.Track;
import org.example.trackartist.classes.TypeDuration;

import java.sql.Time;
import java.time.Year;
import java.util.List;

public interface TrackDAO {
    Track insert(Track track);

    boolean delete(int idTrack);

    boolean update(Track track);

    Track getTrackById(int idTrack);
    List<Track> getAllTracks();
    List<Track> getTrackByMediaType(MediaType mediaType);

    List<Track> getTrackByYear(Year issueDate);

    List<Track> getTrackByDuration(TypeDuration typeDuration, Time duration);

}
