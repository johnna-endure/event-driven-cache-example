package spring.cloud.memberservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.cloud.memberservice.client.cache.TeamCache;
import spring.cloud.memberservice.controller.dto.MemberRequestDto;
import spring.cloud.memberservice.controller.dto.MemberQueryDto;
import spring.cloud.memberservice.service.MemberService;

@RequiredArgsConstructor
@RestController
public class MemberRestController {

    private final MemberService memberService;
    private final TeamCache teamCache;

    @PostMapping("/member")
    public Long createMember(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.createMember(memberRequestDto);
    }

    @GetMapping("/members/{memberId}")
    public MemberQueryDto findMember(@PathVariable("memberId") Long memberId) {
        return memberService.findMember(memberId);
    }

    @GetMapping("/cache")
    public String showCache() {
        return teamCache.toString();
    }

}
