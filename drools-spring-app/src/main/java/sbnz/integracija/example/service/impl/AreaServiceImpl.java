package sbnz.integracija.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.facts.Area;
import sbnz.integracija.example.repository.AreaRepository;
import sbnz.integracija.example.service.AreaService;

import java.util.Collection;

@Service
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public Collection<Area> getAll() {
        return areaRepository.findAll();
    }
}
