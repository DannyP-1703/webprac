package cmc.sp.webprac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
    @GetMapping(value = { "/", "/index"})
    String index() {
        return "index";
    }
}
