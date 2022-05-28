package WebSocket.chat_exam.handler;

import WebSocket.chat_exam.Service.ChatService;
import WebSocket.chat_exam.domain.ChatMessage;
import WebSocket.chat_exam.domain.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            String payload = message.getPayload();
            log.info("payload {}", payload);
// 삭제        TextMessage textMessage = new TextMessage("Welcome chatting sever~^^ ");
// 삭제       session.sendMessage(textMessage);
            ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
            ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
            room.handleActions(session, chatMessage, chatService);
        }
    }
    /*
        Websocket 통신을 활용하면 서버와 클라이언트가 1:N 관계를 갖는다
        이로 인해서 한 서버에 여러명의 클라이언트가 접속할 수 있다
        서버에는 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler를 작성해 준다

        handler 수정
        웹소켓 클라이언트로부터 채팅 메시지를 전달받아 채팅 메시지 객체로 변환
        전달받은 메시지에 담긴 채팅방 Id로 발송 대상 채팅방 정보를 조회함
        해당 채팅방에 입장해있는 모든 클라이언트 (Websocket session) 들에게 타입에 따른 메시지 발송
     */

