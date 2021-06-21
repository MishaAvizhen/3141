package com.avizhen.service.impl;

import com.avizhen.converter.impl.UniverseConverter;
import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Universe;
import com.avizhen.repository.UniverseRepository;
import com.avizhen.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
