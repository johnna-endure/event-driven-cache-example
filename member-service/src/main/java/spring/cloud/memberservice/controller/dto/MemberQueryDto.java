package spring.cloud.memberservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberQueryDto {
    private Long memberId;
    private String memberName;
    private Long teamId;
    private String teamName;

    public MemberQueryDto() {}

    public MemberQueryDto(Long memberId, String memberName, Long teamId, String teamName) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
