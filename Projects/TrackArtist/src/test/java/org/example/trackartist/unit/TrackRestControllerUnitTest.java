package org.example.trackartist.unit;

import org.example.trackartist.classes.Artist;
import org.example.trackartist.classes.MediaType;
import org.example.trackartist.classes.Track;
import org.example.trackartist.classes.TrackPrice;
import org.example.trackartist.controller.RestResultWrapper;
import org.example.trackartist.controller.TrackController;
import org.example.trackartist.controller.UriCreator;
import org.example.trackartist.service.TrackService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.example.trackartist.price.PriceProvider;

import java.net.URI;
import java.util.List;

import static org.example.trackartist.InMemoryInitializer.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class TrackRestControllerUnitTest {
    @InjectMocks
    private TrackController trackController;
    @Mock
    private PriceProvider priceProvider;
    @Mock
    private TrackService trackService;
    @Mock
    private UriCreator uriCreator;



    List<Track> track = List.of(
            Track.builder().
                    tittle(randomTittle()).
                    album(randomAlbum()).
                    idArtist(randomIdArtist()).
                    issueDate(randomDate()).
                    mediaType(MediaType.Flac).
                    duration(randomDuration()).build()
    );
    private final int goodTrackId = 1;
    private final int badTrackId = 10000;
    private TrackPrice transformTrackPrice(Track track){
        return new TrackPrice(track.getIdTrack(),track.getTittle(),track.getAlbum(),track.getIssueDate(),track.getMediaType(),track.getDuration(),0,track.getIdArtist());
    }
    @Test
    public void testGetOneTrackGoodJson() throws Exception {

        Mockito.when(trackService.findById(goodTrackId)).thenReturn(transformTrackPrice(track.getFirst()));
        ResponseEntity<?> response = trackController.getTrack(goodTrackId);
        assertEquals(200, response.getStatusCodeValue());

        System.out.println(response.getBody());
        Mockito.verify(trackService).findById(goodTrackId);
    }
    @Test
    public void testGetOneTrackBadId() throws Exception {
        Mockito.when(trackService.findById(badTrackId)).thenReturn(null);
        var status = HttpStatus.BAD_REQUEST;
        var message = "Track with id: " + badTrackId + " not found";

        var pd = ProblemDetail.forStatusAndDetail(status, message);
        Mockito.when(uriCreator.getProblemDetail(status, message)).thenReturn(pd);

        ResponseEntity<?> response = trackController.getTrack(badTrackId);
        assertEquals(400, response.getStatusCodeValue());


        Mockito.verify(uriCreator).getProblemDetail(status, message);
        Mockito.verify(trackService).findById(badTrackId);
    }
    @Test
    public void testAddTrackGood() throws Exception {
       Track track1 = Track.builder().
               tittle(randomTittle()).
               album(randomAlbum()).
               idArtist(randomIdArtist()).
               issueDate(randomDate()).
               mediaType(MediaType.Flac).
               duration(randomDuration()).build();
       track1.setIdTrack(1);

        URI newTrackURI = new URI("http://localhost:8080/track");

        Mockito.when(trackService.addTrack(track1)).thenReturn(track1);
        Mockito.when(uriCreator.getURI(1)).thenReturn(newTrackURI);

        ResponseEntity<?> response = trackController.addTrack(track1);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(newTrackURI, response.getHeaders().getLocation());


        Mockito.verify(trackService).addTrack(track1);
        Mockito.verify(uriCreator).getURI(1);

    }
    @Test
    public void testUpdateTrackGood() throws Exception {
        Track s = track.get(0);
        s.setIdTrack(goodTrackId);

        Mockito.when(trackService.updateTrack(s)).thenReturn(true);

        ResponseEntity<?> response = trackController.updateTrack(s);
        assertEquals(204, response.getStatusCodeValue());

        Mockito.verify(trackService).updateTrack(s);
    }
    @Test
    public void testUpdateTrackBad() throws Exception {
        Track s = track.get(0);
        s.setIdTrack(badTrackId);

        Mockito.when(trackService.updateTrack(s)).thenReturn(false);

        ResponseEntity<?> response = trackController.updateTrack(s);
        assertEquals(418, response.getStatusCodeValue());

        Mockito.verify(trackService).updateTrack(s);

    }
    @Test
    public void testDeleteTrackGood() throws Exception {

        Mockito.when(trackService.deleteTrack(goodTrackId)).thenReturn(true);

        ResponseEntity<?> response = trackController.deleteTrack(goodTrackId);
        assertEquals(204, response.getStatusCodeValue());

        Mockito.verify(trackService).deleteTrack(goodTrackId);
    }
    @Test
    public void testDeleteTrackBad() throws Exception {
        Mockito.when(trackService.deleteTrack(badTrackId)).thenReturn(false);

        ResponseEntity<?> response = trackController.deleteTrack(badTrackId);
        assertEquals(418, response.getStatusCodeValue());

        Mockito.verify(trackService).deleteTrack(badTrackId);
    }
}
