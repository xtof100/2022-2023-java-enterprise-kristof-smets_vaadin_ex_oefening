package be.ucll.jpa.entities.caching;

import javax.persistence.*;

@Entity
public class DescriptionTranslation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String language;
	private String translation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

}
