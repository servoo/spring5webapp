package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Why do we add <Author, Long>  after the extends CrudRepository?
 * Why do we extend CrudRepository?
 * What is '<>' ?
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
