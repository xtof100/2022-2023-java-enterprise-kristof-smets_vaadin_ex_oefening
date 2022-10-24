package be.ucll.jpa.exercise4;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

public class NaturalPerson extends Party {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String name() {
        return firstName + " " + lastName;
    }
}
