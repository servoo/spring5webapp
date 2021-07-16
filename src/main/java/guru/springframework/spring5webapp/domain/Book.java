package guru.springframework.spring5webapp.domain;

import java.util.Set;

public class Book {
	private String title;
	private String isbn;
	private Set<Author> authors;

	public Book() {
	}

	public Book(final String title, final String isbn, final Set<Author> authors) {
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(final Set<Author> authors) {
		this.authors = authors;
	}
}
