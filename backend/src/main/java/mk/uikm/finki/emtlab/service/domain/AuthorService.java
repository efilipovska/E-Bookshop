package mk.uikm.finki.emtlab.service.domain;

import mk.uikm.finki.emtlab.model.domain.Author;
import mk.uikm.finki.emtlab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author findById(Long id);

    List<Author> findAll();

    Author create(Author author);

    Author update(Long id, Author author);

    Author delete(Long id);
}
