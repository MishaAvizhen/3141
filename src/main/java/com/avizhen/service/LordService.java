package com.avizhen.service;

import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;

import java.util.List;

public interface LordService {
    Lord createLord(LordCreateDto lordCreateDto);

    void appointToRulePlanet(Integer lordId);

    List<Lord> findLordWithoutPlanet(Integer universeId);

    List<Lord> findTheEldestLords();


}
