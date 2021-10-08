package jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@SequenceGenerator(
        name="ORDER_ITEM_SEQ_GENERATOR",
        sequenceName = "ORDER_ITEM_SEQ"
)
public class OrderItem {
    @Setter(AccessLevel.PRIVATE)
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int orderPrice;
    private int count;

    public void setOrders(Orders orders) {
        this.orders = orders;
        orders.getOrderItem().add(this);
    }
}
