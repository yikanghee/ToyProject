package WebSocket.chat_exam.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSockConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    public WebSockConfig(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }

    /*
    *  만들어 놓은 handler를 이용해서 WebSocket을 활성화 하기 위한 Config를 작성
    *  @EnableWebSocket을 선언하여 WebSocket을 활성화한다
    *  WebSocket에 접속하기 위한 endPoint는 /ws/chat으로 설정하고 도메인이 다른 서버에서도 접속 가능하도록
    *  CORS : setAllowedOrigins("*") 을 추가해준다
    *  이제 ws://localhost:8080/ws/chat으로 커넥션을 연결하고 메시지 통신을 할 수 있는 기본적인 준비가 끝났다
    * */
}
