package com.avizhen.service.impl;

import com.avizhen.converter.impl.UniverseConverter;
import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Universe;
import com.avizhen.enums.Status;
import com.avizhen.repository.UniverseRepository;
import com.avizhen.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniverseServiceImpl implements UniverseService {
    private UniverseRepository universeRepository;
    private UniverseConverter universeConverter;

    @Autowired
    public UniverseServiceImpl(UniverseRepository universeRepository, UniverseConverter universeConverter) {

        this.universeRepository = universeRepository;
        this.universeConverter = universeConverter;
    }

    @Override
    public Universe createUniverse(UniverseCreateDto universeCreateDto) {
        Universe universe = universeConverter.convertToEntity(universeCreateDto);
        universe.setStatus(Status.FREE);
        return universeRepository.save(universe);
    }

    @Override
    public void deleteUniverse(Integer universeId) {
        universeRepository.deleteById(universeId);
    }

    @Override
    public List<Universe> getAllUniversity() {
        return universeRepository.findAll();
    }

    @Override
    public List<Universe> getAllFreeUniversity() {
        List<Universe> allUniversity = this.getAllUniversity();
        return allUniversity.stream()
                .filter(u -> u.getStatus().equals(Status.FREE))
                .collect(Collectors.toList());
    }
}
