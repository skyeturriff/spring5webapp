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
 * Spring will look for instances of {@link CommandLineRunner},
 * and when it finds them, it will call {@code run}.
 *
 * Using the {@code @Component} annotation allows Spring to
 * detect this class as a managed component.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    /**
     * When Spring implements this component and brings it
     * into the Spring context, it will perform dependency
     * injection into the Constructor and provide instances
     * of the two required Repositories.
     */
    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    /**
     * The JPA provider (Hibernate) will generate the SQL statements to
     * create the database tables based on our JPA definitions. This
     * all happens transparently in the configurable settings.
     *
     * So Hibernate will initialize the database, and create the SQL
     * statements to insert new data for us.
     */
    @Override
    public void run(String... args) throws Exception {

        // Create entities
        Publisher publisher = new Publisher("Skye Turriff", "123 Somewhere", "Someplace", "ON", "A1A1A1");

        // Persist
        publisherRepository.save(publisher);

        // Create entities
        Author author1 = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "12345");

        // Create relationships
        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);

        // Persist
        authorRepository.save(author1);
        bookRepository.save(book1);
        publisherRepository.save(publisher);

        // Create entities
        Author author2 = new Author("Rod", "Johnson");
        Book book2 = new Book("J2EE Development without EJB", "33444555");

        // Create relationships
        author2.getBooks().add(book2);
        book2.getAuthors().add(author2);
        book2.setPublisher(publisher);
        publisher.getBooks().add(book2);

        // Persist
        authorRepository.save(author2);
        bookRepository.save(book2);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of books assigned to publisher: " + publisher.getBooks().size());
    }
}
