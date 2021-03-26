package spring.cloud.memberservice.client;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.cloud.memberservice.client.cache.TeamCache;
import spring.cloud.memberservice.client.dto.TeamDto;

@RequiredArgsConstructor
@Component
public class TeamClient {

    private final RestTemplate restTemplate;
    private final TeamCache teamCache;

    private static Logger logger = LoggerFactory.getLogger(TeamClient.class);

    public TeamDto getTeam(Long teamId) {
        //캐시 구현
        if(teamCache.containsKey(teamId)) {
            logger.info("캐시 히트");
            return teamCache.get(teamId);
        }
        ResponseEntity<TeamDto> response =
                restTemplate.exchange("http://localhost:8086/teams/" + teamId, HttpMethod.GET, null, TeamDto.class);
        TeamDto result = response.getBody();
        logger.info("캐시에 저장");
        teamCache.put(teamId, result);
        return result;
    }
}
