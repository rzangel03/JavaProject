package org.example.trackartist.dao.InMemory;

import org.example.trackartist.classes.*;
import org.example.trackartist.dao.TrackDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Profile("dev")
public class InMemoryTrackDAO implements TrackDAO {

    private Map<Integer, Track> trackMap = new HashMap<>();
    private int nextId = 1;

    @Override
    public Track insert(Track track) {
        int id = nextId++;
        track.setIdTrack(id);
        track.setTittle(track.getTittle());
        trackMap.put(track.getIdTrack(), track);
        return track;
    }

    @Override
    public boolean delete(int idTrack) {
        return trackMap.remove(idTrack) != null;
    }

    @Override
    public boolean update(Track track) {
        return trackMap.replace(track.getIdTrack(), track) != null;
    }

    @Override
    public Track getTrackById(int idTrack) {
        return trackMap.get(idTrack);
    }

    @Override
    public List<Track> getTrackByMediaType(MediaType mediaType) {
        return trackMap.values().stream().filter(f -> f.getMediaType().equals(mediaType)).collect(Collectors.toList());
    }

    @Override
    public List<Track> getTrackByYear(Year issueDate) {
        return trackMap.values().stream().filter(f -> f.getIssueDate().getYear() == issueDate.getValue()).collect(Collectors.toList());
    }
    @Override
    public List<Track> getAllTracks() {
        return new ArrayList<>(trackMap.values());
    }

    @Override
    public List<Track> getTrackByDuration(TypeDuration typeDuration, Time duration) {
        return switch (typeDuration) {
            case Longer ->
                    trackMap.values().stream().filter(f -> f.getDuration().after(duration)).collect(Collectors.toList());
            case Shorted ->
                    trackMap.values().stream().filter(f -> f.getDuration().before(duration)).collect(Collectors.toList());
            case Equal ->
                    trackMap.values().stream().filter(f -> f.getDuration().equals(duration)).collect(Collectors.toList());
        };
    }
}
