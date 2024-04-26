package org.example.trackartist.classes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class Track {
    private int idTrack;
    private String tittle;
    private String album;
    private LocalDate issueDate;
    private MediaType mediaType;
    private Time duration;
    private List<Integer> idArtist;
    @Builder
    public Track(int idTrack, String tittle, String album, LocalDate issueDate, MediaType mediaType, Time duration, List<Integer> idArtist) {
        this.idTrack = idTrack;
        this.tittle = tittle;
        this.album = album;
        this.issueDate = issueDate;
        this.mediaType = mediaType;
        this.duration = duration;
        this.idArtist = idArtist;
    }

    public Track() {
    }


    @Override
    public String toString() {
        return "Track{" +
                "idTrack=" + idTrack +
                ", tittle='" + tittle + '\'' +
                ", album='" + album + '\'' +
                ", issueDate=" + issueDate +
                ", mediaType=" + mediaType +
                ", duration=" + duration +
                '}';
    }

    public Track(String tittle, String album, LocalDate issueDate, MediaType mediaType, Time duration, List<Integer> idArtist) {
        this(0, tittle, album, issueDate, mediaType, duration,idArtist);
    }
}
