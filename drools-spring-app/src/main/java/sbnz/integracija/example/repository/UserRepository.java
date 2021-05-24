package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.facts.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
