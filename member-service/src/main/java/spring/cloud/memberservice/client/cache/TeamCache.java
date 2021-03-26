package spring.cloud.memberservice.client.cache;

import org.springframework.stereotype.Component;
import spring.cloud.memberservice.client.dto.TeamDto;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TeamCache {
    private final ConcurrentHashMap<Long, TeamDto> teamCacheMap = new ConcurrentHashMap<>();

    public boolean containsKey(Long teamId) {
        return teamCacheMap.containsKey(teamId);
    }

    public void put(Long teamId, TeamDto team) {
        teamCacheMap.put(teamId, team);
    }

    public TeamDto get(Long teamId) {
        return teamCacheMap.get(teamId);
    }

    public void remove(Long teamId) {
        if(containsKey(teamId)) teamCacheMap.remove(teamId);
    }

    @Override
    public String toString() {
        return teamCacheMap.toString();
    }
}
