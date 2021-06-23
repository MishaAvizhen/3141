package com.avizhen.service.impl;

import com.avizhen.converter.impl.LordConverter;
import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;
import com.avizhen.entity.Universe;
import com.avizhen.enums.Status;
import com.avizhen.repository.LordRepository;
import com.avizhen.repository.UniverseRepository;
import com.avizhen.service.LordService;
import com.avizhen.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LordServiceImpl implements LordService {
    private LordRepository lordRepository;
    private LordConverter lordConverter;
    private UniverseService universeService;
    private UniverseRepository universeRepository;

    @Autowired
    public LordServiceImpl(LordRepository lordRepository, LordConverter lordConverter, UniverseService universeService, UniverseRepository universeRepository) {

        this.lordRepository = lordRepository;
        this.lordConverter = lordConverter;
        this.universeService = universeService;
        this.universeRepository = universeRepository;
    }

    @Override
    public Lord createLord(LordCreateDto lordCreateDto) {
        Lord lord = lordConverter.convertToEntity(lordCreateDto);
        return lordRepository.save(lord);
    }

    @Override
    public void appointToRulePlanet(Integer lordId, Integer universeId) {
        Universe universe = universeRepository.getOne(universeId);
        Lord lord = lordRepository.getOne(lordId);
        universe.setStatus(Status.BUSY);
        universe.setLord(lord);
        universeRepository.save(universe);
    }

    @Override
    public List<Lord> findLordWithoutPlanet() {
        List<Lord> lordsWithUniversity = new ArrayList<>();
        List<Universe> allUniversity = universeService.getAllUniversity();
        for (Universe universe : allUniversity) {
            if (universe.getLord() != null) {
                lordsWithUniversity.add(universe.getLord());
            }
        }
        List<Lord> allLords = lordRepository.findAll();
        allLords.removeAll(lordsWithUniversity);
        return allLords;
    }

    @Override
    public List<Lord> findTheEldestLords() {
        List<Lord> lordList = lordRepository.findAll();
        return lordList.stream()
                .sorted(Comparator.comparingInt(Lord::getAge))
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public List<Lord> findAllLords() {
        return lordRepository.findAll();
    }
}
