package cmc.sp.webprac.controllers;

import cmc.sp.webprac.dao.ServiceDAO;
import cmc.sp.webprac.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ServicesController {

    @Autowired
    ServiceDAO serviceDAO;

    @GetMapping("/services")
    String services(Model model) {
        model.addAttribute("services", serviceDAO.getAll());
        return "services";
    }

    @GetMapping("/add_service")
    String addServices(Model model) {
        model.addAttribute("service", new Service());
        return "add_service";
    }

    @GetMapping("/service")
    String serviceById(@RequestParam Integer service_id, Model model) {
        var service = serviceDAO.getById(service_id);
        if (service == null) {
            return "error";
        }
        model.addAttribute("service", service);
        return "service";
    }

    @PostMapping("/updateService")
    public String updateService(@ModelAttribute Service service, @RequestParam Integer service_id) {
        service = serviceDAO.update(service);
        return String.format("redirect:/service?service_id=%d", service.getService_id());
    }

    @PostMapping("/saveService")
    public String saveService(@ModelAttribute Service service) {
        serviceDAO.save(service);
        return String.format("redirect:/service?service_id=%d", service.getService_id());
    }

}
