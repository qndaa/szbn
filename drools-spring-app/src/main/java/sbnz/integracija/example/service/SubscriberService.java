package sbnz.integracija.example.service;

import sbnz.integracija.example.dto.AuthorSubscriberReport;
import sbnz.integracija.example.enums.CategoryOfUser;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.Subscriber;

import java.util.Collection;
import java.util.UUID;

public interface SubscriberService {


    Subscriber save(Subscriber subscriberRequest);

    CategoryOfUser updateCategory(UUID fromString);

    Collection<Course> getEnrolledCourses(UUID id);

    Collection<Course> getFinishedCourses(UUID id);

    boolean isSubscriberBlocked(UUID id);

    Collection<AuthorSubscriberReport> getBlockedInfo();

    void updateBlocked(UUID userId);

    void quitCourse(UUID userId, UUID courseId);
}
