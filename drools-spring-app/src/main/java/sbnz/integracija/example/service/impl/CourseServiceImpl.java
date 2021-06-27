package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.CourseSearch;
import sbnz.integracija.example.events.CourseEnrollmentEvent;
import sbnz.integracija.example.events.CourseSearchEvent;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.PrerequisiteCourses;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.facts.Teacher;
import sbnz.integracija.example.facts.dto.CourseSearchDTO;
import sbnz.integracija.example.repository.CourseRepository;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.CourseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;
    SubscriberRepository subscriberRepository;
    KieSession cepSession;
    KieContainer kieContainer;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, KieSession cepSession, SubscriberRepository subscriberRepository, KieContainer kieContainer) {
        this.courseRepository = courseRepository;
        this.cepSession = cepSession;
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
        CourseSearchEvent searchEvent = new CourseSearchEvent(searchDTO.getUserId());
        cepSession.insert(searchEvent);
        Collection<Subscriber> subs = subscriberRepository.findAll();
        subs.forEach(cepSession::insert);
        cepSession.getAgenda().getAgendaGroup("malware").setFocus();
        cepSession.fireAllRules();
        subs.forEach(subscriberRepository::save);
        if(subscriberRepository.findById(searchDTO.getUserId()).get().isBlocked())
            return new ArrayList<>();
        courseRepository.findAll().forEach(kieSession::insert);
        CourseSearch search = new CourseSearch();
        kieSession.insert(search);
//        QueryResults results = kieSession.getQueryResults(
//                "courseSearch", searchDTO.getTitle(), searchDTO.getArea(), searchDTO.getAuthor(), searchDTO.getGrade(),
//                searchDTO.getPrice(), searchDTO.getYear(), searchDTO.getLevel(), searchDTO.getPopularity());
//        for(QueryResultsRow row : results) courses.add((Course) row.get("$c"));
        kieSession.getAgenda().getAgendaGroup("search").setFocus();
        kieSession.setGlobal("_title", searchDTO.getTitle());
        kieSession.setGlobal("_area", searchDTO.getArea());
        kieSession.setGlobal("_price", searchDTO.getPrice());
        kieSession.setGlobal("_level", searchDTO.getLevel());
        kieSession.setGlobal("_year", searchDTO.getYear());
        kieSession.setGlobal("_popularity", searchDTO.getPopularity());
        kieSession.fireAllRules();
        kieSession.dispose();
        return search.getCourses();
//        return null;
    }

    @Override
    public void enroll(UUID userId, UUID courseId) {
//        KieSession kieSession = kieContainer.newKieSession("cepKsession");
//        Subscriber subscriber = subscriberRepository.findById(userId).get();
//        if(subscriber.isBlocked())
//            throw new IllegalStateException("Blocked user cannot enroll in courses!");
//        CourseEnrollmentEvent enrollment = new CourseEnrollmentEvent(userId, courseId);
//        kieSession.insert(enrollment);
//        kieSession.insert(subscriber);
////        kieSession.getAgenda().getAgendaGroup("malware").setFocus();
//        kieSession.fireAllRules();
//        subscriberRepository.save(subscriber);
////        kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
//        kieSession.dispose();
        Subscriber subscriber = subscriberRepository.findById(userId).get();
        if(subscriber.isBlocked())
            throw new IllegalStateException("Blocked user cannot enroll in courses!");
        CourseEnrollmentEvent enrollment = new CourseEnrollmentEvent(userId, courseId);
        cepSession.insert(enrollment);
        cepSession.insert(subscriber);
        cepSession.getAgenda().getAgendaGroup("malware").setFocus();
        cepSession.fireAllRules();
        subscriberRepository.save(subscriber);

        // dodati kurs u listu upisanih kurseva korisnika
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

    @Override
    public Collection<Course> getCoursesByAuthor(UUID authorId) {
        Collection<Course> courses = courseRepository.getCoursesByTeacher_UserId(authorId);
        if(courses == null)
            return new ArrayList<>();
        return courses;
    }

    @Override
    public Teacher getCourseTeacher(UUID courseId) {
        Teacher teacher = courseRepository.findById(courseId).get().getTeacher();
        teacher.setCourses(null);
        return teacher;
    }

    @Override
    public String hasPrecondition(UUID userId, UUID courseId) {
        System.out.println(courseId);
        System.out.println(userId);
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        Subscriber subscriber = subscriberRepository.findById(userId).get();
        subscriber.setCanSubscribe(false);
        kieSession.insert(subscriber);
        Collection<Course> allCourses = courseRepository.findAll();
        allCourses.forEach(c -> {
            kieSession.insert(c);
        });
        kieSession.setGlobal("id", courseId.toString());
        kieSession.setGlobal("subId", userId.toString());

        kieSession.setGlobal("status", "NO_PRECONDITION");
        kieSession.getAgenda().getAgendaGroup("precondition").setFocus();
        kieSession.fireAllRules();


        String ret = (String) kieSession.getGlobal("status");
        System.out.println(subscriber.canSubscribe);


        kieSession.dispose();

        return (subscriber.canSubscribe) ? "OK" :"NO_PRECONDITION";
    }

    @Override
    public Collection<Course> getCoursesByTeacher(String id) {
        Collection<Course> courses = this.courseRepository.getCoursesByTeacher(UUID.fromString(id));
        courses.forEach(c -> cepSession.insert(c));
        subscriberRepository.findAll().forEach(s -> cepSession.insert(s));
        cepSession.fireAllRules();
        courses.forEach(c -> courseRepository.save(c));
        return courses;
    }

    @Override
    public boolean deleteCourse(String id) {
        Course c = courseRepository.findById(UUID.fromString(id)).get();
        if (c == null) {
            return false;
        } else {
            c.setDeleted(true);
            this.courseRepository.save(c);
            return true;
        }
    }

    @Override
    public void save(Course course) {
        this.courseRepository.save(course);
    }
}
