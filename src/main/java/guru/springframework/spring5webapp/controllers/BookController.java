package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller Annotation
 * -----------------------------------------------------------
 * Adapts this class to actually be a Controller within the
 * Spring framework. This Spring Stereotype tells the Spring
 * framework that our intent is to make this into a Spring MVC
 * Controller.
 */
@Controller
public class BookController {

    /**
     * Bring in an instance of the BookRepository so that we may
     * use it when responding to requests for Books.
     */
    private final BookRepository bookRepository;

    /**
     * Because this is a Controller, it is a Spring managed
     * component. When  Spring instantiates it, it will inject
     * (using Spring Dependency Injection) an instance of the
     * BookRepository.
     */
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * When Spring gets a request to this URL (/path), it will
     * execute this getBooks() method. Spring will provide the
     * getBooks() method with a Model object. The method code
     * will use the injected BookRepository to get a list of
     * Books out of the database. An attribute called "books"
     * is added to the Model, and the list of Books returned
     * by the repo is assigned to this attribute.
     *
     * RequestMapping Annotation
     * -----------------------------------------------------------
     * Maps this method to a specific URL on the server. We pass
     * the annotation the path we want to use for this request.
     * So when an action is done against the URL /books, this
     * method will be invoked by the Spring MVC framework.
     *
     * We pass in the Model, which is what will be returned to
     * the View.
     */
    @RequestMapping("/books")
    public String getBooks(Model model) {

        /*
        In this method, we want to enhance the Model with a list of
        Books. We add an attribute to our model that hold the Books
        returned from the repo. When the Model is returned to the
        View, it will have this attribute "books" present. The View
        will be able to utilize that value inside of the View layer
        to apply to necessary View to return back to the Client.
         */
        model.addAttribute("books", bookRepository.findAll());

        /*
        This returns the name of the View. This tells Spring MVC
        that we want to apply the View called "list". This will
        tell it to look for the "list" template inside the directory
        "books".
         */
        return "books/list";
    }
}
