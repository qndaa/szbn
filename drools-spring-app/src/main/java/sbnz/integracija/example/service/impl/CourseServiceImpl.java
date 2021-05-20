package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.repository.CourseRepository;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.CourseService;

import java.util.Collection;

@Service
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;
    SubscriberRepository subscriberRepository;
    KieSession kieSession;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, KieSession kieSession, SubscriberRepository subscriberRepository) {
        this.courseRepository = courseRepository;
        this.kieSession = kieSession;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Collection<Course> getAll() {
        Collection<Course> courses = courseRepository.findAll();
        courses.forEach(c -> kieSession.insert(c));
        subscriberRepository.findAll().forEach(s -> kieSession.insert(s));
        kieSession.fireAllRules();
        return courses;
    }
}
