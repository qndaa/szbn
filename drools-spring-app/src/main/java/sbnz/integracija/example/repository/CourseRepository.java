package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sbnz.integracija.example.facts.Course;

import java.util.Collection;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {


    @Query("SELECT course FROM Course course WHERE ?1 = course.teacher.userId")
    Collection<Course> getCoursesByTeacher(UUID id);
}
