package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.dto.AuthorSubscriberReport;
import sbnz.integracija.example.service.ReportService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/author-subscriber/{id}")
    public ResponseEntity<Collection<AuthorSubscriberReport>> finishedCoursesFromAuthor(@PathVariable UUID id) {
        return new ResponseEntity<>(reportService.finishedCoursesFromAuthor(id), HttpStatus.OK);
    }

    @GetMapping("/course-subscriber/{id}")
    public ResponseEntity<Collection<AuthorSubscriberReport>> subsEnrolledInCourse(@PathVariable UUID id) {
        return new ResponseEntity<>(reportService.getEnrolledSubscribers(id), HttpStatus.OK);
    }

    @GetMapping("/area-subscriber/{area}")
    public ResponseEntity<Collection<AuthorSubscriberReport>> advancedSubscribers(@PathVariable String area) {
        return new ResponseEntity<>(reportService.getAdvancedSubscribers(area), HttpStatus.OK);
    }
}
