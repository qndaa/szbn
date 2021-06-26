package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.facts.Area;
import sbnz.integracija.example.service.AreaService;

import java.util.Collection;

@RestController
@RequestMapping("/areas")
public class AreaController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Area>> getAllAreas() {
        return new ResponseEntity<>(this.areaService.getAll(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Collection<Area>> getAll() {
        return new ResponseEntity<>(areaService.getAll(), HttpStatus.OK);
    }
}
