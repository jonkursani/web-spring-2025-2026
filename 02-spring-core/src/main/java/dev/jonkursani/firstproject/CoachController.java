package dev.jonkursani.firstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class CoachController {
//    Coach coach = new SoccerCoach();
//    Dependency Injection ose inicializim nepermjet konstruktorit

    private Coach coach;
    // @Autowired // @Autowired annotation tells Spring to inject a dependency
    // @Qualifier - i tregon cilin implementim me perdor
    public CoachController(@Qualifier("swimmingCoach") Coach coach) {
//    public CoachController(Coach coach) { // @Primary
        this.coach = coach;
    }

    // Hapat se si inicializohet (DI) nga Spring Boot
    // Coach coach = new SoccerCoach();
    // CoachController cc = new CoachController(coach)


    @GetMapping("/daily-workout") // route ose endpoint
    public String dailyWorkout() {
        return coach.getDailyWorkout();
    }
}
