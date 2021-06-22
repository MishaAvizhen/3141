package com.avizhen.service;

import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Universe;

import java.util.List;

public interface UniverseService {
    Universe createUniverse(UniverseCreateDto universeCreateDto);

    void deleteUniverse(Integer universeId);

    List<Universe> getAllUniversity();

    List<Universe> getAllFreeUniversity();



    }
