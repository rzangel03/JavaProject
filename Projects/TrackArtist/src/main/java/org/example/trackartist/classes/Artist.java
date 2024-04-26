package org.example.trackartist.classes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Artist {
    private int idArtist;
    private String name;
    private List<Integer> idTracks;

    @Builder
    public Artist(String name, List<Integer> idTracks) {
        this(0, name,idTracks);
    }
    @Builder
    public Artist(int idArtist, String name, List<Integer> idTracks) {
        this.idArtist = idArtist;
        this.name = name;
        this.idTracks = idTracks;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "idArtist=" + idArtist +
                ", name='" + name + '\'' +
                ", idTracks=" + idTracks +
                '}';
    }
}
