package sbnz.integracija.example.service;

import sbnz.integracija.example.enums.CategoryOfUser;
import sbnz.integracija.example.facts.Subscriber;

import java.util.UUID;

public interface SubscriberService {


    Subscriber save(Subscriber subscriberRequest);

    CategoryOfUser updateCategory(UUID fromString);
}
