package org.example.trackartist.service;

import org.example.trackartist.classes.*;
import org.example.trackartist.dao.ArtistDAO;
import org.example.trackartist.dao.TrackDAO;
import org.example.trackartist.price.PriceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {
    @Autowired
    private PriceProvider priceProvider;
    @Autowired
    private TrackDAO trackDAO;
    @Autowired
    private ArtistDAO artistDAO;




    public Track addTrack(Track track) {
        return trackDAO.insert(track);
    }

    public boolean deleteTrack(int id) {
        return trackDAO.delete(id);
    }

    public boolean updateTrack(Track track) {
        return trackDAO.update(track);
    }

    public TrackPrice findById(int idTrack) {
        TrackPrice trackPrice = transformTrackPrice(trackDAO.getTrackById(idTrack));
        priceProvider.setPriceToTrack(trackPrice);
        return trackPrice;
    }

    public List<TrackPrice> getTrackByMediaType(MediaType mediaType) {
        List<Track> trackList = trackDAO.getTrackByMediaType(mediaType);
        List<TrackPrice> trackPriceList = new ArrayList<>();
        trackList.forEach(f ->{
            TrackPrice trackPrice = transformTrackPrice(f);
            priceProvider.setPriceToTrack(trackPrice);
            trackPriceList.add(trackPrice);
        });
        return trackPriceList;
    }

    public List<TrackPrice> getTrackByYear(Year issueDate) {
        List<Track> trackList = trackDAO.getTrackByYear(issueDate);
        List<TrackPrice> trackPriceList = new ArrayList<>();
        trackList.forEach(f ->{
            TrackPrice trackPrice = transformTrackPrice(f);
            priceProvider.setPriceToTrack(trackPrice);
            trackPriceList.add(trackPrice);
        });
        return trackPriceList;
    }
    public List<TrackPrice> getAllTracks() {
        List<Track> trackList = trackDAO.getAllTracks();
        List<TrackPrice> trackPriceList = new ArrayList<>();
        trackList.forEach(f ->{
            TrackPrice trackPrice = transformTrackPrice(f);
            priceProvider.setPriceToTrack(trackPrice);
            trackPriceList.add(trackPrice);
        });
        return trackPriceList;
    }
    public List<Artist> getArtistsByTrack(int idTrack) {
        Track track = trackDAO.getTrackById(idTrack);
        List<Integer> idArtistList = track.getIdArtist();
        return artistDAO.getAllArtist().stream().filter(w -> idArtistList.contains(w.getIdArtist())).toList();
    }

    public List<TrackPrice> getTrackByDuration(TypeDuration typeDuration, Time duration) {
        List<Track> trackList = trackDAO.getTrackByDuration(typeDuration, duration);
        List<TrackPrice> trackPriceList = new ArrayList<>();
        trackList.forEach(f ->{
            TrackPrice trackPrice = transformTrackPrice(f);
            priceProvider.setPriceToTrack(trackPrice);
            trackPriceList.add(trackPrice);
        });
        return trackPriceList;
    }

    public TrackPrice transformTrackPrice(Track track){
        return new TrackPrice(track.getIdTrack(),track.getTittle(),track.getAlbum(),track.getIssueDate(),track.getMediaType(),track.getDuration(),0,track.getIdArtist());
    }

}
