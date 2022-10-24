package be.ucll.jpa.exercise3.manytomany;

import java.util.ArrayList;
import java.util.Collection;

public class ClassRoom {

    private Long id;

    private String test;

    private Collection<ClassRoomStudent> parentChildren = new ArrayList<>();

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

    public Collection<ClassRoomStudent> getParentChildren() {
        return parentChildren;
    }

    public void setParentChildren(final Collection<ClassRoomStudent> parentChildren) {
        this.parentChildren = parentChildren;
    }
}


