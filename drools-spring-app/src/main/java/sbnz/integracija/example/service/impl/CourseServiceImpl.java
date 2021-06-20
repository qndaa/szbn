package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.events.CourseEnrollmentEvent;
import sbnz.integracija.example.events.CourseSearchEvent;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.PrerequisiteCourses;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.facts.dto.CourseSearchDTO;
import sbnz.integracija.example.repository.CourseRepository;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.CourseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;
    SubscriberRepository subscriberRepository;
    KieSession kieSession;
    KieContainer kieContainer;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, KieSession kieSession, SubscriberRepository subscriberRepository, KieContainer kieContainer) {
        this.courseRepository = courseRepository;
        this.kieSession = kieSession;
        this.subscriberRepository = subscriberRepository;
        this.kieContainer = kieContainer;
    }

    @Override
    public Collection<Course> getAll() {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        Collection<Course> courses = courseRepository.findAll();
        courses.forEach(kieSession::insert);
        subscriberRepository.findAll().forEach(kieSession::insert);
        kieSession.fireAllRules();
        courses.forEach(c -> courseRepository.save(c));
        kieSession.dispose();
        return courses;
    }

    @Override
    public Collection<Course> search(CourseSearchDTO searchDTO) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        Collection<Course> courses = new ArrayList<>();
        CourseSearchEvent searchEvent = new CourseSearchEvent(searchDTO.getUserId());
        kieSession.insert(searchEvent);
        courseRepository.findAll().forEach(kieSession::insert);
        QueryResults results = kieSession.getQueryResults(
                "courseSearch", searchDTO.getTitle(), searchDTO.getArea(), searchDTO.getAuthor(), searchDTO.getGrade(),
                searchDTO.getPrice(), searchDTO.getYear(), searchDTO.getLevel(), searchDTO.getPopularity());
        for(QueryResultsRow row : results) courses.add((Course) row.get("$c"));
        kieSession.dispose();
        return courses;
//        return null;
    }

    @Override
    public void enroll(UUID userId, UUID courseId) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
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
        kieSession.dispose();

    }

    @Override
    public Collection<Course> listPrerequisites(UUID course) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        Collection<PrerequisiteCourses> courses = new ArrayList<>();
        Collection<Course> allCourses = courseRepository.findAll();
        allCourses.forEach(c -> {
            kieSession.insert(c);
            c.getPreconditions().forEach(pc -> courses.add(new PrerequisiteCourses(pc.getCourseId(), c.getCourseId())));
        });
        courses.forEach(kieSession::insert);
        kieSession.setGlobal("courseId", course);
        kieSession.getAgenda().getAgendaGroup("prereq").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return allCourses.stream().filter(Course::isPrecondition).collect(Collectors.toList());
    }
}
