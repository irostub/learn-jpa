package jpashop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter @Setter @EqualsAndHashCode
@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
