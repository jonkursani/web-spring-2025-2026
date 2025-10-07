package dev.jonkursani.firstproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mixed")
public class MixedController {
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/posts")
    @ResponseBody // returns JSON mos kthe HTML template
    public String apiData() {
        return "Api data: posts";
    }
}
