package sbnz.integracija.example.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Subscriber;

@Service
public class DiscountService {
    private final KieContainer kieContainer;

    @Autowired
    public DiscountService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Subscriber getDiscountBasedOnUser(Subscriber subscriber) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(subscriber);
        kieSession.fireAllRules();
        kieSession.dispose();
        return subscriber;
    }
}
