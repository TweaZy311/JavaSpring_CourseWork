package org.example.controllers;

import org.example.entities.Service;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.example.services.ApplicationService;
import org.example.services.ServiceService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final ApplicationService applicationService;
    private final ServiceService serviceService;

    @Autowired
    public AdminController(UserService userService, ApplicationService applicationService, ServiceService serviceService) {
        this.userService = userService;
        this.applicationService = applicationService;
        this.serviceService = serviceService;
    }

    @GetMapping("/panel")
    public String getData(Model model){
        model.addAttribute("Applications", applicationService.findAll());
        model.addAttribute("Users", userService.findAll());
        model.addAttribute("Services", serviceService.findAll());
        return "admin/panel";
    }

    @GetMapping("/panel/user_edit/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRole());

        return "admin/userEdit";
    }
    @PostMapping("/panel/user_edit/{id}")
    public String updateUser(@PathVariable Integer id,
                             @RequestParam String username,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String role) {
        Optional<User> optionalPerson = userService.findById(id);
        User existingUser = optionalPerson.get();
        existingUser.setUsername(username);
        existingUser.setFirstName(firstName);
        existingUser.setLastName(lastName);
        existingUser.setRole(role);
        userService.save(existingUser);
        return "redirect:/admin/panel";
    }


    @GetMapping("/panel/service_edit/{service}")
    public String serviceEditForm(@PathVariable Service service, Model model) {
        model.addAttribute("user", service);
        return "admin/serviceEdit";
    }
    @PostMapping("/panel/service_edit/{id}")
    public String updateService(@PathVariable Integer id,
                             @RequestParam String name,
                             @RequestParam Integer price) {
        Optional<Service> optionalService = serviceService.findById(id);
        Service existingService = optionalService.get();
        existingService.setName(name);
        existingService.setPrice(price);
        serviceService.add(existingService);
        return "redirect:/admin/panel";
    }
//
//    @DeleteMapping("/panel")
//    public String deleteService(@RequestParam(required = false) Integer id) {
//        Optional<Service> optionalService = serviceService.findById(id);
//        if (optionalService.isPresent()){
//            serviceService.deleteById(id);
//        }
//        return "redirect:/admin/panel";
//    }

}
