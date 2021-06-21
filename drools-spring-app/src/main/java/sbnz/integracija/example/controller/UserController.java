package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.dto.LoginInfoDTO;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.facts.User;
import sbnz.integracija.example.service.UserService;

import java.util.Collection;
import java.util.UUID;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginInfoDTO loginInfoDTO) {
        LoginInfoDTO login = userService.login(loginInfoDTO);
        return (login == null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(login, HttpStatus.OK);
    }

}
