package org.example.trackartist.dao.InMemory;

import org.example.trackartist.classes.Artist;
import org.example.trackartist.classes.TrackPrice;
import org.example.trackartist.dao.ArtistDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("dev")
public class InMemoryArtistDAO implements ArtistDAO {
    private Map<Integer, Artist> artistMap = new HashMap<>();
    private Map<Integer, TrackPrice> trackMap = new HashMap<>();
    private int nextId = 1;

    @Override
    public Artist insert(Artist artist) {
        int id = nextId++;
        artist.setIdArtist(id);
        artist.setName(artist.getName());
        artistMap.put(artist.getIdArtist(), artist);
        return artist;
    }

    @Override
    public boolean delete(int id) {
        return artistMap.remove(id) != null;
    }

    @Override
    public boolean update(Artist artist) {
        return artistMap.replace(artist.getIdArtist(), artist) != null;
    }

    @Override
    public Artist getById(int idArtist) {
        return artistMap.get(idArtist);
    }

    @Override
    public Artist getArtistByName(String name) {
        return artistMap.values().stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Artist> getAllArtist() {
        return new ArrayList<>(artistMap.values());
    }

}
