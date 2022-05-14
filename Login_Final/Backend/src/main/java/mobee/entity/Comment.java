package mobee.entity;


import lombok.*;
import mobee.dto.CommentReuestDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private float starRate;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne
    private MovieApi movieApi;

    @ManyToOne
    private Account account;

    public Comment(CommentReuestDTO requestDTO) {
        this.comment = requestDTO.getComment();
        this.starRate = requestDTO.getStarRate();
    }

    public void updateComment(CommentReuestDTO requestDTO) {
        this.comment = requestDTO.getComment();
        this.starRate = requestDTO.getStarRate();
    }


}
