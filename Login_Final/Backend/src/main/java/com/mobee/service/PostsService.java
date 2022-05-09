package com.mobee.service;

import com.mobee.dto.ModifyPostsDTO;
import com.mobee.dto.PostsResDTO;
import com.mobee.dto.RegistPostDTO;
import com.mobee.entity.Posts;
import com.mobee.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postService")
@AllArgsConstructor
public class PostsService {

    private PostsRepository postsRepository;

    // 글 목록 조회
    public List<PostsResDTO> getPostsService() {

        List<Posts> entityList = postsRepository.findAll();
        List<PostsResDTO> result = new ArrayList<>();

        entityList.forEach(entity -> {
            PostsResDTO rDTO = new PostsResDTO();
            rDTO.setId(entity.getId());
            rDTO.setAuthor(entity.getAuthor());
            rDTO.setTitle(entity.getTitle());
            rDTO.setContent(entity.getContent());
            rDTO.setCreatedDate(entity.getCreatedDate());
            rDTO.setModifiedDate(entity.getModifiedDate());
            result.add(rDTO);
        });

        return result;
    }

    // 글 등록
    public Long regPostsService(RegistPostDTO regPosts) {
        Long insertId = postsRepository.save(regPosts.toEntity()).getId();

        return insertId;
    }

    // 글 상세 조회
    public PostsResDTO getPostsByIdService(Long id) throws Exception {

        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new Exception("해당 게시글이 없습니다"));

        PostsResDTO rDTO = new PostsResDTO();
        rDTO.setId(entity.getId());
        rDTO.setAuthor(entity.getAuthor());
        rDTO.setTitle(entity.getTitle());
        rDTO.setContent(entity.getContent());
        rDTO.setCreatedDate(entity.getCreatedDate());
        rDTO.setModifiedDate(entity.getModifiedDate());

        return rDTO;
    }

    // 글 수정
    public void setPostsService(ModifyPostsDTO setPosts) {
        postsRepository.save(setPosts.toEntity());
    }

    // 글 삭제
    public void delPostsService(Long id) {
        postsRepository.deleteById(id);
    }







}