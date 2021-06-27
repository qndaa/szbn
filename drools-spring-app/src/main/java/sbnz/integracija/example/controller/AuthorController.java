package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.enums.TypeOfUser;
import sbnz.integracija.example.facts.Teacher;
import sbnz.integracija.example.service.impl.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Void> registerAuthor(@RequestBody Teacher teacher) {
        teacher.setTypeOfUser(TypeOfUser.TEACHER);
        authorService.save(teacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
