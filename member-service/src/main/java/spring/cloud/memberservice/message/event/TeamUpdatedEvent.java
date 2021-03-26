package spring.cloud.memberservice.message.event;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TeamUpdatedEvent {
    private String eventName;
    private Long teamId;

    protected TeamUpdatedEvent() {}

    public TeamUpdatedEvent(String eventName, Long teamId) {
        this.eventName = eventName;
        this.teamId = teamId;
    }
}
