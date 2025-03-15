package pl.yummy.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";

    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage() {
        return "home"; // widok strony głównej (np. home.html)
    }

}

    /*
    HomeController – odpowiedzialny za stronę główną aplikacji, wyświetlanie aktualnych ofert, promocji czy nowości.
    */