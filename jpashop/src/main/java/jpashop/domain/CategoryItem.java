package jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@SequenceGenerator(
        name="CATEGORY_ITEM_SEQ_GENERATOR",
        sequenceName = "CATEGORY_ITEM_SEQ"
)
@Entity
public class CategoryItem {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ITEM_SEQ_GENERATOR")
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Item item;

    public void setCategory(Category category) {
        this.category = category;
        category.getCategoryItems().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
        item.getCategoryItems().add(this);
    }
}
