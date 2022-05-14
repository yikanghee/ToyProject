package mobee.dto;

import mobee.entity.Posts;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegistPostDTO {

    @NotBlank(message = "필수 항목입니다")
    private String author;

    @NotBlank(message = "필수 항목입니다")
    private String title;

    @NotBlank(message = "필수 항목입니다")
    private String content;

    public Posts toEntity() {

        Posts build = Posts.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();

        return build;
    }
}
