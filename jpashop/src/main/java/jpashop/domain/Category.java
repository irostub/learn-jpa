package jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@SequenceGenerator(
        name="CATEGORY_SEQ_GENERATOR",
        sequenceName = "CATEGORY_SEQ"
)
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ_GENERATOR")
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> items = new ArrayList<>();

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "category")
    private List<Category> child = new ArrayList<>();
}
