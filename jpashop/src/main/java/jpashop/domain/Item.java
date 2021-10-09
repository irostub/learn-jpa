package jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@SequenceGenerator(
        name="ITEM_SEQ_GENERATOR",
        sequenceName = "ITEM_SEQ",
        initialValue = 1, allocationSize = 50
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Entity
public class Item {
    @Setter(AccessLevel.PRIVATE)
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ_GENERATOR")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();
}
