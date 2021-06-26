package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.facts.Skill;

import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
}
