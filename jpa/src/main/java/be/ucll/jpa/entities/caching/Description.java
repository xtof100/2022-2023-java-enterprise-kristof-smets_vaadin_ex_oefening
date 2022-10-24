package be.ucll.jpa.entities.caching;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Collection<DescriptionTranslation> translations = new ArrayList<DescriptionTranslation>();

    public Long getId() {
        return id;
    }

    public Collection<DescriptionTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(Collection<DescriptionTranslation> translations) {
        this.translations = translations;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
