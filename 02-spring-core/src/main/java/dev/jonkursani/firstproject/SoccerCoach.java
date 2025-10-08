package dev.jonkursani.firstproject;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// na mundeson qe me hy ne IOC edhe me u menaxhu nga Spring Boot
@Component
//@Service
//@Repository
//@Controller
//@Primary // i tregon se duhet me perdor ket implementim, me mire perdorni @Qualifier
//@Lazy
public class SoccerCoach implements Coach {
    public SoccerCoach() {
        System.out.println("SoccerCoach constructor called");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice sprints for 15 minutes";
    }
}










