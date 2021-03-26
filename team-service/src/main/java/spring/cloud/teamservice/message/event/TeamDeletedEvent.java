package spring.cloud.teamservice.message.event;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TeamDeletedEvent {
    private String eventName;
    private Long teamId;

    protected TeamDeletedEvent() {}

    public TeamDeletedEvent(String eventName, Long teamId) {
        this.eventName = eventName;
        this.teamId = teamId;
    }
}
