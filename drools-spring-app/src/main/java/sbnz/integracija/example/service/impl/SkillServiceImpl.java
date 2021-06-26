package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Skill;
import sbnz.integracija.example.repository.CourseRepository;
import sbnz.integracija.example.repository.SkillRepository;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.SkillService;

import java.util.Collection;

@Service
public class SkillServiceImpl implements SkillService {

    SkillRepository skillRepository;


    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Collection<Skill> getAll() {
        return this.skillRepository.findAll();
    }
}
