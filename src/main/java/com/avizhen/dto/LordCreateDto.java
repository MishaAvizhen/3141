package com.avizhen.dto;


public class LordCreateDto {
    private String name;
    private Integer age;

    public LordCreateDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public LordCreateDto() {
    }

    public LordCreateDto(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
