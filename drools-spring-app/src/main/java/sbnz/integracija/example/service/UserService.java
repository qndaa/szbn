package sbnz.integracija.example.service;

import sbnz.integracija.example.dto.LoginInfoDTO;

public interface UserService {
    LoginInfoDTO login(LoginInfoDTO loginInfoDTO);
}
