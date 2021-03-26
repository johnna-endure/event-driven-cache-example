package spring.cloud.teamservice.controller.dto;

import lombok.Getter;

@Getter
public class TeamUpdateDto {
    private String name;

    protected TeamUpdateDto() {}

    public TeamUpdateDto(String name) {
        this.name = name;
    }
}
