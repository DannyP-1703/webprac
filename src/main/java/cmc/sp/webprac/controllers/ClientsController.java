package cmc.sp.webprac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @GetMapping("")
    String clients(Model model) {
        return "clients";
    }

}
