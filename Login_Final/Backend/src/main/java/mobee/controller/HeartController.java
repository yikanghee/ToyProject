package mobee.controller;

import lombok.RequiredArgsConstructor;
import mobee.config.UserDetailsImpl;
import mobee.entity.Heart;
import mobee.entity.Message;
import mobee.service.HeartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/api/movies/{movie_id}/heart")
    public HashMap<String, Object> ReadHeart(@PathVariable Long movie_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return heartService.ReadHeart(movie_id, userDetails.getAccount().getId());
    }

    @PostMapping("/api/movies/{movie_id}/heart")
    public ResponseEntity CreateHeart(@PathVariable Long movie_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Heart heart = heartService.CreateHeart(movie_id, userDetails.getAccount().getId());
        if (heart == null) {
            Message message = Message.builder()
                    .messageV1("이미 좋아요한 상태입니다.")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/movies/{movie_id}/heart")
    public ResponseEntity DeleteHeart(@PathVariable Long movie_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Heart heart = heartService.DeleteHeart(movie_id, userDetails.getAccount().getId());
        if (heart == null) {
            Message message = Message.builder()
                    .messageV1("취소할 좋아요가 없습니다")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
