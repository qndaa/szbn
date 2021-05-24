package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.enums.CategoryOfUser;
import sbnz.integracija.example.enums.TypeOfUser;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.SubscriberService;

import java.time.LocalDateTime;

@Service
public class SubscriberServiceImp implements SubscriberService {


    SubscriberRepository subscriberRepository;
    KieSession kieSession;

    @Autowired
    public SubscriberServiceImp(KieSession kieSession, SubscriberRepository subscriberRepository) {
        this.kieSession = kieSession;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Subscriber save(Subscriber subscriberRequest) {
        // throw IllegalArgumentException
        if (subscriberRepository.findByUsername(subscriberRequest.getUsername()) != null) {
            throw new IllegalArgumentException("Subscriber with username already exists!");
        }

        subscriberRequest.setBlocked(false);
        subscriberRequest.setDateOfRegistration(LocalDateTime.now());
        subscriberRequest.setDiscount(0.0);
        subscriberRequest.setTypeOfUser(TypeOfUser.SUBSCRIBER);
        subscriberRequest.setCategoryOfUser(CategoryOfUser.NO_CATEGORY);


        kieSession.insert(subscriberRequest);
        kieSession.getAgenda().getAgendaGroup("set-category").setFocus();
        kieSession.fireAllRules();
        System.out.println(subscriberRequest.getCategoryOfUser());

        return subscriberRepository.save(subscriberRequest);
    }
}
