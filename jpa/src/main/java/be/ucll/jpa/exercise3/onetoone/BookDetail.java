package be.ucll.jpa.exercise3.onetoone;

public class BookDetail {

	private Long id;

	private String isbn;

	private String publisher;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(final String publisher) {
		this.publisher = publisher;
	}
}
