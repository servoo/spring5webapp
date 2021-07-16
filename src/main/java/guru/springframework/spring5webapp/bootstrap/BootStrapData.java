package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * So @Component annotation tells that this is a spring managed component
 * What does this do for us?
 *
 * Why do we implement CommandLineRunner?
 */
@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	/**
	 * So this constructor will bring the AutorRepository and BookRepository 'inside of the Spring context'
	 * What does this mean and why do we need it?
	 *
	 * I understand that we need to use these repo's to bootstrap our
	 * data in this class?
	 */
	public BootStrapData(final AuthorRepository authorRepository, final BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	/**
	 * This comes forth from the CommandLineRunner and is used to run the bean?
	 */
	@Override
	public void run(final String... args) throws Exception {

		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "321321");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		authorRepository.save(rod);
		bookRepository.save(noEJB);

		System.out.println("Started in Bootstrap");
		System.out.println("Number of books: " + bookRepository.count());
	}
}
