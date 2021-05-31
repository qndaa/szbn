package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sbnz.integracija.example.facts.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT user FROM User user WHERE ?1 = user.username and ?2 = user.password")
    User findByUsernameAndPassword(String username, String password);
}
