package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.facts.Area;

import java.util.UUID;

public interface AreaRepository extends JpaRepository<Area, UUID> {
}
