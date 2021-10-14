package jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.NamedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@NamedQuery(
        name="Member.myNamedQuery",
        query="SELECT distinct m FROM Member m join fetch m.team"
)
@Getter @Setter
@Entity
@SequenceGenerator(
        name="MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ"
)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 20)
    private String name;

    //    @Column(unique = true)
    private String phone;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(updatable = false)
    private LocalDate localDate;

    private LocalDateTime localDateTime;

    @Lob
    private String description;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",
                    column = @Column(name = "WORK_ZIPCODE")),
            @AttributeOverride(name = "street",
                    column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "city",
                    column = @Column(name = "WORK_CITY"))
    })
    private Address workAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
