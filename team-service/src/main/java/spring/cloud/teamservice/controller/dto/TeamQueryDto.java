package spring.cloud.teamservice.controller.dto;

import lombok.Getter;

@Getter
public class TeamQueryDto {
    private Long id;
    private String name;

    protected TeamQueryDto() { }

    public TeamQueryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}