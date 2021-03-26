package spring.cloud.memberservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.cloud.memberservice.client.TeamClient;
import spring.cloud.memberservice.client.dto.TeamDto;
import spring.cloud.memberservice.controller.dto.MemberRequestDto;
import spring.cloud.memberservice.controller.dto.MemberQueryDto;
import spring.cloud.memberservice.entity.Member;
import spring.cloud.memberservice.repository.MemberRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamClient teamClient;

    public Long createMember(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getTeamId());
        member = memberRepository.save(member);
        return member.getId();
    }


    public MemberQueryDto findMember(Long memberId) {
        MemberQueryDto result = new MemberQueryDto();
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("ID:"+memberId + " 회원이 존재하지 않음."));
        result.setMemberId(findMember.getId());
        result.setMemberName(findMember.getName());

        if(findMember.getTeamId() != null) {
            TeamDto teamDto = teamClient.getTeam(findMember.getTeamId());
            result.setTeamId(teamDto.getId());
            result.setTeamName(teamDto.getName());
        }

        return result;
    }
}
