package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Under the scenes, Spring Data repositories take care of all the
 * Hibernate commands, all the transactional commands, etc. (hiding
 * all the boilerplate JDBC code from us). This abstracts the burden
 * away from developers and allows us to just focus on providing
 * business functionality.
 *
 * The Repository pattern is a competing pattern to the DAO object
 * (used heavily in Java EE projects). A Repository object is
 * responsible for the persistence and query operations.
 *
 * An implementation will be provided to us by Spring Data JPA at
 * runtime, with implementations for all of the methods specified in
 * the {@link CrudRepository} interface.
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
