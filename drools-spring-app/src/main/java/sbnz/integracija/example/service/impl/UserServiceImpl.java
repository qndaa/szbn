package sbnz.integracija.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.LoginInfoDTO;
import sbnz.integracija.example.facts.User;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.security.TokenUtils;
import sbnz.integracija.example.service.UserService;

import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TokenUtils tokenUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenUtils tokenUtils) {
        this.userRepository = userRepository;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public LoginInfoDTO login(LoginInfoDTO loginInfoDTO) {
        User user = this.userRepository.findByUsernameAndPassword(loginInfoDTO.getUsername(), loginInfoDTO.getPassword());

//        if (user != null) {
//            AuthParams ap = new AuthParams();
//            ap.addParam("username", user.getUsername());
//            ap.addParam("role", String.valueOf(user.getTypeOfUser()));
//            return loginInfoDTO.setToken(tokenUtils.generateToken(ap));
//        }
        System.out.println(loginInfoDTO.getUsername());
        System.out.println(loginInfoDTO.getPassword());
        if (user != null) {
            loginInfoDTO.setId(user.getUserId());
            loginInfoDTO.setRole(String.valueOf(user.getTypeOfUser()));
            return loginInfoDTO;
        }
        return null;
    }

    @Override
    public User getById(UUID uuid) {
        return this.userRepository.findById(uuid).get();
    }
}
