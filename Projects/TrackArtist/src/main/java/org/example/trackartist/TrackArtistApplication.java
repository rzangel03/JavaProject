package org.example.trackartist;

import org.example.trackartist.classes.Artist;
import org.example.trackartist.classes.MediaType;
import org.example.trackartist.classes.Track;
import org.example.trackartist.service.ArtistService;
import org.example.trackartist.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TrackArtistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackArtistApplication.class, args);
    }

}
