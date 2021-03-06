package jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@SequenceGenerator(
        name="MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ"
)
public class Member {
    @Setter(AccessLevel.PRIVATE)
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    private String name;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="zipcode",
                    column=@Column(name="WORK_ZIPCODE")),
            @AttributeOverride(name="street",
                    column=@Column(name="WORK_STREET")),
            @AttributeOverride(name="city",
                    column=@Column(name="WORK_CITY"))
    })
    private Address workAddress;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();
}
