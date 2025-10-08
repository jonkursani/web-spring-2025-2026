package dev.jonkursani.firstproject;

import org.springframework.stereotype.Component;

@Component
public class SwimmingCoach implements Coach {
    public SwimmingCoach() {
        System.out.println("SwimmingCoach constructor called");
    }

    @Override
    public String getDailyWorkout() {
        return "Swim freestyle for 10 minutes";
    }
}
