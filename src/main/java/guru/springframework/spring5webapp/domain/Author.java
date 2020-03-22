package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {

    /**
     * Id Annotation
     * -----------------------------------------------------------
     * "Leakage"
     * We have a pure object model (our POJO), which does not
     * include or require an ID field.
     * JPA requires entities to have an ID field, so that it can
     * store and retrieve them.
     * In order to enable our POJOs to become JPA entities, the
     * ID field "leaks" into our pure object model.
     *
     * GeneratedValue Annotation
     * -----------------------------------------------------------
     * We tell Hibernate (the JPA provider) how the ID value will
     * be generated. In this case, the underlying database will be
     * managing and providing this property.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    /**
     * Because a constructor exists that does not require the Author
     * to have Books, we initialize it as a HashSet here.
     *
     * ManyToMany Annotation
     * -----------------------------------------------------------
     * When using a ManyToMany annotation, the relationship must
     * be complete on both sides (i.e. the annotation is required
     * to be on both linked entities). See the Book class for more.
     */
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    /**
     * JPA does require a zero-args constructor.
     */
    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName, Set<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    /**
     * Equals
     * -----------------------------------------------------------
     * The standard equals and hash code that we get from Java
     * will not suffice our needs for JPA entities due to the
     * "leakage" mentioned above. We need to associate equality
     * with the identity field of the object, which essentially
     * equates objects to records within the database. We need
     * to provide some logic around the id property, so that if
     * two objects have the same id, things like Sets will consider
     * them as the same object (even if some of their fields have
     * different values).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return Objects.equals(id, author.id);
    }

    /**
     * Hashcode
     * -----------------------------------------------------------
     * Same here, we override the hashCode method and provide
     * some logic around the id property only.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * ToString
     * -----------------------------------------------------------
     * If we were using an entity with sensitive data, we would
     * exclude those fields from the toString implementation.
     */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
