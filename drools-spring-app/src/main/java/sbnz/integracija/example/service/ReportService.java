package sbnz.integracija.example.service;

import sbnz.integracija.example.dto.AuthorSubscriberReport;

import java.util.Collection;
import java.util.UUID;

public interface ReportService {
    Collection<AuthorSubscriberReport> finishedCoursesFromAuthor(UUID authorId);

    Collection<AuthorSubscriberReport> getEnrolledSubscribers(UUID courseId);

    Collection<AuthorSubscriberReport> getAdvancedSubscribers(String area);
}
