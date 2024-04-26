package org.example.trackartist.price;

import org.example.trackartist.classes.TrackPrice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PriceProvider {
    private String priceUrl;
    private RestClient restClient;
    public PriceProvider() {
        var baseUrl = "http://localhost:10001/pricing";
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public void setPriceToTrack(TrackPrice track){
        ResponseEntity<Double> response = restClient.get()
                .uri("/{id}", track.getIdTrack())
                .retrieve()
                .toEntity(Double.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            double price = response.getBody();
            track.setPrice(price);
        }
    }
}
