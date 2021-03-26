package spring.cloud.memberservice.message.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import spring.cloud.memberservice.client.TeamClient;
import spring.cloud.memberservice.client.cache.TeamCache;
import spring.cloud.memberservice.client.dto.TeamDto;
import spring.cloud.memberservice.message.event.TeamDeletedEvent;
import spring.cloud.memberservice.message.event.TeamUpdatedEvent;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
public class TeamEventConsumers {

    private final TeamCache teamCache;
    private final TeamClient teamClient;
    private Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(TeamEventConsumers.class);

    @Bean
    public Consumer teamUpdated() {
        return (o) -> {
            logger.info("teamUpdated 이벤트 수신");
            try {
                TeamUpdatedEvent event = mapper.fromJson(o, TeamUpdatedEvent.class);
                updateCache(event.getTeamId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private void updateCache(Long teamId) {
        if(teamCache.containsKey(teamId)) {
            teamCache.remove(teamId);
            TeamDto teamDto = teamClient.getTeam(teamId);
            teamCache.put(teamId, teamDto);
            logger.info("[teamId : {}, data : {}] is updated.", teamId, teamDto);
        };
    }

    @Bean
    public Consumer teamDeleted() {
        return (o)->{
            logger.info("teamDeleted 이벤트 수신");
            try {
                TeamDeletedEvent event = mapper.fromJson(o, TeamDeletedEvent.class);
                deleteCache(event.getTeamId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private void deleteCache(Long teamId) {
        if(teamCache.containsKey(teamId)) {
            teamCache.remove(teamId);
            logger.info("teamId : {}  is deleted.", teamId);
        }

    }


}
