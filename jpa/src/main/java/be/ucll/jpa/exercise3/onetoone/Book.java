package be.ucll.jpa.exercise3.onetoone;

public class Book {

	private Long id;
	private String bookName;
	private BookDetail bookDetail;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(final String bookName) {
		this.bookName = bookName;
	}

	public BookDetail getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(final BookDetail bookDetail) {
		this.bookDetail = bookDetail;
	}
}
