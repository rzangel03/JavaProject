package org.example.trackartist.dao;



import org.example.trackartist.classes.Artist;

import java.util.List;

public interface ArtistDAO {
    Artist insert(Artist artist);

    boolean delete(int id);

    boolean update(Artist artist);

    Artist getById(int idArtist);

    Artist getArtistByName(String name);

    List<Artist> getAllArtist();
}
