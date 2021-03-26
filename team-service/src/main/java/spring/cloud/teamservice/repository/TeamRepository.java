package spring.cloud.teamservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.cloud.teamservice.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
