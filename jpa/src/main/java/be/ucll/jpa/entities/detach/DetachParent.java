package be.ucll.jpa.entities.detach;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DetachParent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String parentText;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private List<DetachChild> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentText() {
        return parentText;
    }

    public void setParentText(String parentText) {
        this.parentText = parentText;
    }

    public List<DetachChild> getChildren() {
        return children;
    }

    public void setChildren(List<DetachChild> children) {
        this.children = children;
    }
}
