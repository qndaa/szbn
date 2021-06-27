package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.Teacher;
import sbnz.integracija.example.facts.User;
import sbnz.integracija.example.facts.dto.CourseSearchDTO;
import sbnz.integracija.example.service.CourseService;
import sbnz.integracija.example.service.UserService;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {
    CourseService courseService;
    UserService userService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
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

    @GetMapping(value = "/getCoursesByTeacher/{id}")
    public ResponseEntity<Collection<Course>> getCoursesByTeacher(@PathVariable("id") String id) {
        return new ResponseEntity<>(courseService.getCoursesByTeacher(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") String id) {
        boolean deleted = this.courseService.deleteCourse(id);
        if ((deleted)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<HttpStatus> createCourse(@RequestBody Course course ,@PathVariable("id") String id ) {
        System.out.println(course.getCourseAreas().size());
        System.out.println(course.getPreconditions().size());
        System.out.println(course.getSkills().size());
        User user = userService.getById(UUID.fromString(id));
        System.out.println(user.getUserId());
        course.setTeacher((Teacher) user);
        this.courseService.save(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/preconditions/{course}")
    public ResponseEntity<Collection<Course>> listPrerequisites(@PathVariable String course) {
        return new ResponseEntity<>(courseService.listPrerequisites(UUID.fromString(course)), HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Collection<Course>> getCoursesByAuthor(@PathVariable UUID authorId) {
        return new ResponseEntity<>(courseService.getCoursesByAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping("/teacher/{courseId}")
    public ResponseEntity<Teacher> getCourseTeacher(@PathVariable UUID courseId) {
        return new ResponseEntity<>(courseService.getCourseTeacher(courseId), HttpStatus.OK);
    }

    @GetMapping("/hasPrecondition/{userId}/{courseId}")
    public ResponseEntity<String> hasPrecondition(@PathVariable("courseId") UUID courseId, @PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(courseService.hasPrecondition(userId, courseId),HttpStatus.OK);
    }

    @GetMapping("/buy/{userId}/{courseId}")
    public ResponseEntity<Course> buy(@PathVariable("courseId") UUID courseId, @PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(courseService.buy(userId, courseId),HttpStatus.OK);
    }

}
