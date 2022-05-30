package WebSocket.chat_exam.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

    /*
    * 채팅 메시지 구현
    * 채팅 메시지를 주고 받기 위한 DTO를 하나 만든다
    * 상황에 따라 채팅방 입장, 채팅방에 메시지 보내기 두가지 상황이 있으므로
    * ENTER, TALK을 enum 객체로 선언한다
    * 나머지 멤버 필드는 채팅방 구별 id, 메시지를 보낸 사람, 메시지 로 구성
    * */

}
