package jpa.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Address {
    private String zipcode;
    private String street;
    private String city;
}
