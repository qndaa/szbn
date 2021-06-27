package sbnz.integracija.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Teacher;
import sbnz.integracija.example.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
    public final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void save(Teacher teacher) {
        authorRepository.save(teacher);
    }
}
