package sbnz.integracija.example.service;

import sbnz.integracija.example.dto.LoginInfoDTO;
import sbnz.integracija.example.facts.User;

import java.util.UUID;

public interface UserService {
    LoginInfoDTO login(LoginInfoDTO loginInfoDTO);
    User getById(UUID uuid);
}
