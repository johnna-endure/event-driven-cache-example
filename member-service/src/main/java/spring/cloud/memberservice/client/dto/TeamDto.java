package spring.cloud.memberservice.client.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class TeamDto {
    private Long id;
    private String name;

    protected TeamDto() {}

    public TeamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return Objects.equals(id, teamDto.id) && Objects.equals(name, teamDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
