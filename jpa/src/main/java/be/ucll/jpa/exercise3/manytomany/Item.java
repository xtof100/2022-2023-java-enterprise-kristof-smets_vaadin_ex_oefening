package be.ucll.jpa.exercise3.manytomany;

import java.util.ArrayList;
import java.util.Collection;

public class Item {

    private Long id;

    private String test;

    private Collection<Order> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(final String test) {
        this.test = test;
    }

    public Collection<Order> getChildren() {
        return children;
    }

    public void setChildren(final Collection<Order> children) {
        this.children = children;
    }
}


