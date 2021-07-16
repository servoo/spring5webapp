package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
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
	private final PublisherRepository publisherRepository;

	/**
	 * So this constructor will bring the AutorRepository and BookRepository 'inside of the Spring context'
	 * What does this mean and why do we need it?
	 *
	 * I understand that we need to use these repo's to bootstrap our
	 * data in this class?
	 */
	public BootStrapData(
			final AuthorRepository authorRepository,
			final BookRepository bookRepository,
			final PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	/**
	 * This comes forth from the CommandLineRunner and is used to run the bean?
	 */
	@Override
	public void run(String... args) throws Exception {

		System.out.println("Started in Bootstrap");

		Publisher publisher = new Publisher();
		publisher.setName("SFG Publishing");
		publisher.setCity("St Petersburg");
		publisher.setState("FL");

		/**
		 * If I save publisher AFTER ddd.setPublisher(publisher), Spring throws an error because we (object) are (is) referencing an unsaved transient instance.
		 * Why is this?
		 *
		 * (https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save)
		 * In this post, it is said that this could be circumvented if you used cascade = {CascadeType.ALL} on the parents reference to the child
		 * so that the parent/ child is saved as well (at the same time?).
		 *
		 * So I've learned that the order in which instances are saved to the DB really matters and that there are options for cascading and such. I should take a further look
		 * into this matter.
		 *
		 * Also, what is flushing? Because the error stated that the instances should be saved before 'flushing' occurs.
		 */
		publisherRepository.save(publisher);

		System.out.println("Publisher Count: " + publisherRepository.count());

		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);

		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(publisher);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "3939459459");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);

		authorRepository.save(rod);
		bookRepository.save(noEJB);
		publisherRepository.save(publisher);

		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
	}
}
