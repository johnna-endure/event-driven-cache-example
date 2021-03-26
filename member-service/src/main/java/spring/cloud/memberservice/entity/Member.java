package spring.cloud.memberservice.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private Long teamId;

    protected Member() {}

    public Member(String name) {
        this(name, null);
    }

    public Member(String name, Long teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public void joinTeam(Long teamId) {
        this.teamId = teamId;
    }
}
