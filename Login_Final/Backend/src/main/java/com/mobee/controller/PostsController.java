package com.mobee.controller;

import com.mobee.dto.ModifyPostsDTO;
import com.mobee.dto.PostsResDTO;
import com.mobee.dto.RegistPostDTO;
import com.mobee.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = {"/posts"}, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PostsController {

    private PostsService postsService;

    // 게시글 목록 조회
    @GetMapping({""})
    public ResponseEntity<List<PostsResDTO>> getPosts() {
        return ResponseEntity.ok()
                .body(postsService.getPostsService());
    }

    // 게시글 등록
    @PostMapping({""})
    public ResponseEntity<Long> regPosts(@Valid @RequestBody RegistPostDTO registPostDTO) {
        return ResponseEntity.ok()
                .body(postsService.regPostsService(registPostDTO));
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostsResDTO> getPostsDetail(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok()
                .body(postsService.getPostsByIdService(id));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> ModifyPosts(
            @PathVariable Long id,
            @Valid @RequestBody ModifyPostsDTO modifyPostsDTO) {

        postsService.setPostsService(modifyPostsDTO);

        return ResponseEntity.ok()
                .body("UPDATE SUCCESS");
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delPosts(
            @PathVariable Long id) {
        postsService.delPostsService(id);

        return ResponseEntity.ok()
                .body("DELETE SUCCESS");
    }

}