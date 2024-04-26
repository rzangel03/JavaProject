package org.example.pricing.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/pricing")
public class PricingController {

    private double lowerLimit = 1;
    private double higherLimit = 10;

    @GetMapping("/{id}")
    public double getRatingForCourse(@PathVariable("id") int id) {
        double rating = ThreadLocalRandom.current().nextDouble(lowerLimit, higherLimit);

        return Double.parseDouble(String.format("%.2f", rating));
    }
}
