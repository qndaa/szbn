package sbnz.integracija.example.service.impl;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.AuthorSubscriberReport;
import sbnz.integracija.example.enums.CategoryOfUser;
import sbnz.integracija.example.enums.TypeOfUser;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.facts.Teacher;
import sbnz.integracija.example.repository.AuthorRepository;
import sbnz.integracija.example.repository.CourseRepository;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.SubscriberService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubscriberServiceImp implements SubscriberService {


    SubscriberRepository subscriberRepository;
    CourseRepository courseRepository;
    KieSession kieSession;
    KieContainer kieContainer;
    AuthorRepository authorRepository;



    @Autowired
    public SubscriberServiceImp(KieSession kieSession, SubscriberRepository subscriberRepository, CourseRepository courseRepository, KieContainer kieContainer, AuthorRepository authorRepository) {
        this.kieSession = kieSession;
        this.subscriberRepository = subscriberRepository;
        this.courseRepository = courseRepository;
        this.kieContainer = kieContainer;
        this.authorRepository = authorRepository;
    }

    @Override
    public Subscriber save(Subscriber subscriberRequest) {
        // throw IllegalArgumentException
        if (subscriberRepository.findByUsername(subscriberRequest.getUsername()) != null) {
            throw new IllegalArgumentException("Subscriber with username already exists!");
        }
        KieSession kieSession = kieContainer.newKieSession("cepKsession");

        subscriberRequest.setBlocked(false);
        subscriberRequest.setDateOfRegistration(LocalDateTime.now());
        subscriberRequest.setDiscount(0.0);
        subscriberRequest.setTypeOfUser(TypeOfUser.SUBSCRIBER);
        subscriberRequest.setCategoryOfUser(CategoryOfUser.NO_CATEGORY);


        kieSession.insert(subscriberRequest);
        kieSession.getAgenda().getAgendaGroup("set-category").setFocus();
        kieSession.fireAllRules();
        System.out.println(subscriberRequest.getCategoryOfUser());
        kieSession.dispose();
        return subscriberRepository.save(subscriberRequest);
    }

    @Override
    public CategoryOfUser updateCategory(UUID id) {
        Subscriber subscriber = subscriberRepository.findById(id).get();
        if (subscriber != null) {


            kieSession.insert(subscriber);
            kieSession.getAgenda().getAgendaGroup("set-category").setFocus();
            int firedRule = kieSession.fireAllRules();

            subscriberRepository.save(subscriber);

            System.out.println("Fired rules: " + firedRule);
            System.out.println(subscriber.getCategoryOfUser());
            return subscriber.getCategoryOfUser();
        }
        return null;
    }

    @Override
    public Collection<Course> getEnrolledCourses(UUID id) {
        Optional<Subscriber> subscriber = subscriberRepository.findById(id);
        if(!subscriber.isPresent())
            return new ArrayList<>();
        return subscriber.get().getSubscribedCourses();
    }

    @Override
    public Collection<Course> getFinishedCourses(UUID id) {
        Optional<Subscriber> subscriber = subscriberRepository.findById(id);
        if(!subscriber.isPresent())
            return new ArrayList<>();
        return subscriber.get().getCompletedCourses();
    }

    @Override
    public boolean isSubscriberBlocked(UUID id) {
        Optional<Subscriber> subscriber = subscriberRepository.findById(id);
        return subscriber.map(Subscriber::isBlocked).orElse(false);
    }

    @Override
    public Collection<AuthorSubscriberReport> getBlockedInfo() {
        return subscriberRepository.findAll().stream()
                .filter(Subscriber::isBlocked)
                .map(s -> new AuthorSubscriberReport(s.getUserId(), s.getName(), s.getSurname(), s.getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateBlocked(UUID userId) {
        Subscriber subscriber = subscriberRepository.findById(userId).get();
        subscriber.setBlocked(!subscriber.isBlocked());
        subscriberRepository.save(subscriber);
    }

    @Override
    public void quitCourse(UUID userId, UUID courseId) {
        Optional<Subscriber> subscriber = subscriberRepository.findById(userId);
        if(!subscriber.isPresent())
            throw new IllegalArgumentException("No such subscriber");
        Set<Course> courses = subscriber.get().getSubscribedCourses().stream()
                .filter(course -> course.getCourseId().compareTo(courseId) != 0)
                .collect(Collectors.toSet());
        subscriber.get().setSubscribedCourses(courses);
        subscriberRepository.save(subscriber.get());
    }

    @Override
    public Double getDiscount(UUID userId, UUID courseId) {
        Subscriber subscriber = subscriberRepository.findById(userId).get();
        KieSession kieSession = kieContainer.newKieSession("cepKsession");

        Course course = courseRepository.findById(courseId).get();
        kieSession.insert(subscriber);
        kieSession.getAgenda().getAgendaGroup("category-discount").setFocus();
        kieSession.fireAllRules();

        kieSession.setGlobal("idTeacher", course.getTeacher().getUserId());
        Teacher teacher = authorRepository.findById(course.getTeacher().getUserId()).get();
        kieSession.insert(teacher);
        subscriber.getCompletedCourses().forEach(kieSession::insert);

        kieSession.getAgenda().getAgendaGroup("special-discount").setFocus();

        kieSession.fireAllRules();

        return subscriber.getDiscount();
    }
}
