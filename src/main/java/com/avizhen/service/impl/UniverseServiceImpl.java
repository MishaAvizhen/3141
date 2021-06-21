package com.avizhen.service.impl;

import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Universe;
import com.avizhen.service.UniverseService;
import org.springframework.stereotype.Service;

@Service
public class UniverseServiceImpl implements UniverseService {
    @Override
    public Universe createUniverse(UniverseCreateDto universeCreateDto) {
        return null;
    }

    @Override
    public void deleteUniverse(Integer universeId) {

    }
}
