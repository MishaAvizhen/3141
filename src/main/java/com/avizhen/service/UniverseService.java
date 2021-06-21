package com.avizhen.service;

import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Universe;

public interface UniverseService {
    Universe createUniverse(UniverseCreateDto universeCreateDto);

    void deleteUniverse(Integer universeId);
    }
