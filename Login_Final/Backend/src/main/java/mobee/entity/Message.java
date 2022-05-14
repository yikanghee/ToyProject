package mobee.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Message {

    private String messageV1;

    @Builder
    public Message(String messageV1, String messageV2) {
        this.messageV1 = messageV1;
    }

}
