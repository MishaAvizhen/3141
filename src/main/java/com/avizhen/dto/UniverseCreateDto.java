package com.avizhen.dto;



public class UniverseCreateDto {
    private String title;

    public UniverseCreateDto() {
    }

    public UniverseCreateDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
