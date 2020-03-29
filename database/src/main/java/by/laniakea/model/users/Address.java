package by.laniakea.model.users;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;


@Embeddable
@Data
public class Address {

    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private int homeNumber;
    @NotBlank
    private int roomNumber;

    public Address(@NotBlank String city, @NotBlank String street, @NotBlank int homeNumber, @NotBlank int roomNumber) {
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.roomNumber = roomNumber;
    }

    public Address() {
    }
}
