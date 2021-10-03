package jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Entity
@SequenceGenerator(
        name="ORDER_SEQ_GENERATOR",
        sequenceName = "ORDER_SEQ",
        initialValue = 1, allocationSize = 50
)
public class Order {
    @Setter(AccessLevel.PRIVATE)
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ_GENERATOR")
    private Long id;

    @ManyToOne
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
