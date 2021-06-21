package com.avizhen.converter.impl;

import com.avizhen.converter.Converter;
import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;

public class LordConverter implements Converter<Lord, LordCreateDto> {


    @Override
    public Lord convertToEntity(LordCreateDto dto) {
        Lord lord = new Lord();
        lord.setName(dto.getName());
        lord.setAge(dto.getAge());
        return lord;
    }
}
