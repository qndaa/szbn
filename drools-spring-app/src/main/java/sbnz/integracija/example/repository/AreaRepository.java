package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbnz.integracija.example.facts.Area;

import java.util.UUID;

@Repository
public interface AreaRepository extends JpaRepository<Area, UUID> {


}

