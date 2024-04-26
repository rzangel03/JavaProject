package org.example.trackartist;

import org.example.trackartist.classes.Artist;
import org.example.trackartist.classes.MediaType;
import org.example.trackartist.classes.Track;
import org.example.trackartist.service.ArtistService;
import org.example.trackartist.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class InMemoryInitializer implements CommandLineRunner {

    @Autowired
    private TrackService trackService;
    @Autowired
    private ArtistService artistService;

    @Override
    public void run(String... args) throws Exception {

        var track = List.of(
                Track.builder().
                        tittle(randomTittle()).
                        album(randomAlbum()).
                        idArtist(randomIdArtist()).
                        issueDate(randomDate()).
                        mediaType(MediaType.Flac).
                        duration(randomDuration()).build(),
                Track.builder().
                        tittle(randomTittle()).
                        album(randomAlbum()).
                        idArtist(randomIdArtist()).
                        issueDate(randomDate()).
                        mediaType(MediaType.Mp3).
                        duration(randomDuration()).build(),
                Track.builder().
                        tittle(randomTittle()).
                        album(randomAlbum()).
                        idArtist(randomIdArtist()).
                        issueDate(randomDate()).
                        mediaType(MediaType.Ogg).
                        duration(randomDuration()).build(),
                Track.builder().
                        tittle(randomTittle()).
                        album(randomAlbum()).
                        idArtist(randomIdArtist()).
                        issueDate(randomDate()).
                        mediaType(MediaType.Wav).
                        duration(randomDuration()).build()
        );

        var artist = List.of(
                Artist.builder().
                        name(randomName()).
                        idTracks(randomIdTrack()).build(),
                Artist.builder().
                        name(randomName()).
                        idTracks(randomIdTrack()).build(),
                Artist.builder().
                        name(randomName()).
                        idTracks(randomIdTrack()).build(),
                Artist.builder().
                        name(randomName()).
                        idTracks(randomIdTrack()).build()
        );
        track.forEach(trackService::addTrack);
        artist.forEach(artistService::addArtist);
    }
    public static String randomTittle(){
        String[] tittle = {"Tittle 1",
                "Tittle 2",
                "Tittle 3",
                "Tittle 4",
                "Tittle 5",
                "Tittle 6",};
        int index = ThreadLocalRandom.current().nextInt(tittle.length);
        return tittle[index];
    }
    public static String randomAlbum(){
        String[] album = {"Album 1",
                "Album 2",
                "Album 3",
                "Album 4",
                "Album 5",
                "Album 6",};
        int index = ThreadLocalRandom.current().nextInt(album.length);
        return album[index];
    }
    public static List<Integer> randomIdArtist(){
        List<Integer> givenList = Arrays.asList(
                new Integer[] {1,2,3,4,5,6,7,8,9}
        );
        Collections.shuffle(givenList);
        int randomSeriesLength = 3;

        return givenList.subList(0, randomSeriesLength);
    }
    public static LocalDate randomDate(){
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2024, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }
    public static Time randomDuration(){
        int startSeconds = LocalTime.of(0,0,0).toSecondOfDay();
        int endSeconds = LocalTime.of(0,6,0).toSecondOfDay();
        int randomTime = ThreadLocalRandom
                .current()
                .nextInt(startSeconds, endSeconds);
        return Time.valueOf(LocalTime.ofSecondOfDay(randomTime));
    }
    public static String randomName(){
        String[] tittle = {"Miguel",
                "Angel",
                "Rosendo",
                "Audomaro",
                "Alberto"};
        int index = ThreadLocalRandom.current().nextInt(tittle.length);
        return tittle[index];
    }
    public static List<Integer> randomIdTrack(){
        List<Integer> givenList = Arrays.asList(
                new Integer[] {1,2,3,4}
        );
        Collections.shuffle(givenList);
        int randomSeriesLength = 2;

        return givenList.subList(0, randomSeriesLength);
    }


}
