package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.events.CourseEnrollmentEvent;
import sbnz.integracija.example.events.CourseSearchEvent;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.facts.dto.CourseSearchDTO;
import sbnz.integracija.example.repository.CourseRepository;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.CourseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

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

    @Override
    public Collection<Course> search(CourseSearchDTO searchDTO) {
        Collection<Course> courses = new ArrayList<>();
        CourseSearchEvent searchEvent = new CourseSearchEvent(searchDTO.getUserId());
        kieSession.insert(searchEvent);
        courseRepository.findAll().forEach(c -> kieSession.insert(c));
        QueryResults results = kieSession.getQueryResults(
                "courseSearch", searchDTO.getTitle(), searchDTO.getArea(), searchDTO.getAuthor(), searchDTO.getGrade(),
                searchDTO.getPrice(), searchDTO.getYear(), searchDTO.getLevel(), searchDTO.getPopularity());
        for(QueryResultsRow row : results) courses.add((Course) row.get("$c"));
        return courses;
//        return null;
    }

    @Override
    public void enroll(UUID userId, UUID courseId) {
        Subscriber subscriber = subscriberRepository.findById(userId).get();
        if(subscriber.isBlocked())
            throw new IllegalStateException("Blocked user cannot enroll in courses!");
        CourseEnrollmentEvent enrollment = new CourseEnrollmentEvent(userId, courseId);
        kieSession.insert(enrollment);
        kieSession.insert(subscriber);
//        kieSession.getAgenda().getAgendaGroup("malware").setFocus();
        kieSession.fireAllRules();
        subscriberRepository.save(subscriber);
//        kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();

    }
}
