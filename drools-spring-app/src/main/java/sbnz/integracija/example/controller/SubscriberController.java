package sbnz.integracija.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.enums.CategoryOfUser;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.service.SubscriberService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class SubscriberController {

    SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Subscriber> registrationSubscriber(@RequestBody Subscriber subscriberRequest) {
        Subscriber subscriber = null;
        try {
            subscriber = subscriberService.save(subscriberRequest);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Subscriber>(subscriber, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryOfUser> updateCategory(@PathVariable String id) {
        return new ResponseEntity<CategoryOfUser>(subscriberService.updateCategory(UUID.fromString(id)), HttpStatus.OK);
    }


}
