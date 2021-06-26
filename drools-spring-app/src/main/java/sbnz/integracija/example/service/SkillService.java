package sbnz.integracija.example.service;

import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Skill;

import java.util.Collection;


public interface SkillService {
    Collection<Skill> getAll();
}
