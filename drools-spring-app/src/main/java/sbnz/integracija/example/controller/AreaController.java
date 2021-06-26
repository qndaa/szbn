package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.facts.Area;
import sbnz.integracija.example.service.AreaService;
import sbnz.integracija.example.service.CourseService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/areas")
public class AreaController {

    AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Area>> getAll() {
        return new ResponseEntity<>(this.areaService.getAll(), HttpStatus.OK);
    }


}
