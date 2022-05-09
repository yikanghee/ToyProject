package com.mobee.dto;

import com.mobee.entity.Posts;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModifyPostsDTO {

    @Min(1)
    private Long id;

    @NotBlank(message = "필수 사항입니다")
    private String author;

    @NotBlank(message = "필수 사항입니다")
    private String title;

    @NotBlank(message = "필수 사항입니다")
    private String content;

    public Posts toEntity() {
        Posts build = Posts.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return build;
    }

}