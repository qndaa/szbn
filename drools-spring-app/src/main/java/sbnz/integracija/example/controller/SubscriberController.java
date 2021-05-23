package sbnz.integracija.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.service.SubscriberService;

@RestController
@RequestMapping(value = "/users")
public class SubscriberController {

    SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Subscriber> registrationSubscriber(@RequestBody Subscriber subscriber) {
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
