package guru.springframework.spring5webapp.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * We created POJO's which will be annotated with @Entity so that JPA can do it's mapping work.
 * JPA = ORM tool (Object Relational Mapping) -> when you get into mapping, you will get 'leakage'.
 *
 * I learned that when an entity is marked, it will tell you that entities need to have some kind of ID.
 * The course tells that the identity value (ID) will be 'leaiking' up into our object model.
 *
 * Hibernate will be used to persist these mapped objects via JPA into the DB.
 *

 */
@Entity
public class Author {

	/**
	 * Here we are telling that SQL will be generating an ID for us with the @GeneratedValue(strategy = ...) annotation
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String lastName;

	/**
	 * Here we define a relationship, a manyToMany in specific, because a book or author can have multiple of both parties
	 *
	 * The JoinTable is a table which will contain both parties
	 *
	 * A 'mappedBy' needs to be used on the 'owning' side of the relationship, if this is bidirectional you can choose on what side
	 * it is used. Here it is more 'logical' to do this on the author in stead of on the book.
	 */
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books;

	public Author() {
	}

	public Author(final String firstName, final String lastName, final Set<Book> books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
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

	/**
	 * How do you use this function? do you call this after an @Author object?
	 *
	 * 		i.e.?
	 * 		Book book = new Book(bla bla);
	 * 		String bookAsString = book.toString();
	 */
	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", books=" + books +
				'}';
	}

	/**
	 * What do these two generated equals and hashCode functions do?!
	 * 		I see what they do, but still..
	 * Why do we need them?
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final Author author = (Author) o;

		return id != null ? id.equals(author.id) : author.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
