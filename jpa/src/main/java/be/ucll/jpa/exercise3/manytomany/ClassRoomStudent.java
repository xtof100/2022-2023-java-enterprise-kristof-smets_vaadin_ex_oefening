package be.ucll.jpa.exercise3.manytomany;

public class ClassRoomStudent {

    private Long id;

    private ClassRoom classRoom;

    private String someJoinTableProperty;

    private Student child;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public ClassRoom getParent() {
        return classRoom;
    }

    public void setParent(final ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public String getSomeJoinTableProperty() {
        return someJoinTableProperty;
    }

    public void setSomeJoinTableProperty(final String someJoinTableProperty) {
        this.someJoinTableProperty = someJoinTableProperty;
    }

    public Student getChild() {
        return child;
    }

    public void setChild(final Student child) {
        this.child = child;
    }
}
