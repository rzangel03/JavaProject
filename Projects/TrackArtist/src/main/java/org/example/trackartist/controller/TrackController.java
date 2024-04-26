package org.example.trackartist.controller;


import org.example.trackartist.classes.*;
import org.example.trackartist.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Time;
import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    private TrackService trackService;
    @Autowired
    private UriCreator uriCreator;



    @GetMapping()
    public ResponseEntity<?> getAllTracks() {
        List<TrackPrice> artist = trackService.getAllTracks();
        if (artist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No artists");
        }
        return ResponseEntity.ok(artist);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrack(@PathVariable("id") int id) {
        TrackPrice track = trackService.findById(id);
        if (track == null) {
            var pd = uriCreator.getProblemDetail(HttpStatus.BAD_REQUEST,
                    "Track with id: " + id + " not found");
            return ResponseEntity.badRequest()
                    .body(RestResultWrapper.ofError(pd));
        }
        return ResponseEntity.ok(track);
    }

    @GetMapping("/getTrackByMediaType/{mediaType}")
    public ResponseEntity<?> getTrackByMediaType(@PathVariable("mediaType") MediaType mediaType) {
        List<TrackPrice> track = trackService.getTrackByMediaType(mediaType);
        if (track.isEmpty()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No track with Media Type: " + mediaType);
        }
        return ResponseEntity.ok(track);
    }

    @GetMapping("/getTrackByYear/{issueDate}")
    public ResponseEntity<?> getTrackByYear(@PathVariable("issueDate") Year issueDate) {
        List<TrackPrice> track = trackService.getTrackByYear(issueDate);
        if (track.isEmpty()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No track with Year: " + issueDate);
        }
        return ResponseEntity.ok(track);
    }

    @GetMapping("/getArtistsByTrack/{idTrack}")
    public ResponseEntity<?> getArtistsByTrack(@PathVariable("idTrack") int idTrack) {
        List<Artist> track = trackService.getArtistsByTrack(idTrack);
        if (track.isEmpty()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No artist with this Track: " + idTrack);
        }
        return ResponseEntity.ok(track);
    }

    @GetMapping("/getTrackByDuration")
    public ResponseEntity<?> getTrackByDuration(@RequestParam TypeDuration typeDuration, @RequestParam Time duration) {
        if (!typeDuration.toString().isEmpty() && !duration.toString().isEmpty()) {
            List<TrackPrice> track = trackService.getTrackByDuration(typeDuration, duration);
            if (track.isEmpty()) {
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No track with this TypeDuration: " + typeDuration);
            }
            return ResponseEntity.ok(track);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No track with this TypeDuration: " + typeDuration);
    }

    @PostMapping
    public ResponseEntity<?> addTrack(@RequestBody Track track) {
        Track newTrack = trackService.addTrack(track);
        URI newResource = uriCreator.getURI(newTrack.getIdTrack());

        return ResponseEntity.created(newResource).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) {
        boolean result = trackService.deleteTrack(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No track with id:" + id);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateTrack(@RequestBody Track track) {
        boolean result = trackService.updateTrack(track);
        if (!result) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No track with id: " + track.getIdTrack());
        }
        return ResponseEntity.noContent().build();
    }


}
