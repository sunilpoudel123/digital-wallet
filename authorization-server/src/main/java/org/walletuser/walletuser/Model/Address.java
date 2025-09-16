package org.walletuser.walletuser.Model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String state;
    private String city;
    private String street;
    private String zipcode;
}
