package be.ucll.jpa.entities.caching;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "CacheItem")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Description itemDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Description getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(Description itemDescription) {
        this.itemDescription = itemDescription;
    }

}
