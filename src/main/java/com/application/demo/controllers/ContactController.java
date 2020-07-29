package com.application.demo.controllers;

import com.application.demo.entities.Contacts;
import com.application.demo.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping(path="/demo")

public class ContactController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser( @RequestParam int id, @RequestParam String firstName, @RequestParam String lastName) {
        Contacts contacts = new Contacts();
        contacts.setId(id);
        contacts.setFirst_name(firstName);
        contacts.setLast_name(lastName);
        userRepository.save(contacts);
        return "Saved";
    }

//    @PostMapping(path="/edit")
//    public @ResponseBody String editUser(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName) {
//
//    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Contacts> getAllContacts() {
        return userRepository.findAll();
    }
}
