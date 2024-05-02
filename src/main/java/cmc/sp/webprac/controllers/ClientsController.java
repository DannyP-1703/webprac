package cmc.sp.webprac.controllers;

import cmc.sp.webprac.dao.EntityClientDAO;
import cmc.sp.webprac.dao.IndividualClientDAO;
import cmc.sp.webprac.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    IndividualClientDAO individualClient;

    @Autowired
    EntityClientDAO entityClient;

    @GetMapping("")
    String clients(Model model) {
        var all_clients = new ArrayList<Client>();
        for (var client : individualClient.getAll()) {
            all_clients.add(new Client(client));
        }
        for (var client : entityClient.getAll()) {
            all_clients.add(new Client(client));
        }
        model.addAttribute("clients", all_clients);
        return "clients";
    }

}
