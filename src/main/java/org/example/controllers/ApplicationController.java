package org.example.controllers;


import org.example.entities.Application;
import org.example.entities.Service;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.example.services.ApplicationService;
import org.example.services.EmailService;
import org.example.services.ServiceService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ApplicationController {
    private final ApplicationService applicationService;
    private final UserService userService;
    private final EmailService emailService;
    private final ServiceService serviceService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, UserService userService, EmailService emailService, ServiceService serviceService) {
        this.applicationService = applicationService;
        this.userService = userService;
        this.emailService = emailService;
        this.serviceService = serviceService;
    }

    @GetMapping("/application")
    public String getApplicationPage(Model model, Application application) {
        List<Service> services = serviceService.findAll();
        model.addAttribute("application", application);
        model.addAttribute("services", services);
        return "applicationPage";
    }

    @PostMapping("/application")
    public String createApplication(@ModelAttribute("application") Application application,
                                    Principal principal) {
        String username = principal.getName();
        User existingUser = userService.findByUsername(username);
        application.setUser(existingUser);
        applicationService.saveApplication(application);
        emailService.sendEmail(existingUser.getUsername(),
                "Созданная заявка",
                "Уважаемый " + existingUser.getFirstName() + " " + existingUser.getLastName() +
                ", ваша заявка под номером " + application.getId() + " на услугу " + application.getName() +
                " на следующую дату: " + application.getDate() + " была принята. Ожидаем вас в указанный день!");
        return "redirect:/application";
    }

}
