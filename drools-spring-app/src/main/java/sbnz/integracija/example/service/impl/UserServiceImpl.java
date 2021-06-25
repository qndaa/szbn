package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.LoginInfoDTO;
import sbnz.integracija.example.events.LoginEvent;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.facts.User;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.security.TokenUtils;
import sbnz.integracija.example.service.UserService;

import java.util.Collection;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscriberRepository subscriberRepository;
    private final TokenUtils tokenUtils;
    private final KieSession cepSession;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SubscriberRepository subscriberRepository, TokenUtils tokenUtils, KieSession cepSession) {
        this.userRepository = userRepository;
        this.subscriberRepository = subscriberRepository;
        this.tokenUtils = tokenUtils;
        this.cepSession = cepSession;
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
        if (user != null) {
            loginInfoDTO.setId(user.getUserId());
            loginInfoDTO.setRole(String.valueOf(user.getTypeOfUser()));
            LoginEvent loginEvent = new LoginEvent(user.getUserId());
            Collection<Subscriber> subs = subscriberRepository.findAll();
            subs.forEach(cepSession::insert);
            cepSession.insert(loginEvent);
            cepSession.getAgenda().getAgendaGroup("malware").setFocus();
            cepSession.fireAllRules();
//            subs.forEach(subscriberRepository::save);
            return loginInfoDTO;
        }
        return null;
    }
}
