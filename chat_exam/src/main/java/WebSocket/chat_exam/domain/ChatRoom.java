package WebSocket.chat_exam.domain;

import WebSocket.chat_exam.Service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }

    /*
    * 채팅방 구현
    * 채팅방을 구현하기 위해 DTO를 하나 만든다
    * 채팅방은 입장한 클라이언트들의 정보를 가지고 있어야한다
    * WebsocketSesstion 정보 리스트를 멤버 필드로 가지고 있는다
    * 나머지 멤버 필드로 치탱방 id, 채팅방 이름을 추가한다
    * 채팅방에서는 입장, 대화하기의 기능이 있으므로 handleAction을 통해 분기 처리한다
    * 입장 시에는 채팅룸의 session 정보에 클라이언트의 session 리스트에 추가해 놓는다
    * 채팅룸에 메시지가 도착할 경우 채팅룸의 모든 session에 메시지를 발송하면 채팅이 완성된다
    *
    * */
}

