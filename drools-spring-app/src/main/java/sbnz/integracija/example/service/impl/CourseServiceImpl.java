package sbnz.integracija.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.repository.CourseRepository;
import sbnz.integracija.example.service.CourseService;

import java.util.Collection;

@Service
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Collection<Course> getAll() {
        return courseRepository.findAll();
    }
}
