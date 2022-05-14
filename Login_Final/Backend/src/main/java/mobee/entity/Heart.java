package mobee.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
// equals와 hashcode를 자동으로 생성,
// 두객체의 같은 객체인지, 내용이 같은지 확인인@AllArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Heart {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isHeart;

    @ManyToOne
    private MovieApi movieApi;

    @ManyToOne
    private Account account;
}
