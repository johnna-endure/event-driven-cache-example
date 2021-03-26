package spring.cloud.memberservice.controller.dto;

import lombok.Data;

@Data
public class MemberRequestDto {
    private final String name;
    private final Long teamId;
}
