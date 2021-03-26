package spring.cloud.teamservice.controller.dto;

import lombok.Getter;

@Getter
public class TeamCreateDto {

    private String name;

    protected TeamCreateDto() { }

    public TeamCreateDto(String name) {
        this.name = name;
    }
}
