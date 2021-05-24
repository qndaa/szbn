package sbnz.integracija.example.service;

import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.Subscriber;

import java.util.Collection;
import java.util.UUID;

public interface ReportService {
    Collection<Subscriber> finishedCoursesFromAuthor(UUID authorId);
}
