package mk.uikm.finki.emtlab.service.domain;

import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;
import mk.uikm.finki.emtlab.model.projection.LongBookProjection;
import mk.uikm.finki.emtlab.model.projection.ShortBookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book findById(Long id);

    List<Book> findAll(BookCategory category);

    Book create(Book book);

    Book update(Long id, Book book);

    Book delete(Long id);

    Book softDelete(Long id);

    Book markAsRented(Long id);

    Page<Book> listBooks(
            BookCategory category,
            BookState state,
            String authorName,
            Boolean available,
            int page, int size, String sortBy);

    List<ShortBookProjection> findAllBy();

    List<LongBookProjection> findAllProjectedBy();

    List<Book> findWithEntityGraph();

    List<Book> findByDatePublished();
}
