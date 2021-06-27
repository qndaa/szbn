package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.Subscriber;

import java.util.UUID;

public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {
    Subscriber findByUsername(String username);

}
