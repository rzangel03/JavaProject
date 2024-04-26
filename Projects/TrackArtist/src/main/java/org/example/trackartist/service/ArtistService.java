package org.example.trackartist.service;


import org.example.trackartist.classes.Artist;
import org.example.trackartist.classes.Track;
import org.example.trackartist.classes.TrackPrice;
import org.example.trackartist.dao.ArtistDAO;
import org.example.trackartist.dao.TrackDAO;
import org.example.trackartist.price.PriceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private PriceProvider priceProvider;
    @Autowired
    private TrackDAO trackDAO;
    private ArtistDAO artistDAO;

    public ArtistService(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public Artist addArtist(Artist artist) {
        return artistDAO.insert(artist);
    }

    public boolean deleteArtist(int id) {
        return artistDAO.delete(id);
    }

    public boolean updateArtist(Artist artist) {
        return artistDAO.update(artist);
    }

    public Artist getById(int idArtist) {
        return artistDAO.getById(idArtist);
    }

    public Artist getArtistByName(String name) {
        return artistDAO.getArtistByName(name);
    }

    public List<Artist> getAllArtist() {
        return artistDAO.getAllArtist();
    }
    public List<TrackPrice> getTracksByArtist(int idArtist) {
        Artist artist = artistDAO.getById(idArtist);
        List<Integer> idTracksList = artist.getIdTracks();

        List<Track> trackList = trackDAO.getAllTracks().stream().filter(w -> idTracksList.contains(w.getIdTrack())).toList();
        List<TrackPrice> trackPriceList = new ArrayList<>();
        trackList.forEach(f ->{
            TrackPrice trackPrice = transformTrackPrice(f);
            priceProvider.setPriceToTrack(trackPrice);
            trackPriceList.add(trackPrice);
        });
        return trackPriceList;
    }
    private TrackPrice transformTrackPrice(Track track){
        return new TrackPrice(track.getIdTrack(),track.getTittle(),track.getAlbum(),track.getIssueDate(),track.getMediaType(),track.getDuration(),0,track.getIdArtist());
    }
}
