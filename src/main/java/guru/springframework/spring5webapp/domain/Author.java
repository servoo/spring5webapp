package guru.springframework.spring5webapp.domain;

import java.util.Set;

public class Author {
	private String firstName;
	private String lastName;
	private Set<Book> books;

	public Author() {
	}

	public Author(final String firstName, final String lastName, final Set<Book> books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = books;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(final Set<Book> books) {
		this.books = books;
	}
}
