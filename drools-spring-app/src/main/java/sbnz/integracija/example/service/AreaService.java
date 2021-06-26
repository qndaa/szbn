package sbnz.integracija.example.service;

import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Area;

import java.util.Collection;

public interface AreaService {
    Collection<Area> getAll();
}
