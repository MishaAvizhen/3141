package com.avizhen.converter.impl;

import com.avizhen.converter.Converter;
import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Universe;

public class UniverseConverter implements Converter<Universe, UniverseCreateDto> {

    @Override
    public Universe convertToEntity(UniverseCreateDto dto) {
        Universe universe = new Universe();
        universe.setTitle(dto.getTitle());
        return universe;
    }
}
