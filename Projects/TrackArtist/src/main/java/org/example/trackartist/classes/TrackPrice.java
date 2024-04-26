package org.example.trackartist.classes;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class TrackPrice extends Track {
    private double price;
    public TrackPrice(int idTrack,String tittle, String album, LocalDate issueDate, MediaType mediaType, Time duration, double price, List<Integer> idArtist) {
        super(tittle, album, issueDate, mediaType, duration,idArtist);
        this.setIdTrack(idTrack);
        this.price = price;
    }

}
