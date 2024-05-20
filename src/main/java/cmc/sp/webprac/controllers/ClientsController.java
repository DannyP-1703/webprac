package cmc.sp.webprac.controllers;

import cmc.sp.webprac.dao.EntityClientDAO;
import cmc.sp.webprac.dao.IndividualClientDAO;
import cmc.sp.webprac.models.Client;
import cmc.sp.webprac.models.EntityClient;
import cmc.sp.webprac.models.IndividualClient;
import cmc.sp.webprac.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ClientsController {

    @Autowired
    IndividualClientDAO individualClient;

    @Autowired
    EntityClientDAO entityClient;

    @GetMapping("/clients")
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

    @GetMapping("/client")
    String client(@RequestParam Integer client_id, @RequestParam Integer client_type, Model model) {
        if (client_type == 0) {
            var client = individualClient.getById(client_id);
            model.addAttribute("client", client);
            model.addAttribute("client_type", 0);
            return "individual_client";
        } else {
            var client = entityClient.getById(client_id);
            model.addAttribute("client", client);
            model.addAttribute("client_type", 1);
            return "entity_client";
        }
    }

    @PostMapping("/update_individual_client")
    String updateIndividualClient(@ModelAttribute IndividualClient client, Model model) {
        client = individualClient.update(client);
        return String.format("redirect:/client?client_id=%d&client_type=0", client.getClient_id());
    }

    @PostMapping("/update_entity_client")
    String updateEntityClient(@ModelAttribute EntityClient client, Model model) {
        client = entityClient.update(client);
        return String.format("redirect:/client?client_id=%d&client_type=1", client.getClient_id());
    }

    @GetMapping("/add_client")
    String addClient(Model model) {
        model.addAttribute("individual_client", new IndividualClient());
        model.addAttribute("entity_client", new EntityClient());
        return "add_client";
    }

    @PostMapping("/save_individual_client")
    String saveIndividualClient(@ModelAttribute IndividualClient client, Model model) {
        individualClient.save(client);
        return String.format("redirect:/client?client_id=%d&client_type=0", client.getClient_id());
    }

    @PostMapping("/save_entity_client")
    String saveEntityClient(@ModelAttribute EntityClient client, Model model) {
        entityClient.save(client);
        return String.format("redirect:/client?client_id=%d&client_type=1", client.getClient_id());
    }

    @GetMapping("/remove_client")
    String removeClient(@RequestParam Integer client_id, @RequestParam Integer client_type, Model model) {
        if (client_type == 0) {
            individualClient.deleteById(client_id);
        } else {
            entityClient.deleteById(client_id);
        }
        return "redirect:/clients";
    }

}
