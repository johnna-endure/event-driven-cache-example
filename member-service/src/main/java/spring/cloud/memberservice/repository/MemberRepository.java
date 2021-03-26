package spring.cloud.memberservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.cloud.memberservice.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
