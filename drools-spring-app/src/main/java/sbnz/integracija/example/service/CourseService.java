package sbnz.integracija.example.service;

import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.dto.CourseSearchDTO;

import java.util.Collection;

public interface CourseService {
    Collection<Course> getAll();
    Collection<Course> search(CourseSearchDTO searchDTO);
}
