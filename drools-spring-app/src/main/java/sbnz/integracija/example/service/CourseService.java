package sbnz.integracija.example.service;

import sbnz.integracija.example.facts.Course;

import java.util.Collection;

public interface CourseService {
    Collection<Course> getAll();
}
