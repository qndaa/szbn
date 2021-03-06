package sbnz.integracija.example.service;

import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.Teacher;
import sbnz.integracija.example.facts.dto.CourseSearchDTO;

import java.util.Collection;
import java.util.UUID;

public interface CourseService {
    Collection<Course> getAll();
    Collection<Course> search(CourseSearchDTO searchDTO);
    void enroll(UUID userId, UUID courseId);

    Collection<Course> getCoursesByTeacher(String id);

    boolean deleteCourse(String id);

    void save(Course course);
    Collection<Course> listPrerequisites(UUID course);

    Collection<Course> getCoursesByAuthor(UUID authorId);

    Teacher getCourseTeacher(UUID courseId);

    String hasPrecondition(UUID userId, UUID courseId);

    Course buy(UUID userId, UUID courseId);
}
