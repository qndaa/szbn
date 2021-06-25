package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.facts.Course;

import java.util.Collection;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    Collection<Course> getCoursesByTeacher_UserId(UUID userId);
}
