package com.avizhen.service.impl;

import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;
import com.avizhen.repository.LordRepository;
import com.avizhen.service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LordServiceImpl implements LordService {
    private LordRepository lordRepository;

    @Autowired
    public LordServiceImpl(LordRepository lordRepository) {

        this.lordRepository = lordRepository;
    }

    @Override
    public Lord createLord(LordCreateDto lordCreateDto) {
        return null;
    }

    @Override
    public void appointToRulePlanet(Integer lordId) {

    }

    @Override
    public List<Lord> findLordWithoutPlanet(Integer universeId) {
        return null;
    }

    @Override
    public List<Lord> findTheEldestLords() {
        return lordRepository.findAll();
    }
}
