package SpringReact.SpringReact.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Currency {

    public Currency(String name, String code, BigDecimal amount, BigDecimal rate) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.rate = rate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String code;

    private BigDecimal amount;

    private BigDecimal rate;


}