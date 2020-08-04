package com.application.demo.controllers;

import com.application.demo.entities.Contacts;
import com.application.demo.exceptions.ContactNotFoundExpception;
import com.application.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// indicates that the data returned by each method will be writeen straight into the response body
// instead of rendering template
@RequestMapping("/api/v1")
public class ContactController {

    @Autowired
    private UserRepository userRepository;

    public ContactController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/contacts")
    public List<Contacts> all() {
        return userRepository.findAll();
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contacts> getContactById(@PathVariable (value = "id") int userId) throws ContactNotFoundExpception  {
        Contacts contact = userRepository.findById(userId).orElseThrow(() -> new ContactNotFoundExpception(userId));
        return ResponseEntity.ok().body(contact);
    }

    @PostMapping("/contacts")
    public Contacts createContact(@RequestBody Contacts newContact) {
        return userRepository.save(newContact);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contacts> updateContact(@PathVariable(value="id") int userId,@RequestBody Contacts contactDetails) throws ContactNotFoundExpception {
       Contacts contact = userRepository.findById(userId).orElseThrow( () -> new ContactNotFoundExpception(userId));

       contact.setFirstName(contactDetails.getFirstName());
       contact.setLastName(contactDetails.getLastName());
       final Contacts updatedContact = userRepository.save(contact);
       return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/contacts/{id}")
   public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws Exception {
        Contacts contact = userRepository.findById(userId).orElseThrow( () -> new ContactNotFoundExpception(userId));

        userRepository.delete(contact);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
