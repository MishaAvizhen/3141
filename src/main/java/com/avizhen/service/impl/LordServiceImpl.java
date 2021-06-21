package com.avizhen.service.impl;

import com.avizhen.converter.impl.LordConverter;
import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;
import com.avizhen.entity.Universe;
import com.avizhen.repository.LordRepository;
import com.avizhen.service.LordService;
import com.avizhen.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LordServiceImpl implements LordService {
    private LordRepository lordRepository;
    private LordConverter lordConverter;
    private UniverseService universeService;

    @Autowired
    public LordServiceImpl(LordRepository lordRepository, LordConverter lordConverter, UniverseService universeService) {

        this.lordRepository = lordRepository;
        this.lordConverter = lordConverter;
        this.universeService = universeService;
    }

    @Override
    public Lord createLord(LordCreateDto lordCreateDto) {
        Lord lord = lordConverter.convertToEntity(lordCreateDto);
        return lordRepository.save(lord);
    }

    @Override
    public void appointToRulePlanet(Integer lordId) {

    }

    @Override
    public List<Lord> findLordWithoutPlanet() {
        List<Lord> lordsWithUniversity = new ArrayList<>();
        List<Universe> allUniversity = universeService.getAllUniversity();
        for (Universe universe : allUniversity) {
            lordsWithUniversity.add(universe.getLord());
        }
        List<Lord> allLords = lordRepository.findAll();
        allLords.removeAll(lordsWithUniversity);
        return allLords;
    }

    @Override
    public List<Lord> findTheEldestLords() {
        return lordRepository.findAll();
    }

    @Override
    public List<Lord> findAllLords() {
        return lordRepository.findAll();
    }
}
