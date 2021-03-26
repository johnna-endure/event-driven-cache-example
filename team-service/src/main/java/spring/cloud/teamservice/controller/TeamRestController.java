package spring.cloud.teamservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.cloud.teamservice.controller.dto.TeamQueryDto;
import spring.cloud.teamservice.controller.dto.TeamCreateDto;
import spring.cloud.teamservice.controller.dto.TeamUpdateDto;
import spring.cloud.teamservice.service.TeamService;

@RequiredArgsConstructor
@RestController
public class TeamRestController {

    private final TeamService teamService;

    @PostMapping("/team")
    public TeamQueryDto createTeam(@RequestBody TeamCreateDto teamCreateDto) {
        return teamService.createTeam(teamCreateDto);
    }

    @GetMapping("/teams/{teamId}")
    public TeamQueryDto findTeam(@PathVariable("teamId") Long teamId) {
        return teamService.findTeam(teamId);
    }

    @PutMapping("/teams/{teamId}")
    public TeamQueryDto updateTeam(@PathVariable("teamId") Long teamId, @RequestBody TeamUpdateDto updateDto) {
        return teamService.updateTeam(teamId, updateDto);
    }

    @DeleteMapping("/teams/{teamId}")
    public void deleteTeam(@PathVariable("teamId") Long teamId) {
        teamService.deleteTeam(teamId);
    }
}
