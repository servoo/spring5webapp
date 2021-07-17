package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  The @Controller will tell Spring that this class should be in the Spring context.
 *
 *  But what I don't get is, what exactly happens.
 *
 *  I've found that the @Controller annotation is used so that the 'Dispatcher Servlet'
 *  will use this class to look for a path for the given request, i.e. "/books" in this case.
 */
@Controller
public class BookController {

	private final BookRepository bookRepository;

	public BookController(final BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * why Model? What does this do? How does this parameter get passed to the method?
	 * Is this given by the client? (Is this the DOM?)
	 */
	@RequestMapping("/books")
	public String getBooks(Model model) {

		/**
		 * What does this exactly do?
		 */
		model.addAttribute("books", bookRepository.findAll());

		return "books/list";
	}
}
