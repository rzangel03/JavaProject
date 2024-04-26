package org.example.trackartist.controller;

import org.example.trackartist.classes.TrackPrice;
import org.example.trackartist.service.ArtistService;
import org.example.trackartist.classes.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping()
    public ResponseEntity<?> getAllArtists() {
        List<Artist> artist = artistService.getAllArtist();
        if (artist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No artists");
        }
        return ResponseEntity.ok(artist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtist(@PathVariable("id") int id) {
        Artist artist = artistService.getById(id);
        if (artist == null) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No artist with id: " + id);
        }
        return ResponseEntity.ok(artist);
    }

    @GetMapping("/getTracksByArtist/{idArtist}")
    public ResponseEntity<?> getTracksByArtist(@PathVariable("idArtist") int idArtist) {
        List<TrackPrice> track = artistService.getTracksByArtist(idArtist);
        if (track.isEmpty()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No track with this Artist: " + idArtist);
        }
        return ResponseEntity.ok(track);
    }

    @GetMapping("/getArtistByName/{name}")
    public ResponseEntity<?> getTrackByMediaType(@PathVariable("name") String name) {
        Artist artist = artistService.getArtistByName(name);
        if (artist == null) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No artist with Name: " + name);
        }
        return ResponseEntity.ok(artist);
    }

    @PostMapping
    public ResponseEntity<?> addArtist(@RequestBody Artist artist) {
        Artist newArtist = artistService.addArtist(artist);
        URI newUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(artist.getIdArtist())
                .toUri();

        return ResponseEntity.created(newUri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable("id") int id) {
        boolean result = artistService.deleteArtist(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No artist with id:" + id);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateArtist(@RequestBody Artist artist) {
        boolean result = artistService.updateArtist(artist);
        if (!result) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No track with id: " + artist.getIdArtist());
        }
        return ResponseEntity.noContent().build();
    }
}
