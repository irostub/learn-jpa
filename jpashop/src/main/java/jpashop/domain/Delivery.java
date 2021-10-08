package jpashop.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SequenceGenerator(
        name="DELIVERY_SEQ_GENERATOR",
        sequenceName = "DELIVERY_SEQ"
)
@Getter @Setter
@Entity
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELIVERY_SEQ_GENERATOR")
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Orders orders;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
