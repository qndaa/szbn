package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.facts.Skill;
import sbnz.integracija.example.service.SkillService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/skills")
public class SkillController {

    SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Skill>> getAll () {
        return new ResponseEntity<>(skillService.getAll(), HttpStatus.OK);
    }




}
