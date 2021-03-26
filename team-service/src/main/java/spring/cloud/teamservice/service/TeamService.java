package spring.cloud.teamservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.cloud.teamservice.controller.dto.TeamQueryDto;
import spring.cloud.teamservice.controller.dto.TeamCreateDto;
import spring.cloud.teamservice.controller.dto.TeamUpdateDto;
import spring.cloud.teamservice.entity.Team;
import spring.cloud.teamservice.message.event.TeamDeletedEvent;
import spring.cloud.teamservice.message.event.TeamUpdatedEvent;
import spring.cloud.teamservice.repository.TeamRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;
    private final StreamBridge streamBridge;

    private static Logger logger = LoggerFactory.getLogger(TeamService.class);

    public TeamQueryDto createTeam(TeamCreateDto teamCreateDto) {
        Team team = new Team(teamCreateDto.getName());
        team = teamRepository.save(team);
        return new TeamQueryDto(team.getId(), team.getName());
    }

    public TeamQueryDto findTeam(Long teamId) {
        return teamRepository.findById(teamId)
                .map(t -> new TeamQueryDto(t.getId(), t.getName()))
                .orElse(new TeamQueryDto(null, null));
    }

    public TeamQueryDto updateTeam(Long teamId, TeamUpdateDto updateDto) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("id : " + teamId + " 회원을 찾을 수 없습니다."));
        team.update(updateDto.getName());

        TeamUpdatedEvent event = new TeamUpdatedEvent("updated",team.getId());
        streamBridge.send("teamUpdated", event);
        logger.info("team-updated 이벤트 발신 : {} ", event);
        return new TeamQueryDto(team.getId(), team.getName());
    }

    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
        TeamDeletedEvent event = new TeamDeletedEvent("deleted", teamId);
        streamBridge.send("teamDeleted", event);
        logger.info("team-deleted 이벤트 발신 : {}", event);
    }
}
