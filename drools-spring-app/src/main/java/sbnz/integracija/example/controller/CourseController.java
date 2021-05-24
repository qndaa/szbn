package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.dto.CourseSearchDTO;
import sbnz.integracija.example.service.CourseService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {
    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Collection<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Collection<Course>> search(@RequestBody CourseSearchDTO searchDTO) {
        return new ResponseEntity<>(courseService.search(searchDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/enroll")
    public ResponseEntity<Void> enroll(@RequestParam UUID userId, @RequestParam UUID courseId) {
        try {
            courseService.enroll(userId, courseId);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
