package mk.uikm.finki.emtlab.service.domain.impl;

import mk.uikm.finki.emtlab.event.BookRentEvent;
import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.dto.DisplayBookDto;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;
import mk.uikm.finki.emtlab.model.exception.BookNotAvailableException;
import mk.uikm.finki.emtlab.model.exception.ResourceNotFoundException;
import mk.uikm.finki.emtlab.model.projection.LongBookProjection;
import mk.uikm.finki.emtlab.model.projection.ShortBookProjection;
import mk.uikm.finki.emtlab.repository.BookRepository;
import mk.uikm.finki.emtlab.service.domain.BookService;
import mk.uikm.finki.emtlab.service.jpa.BookSpecification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.uikm.finki.emtlab.service.jpa.FieldFilterSpecification.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %d not found!", id)));
    }

    @Override
    public List<Book> findAll(BookCategory category) {
        Specification<Book> spec = Specification.where(
                filterEqualsV(Book.class, "deleted", false)
        );
        Specification<Book> categorySpec = filterEqualsV(Book.class, "category", category);

        if (category != null) {
            spec = spec.and(categorySpec);
        }

        return bookRepository.findAll(spec);
    }


    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        Book existing = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %d not found!", id)));
        existing.setName(book.getName());
        existing.setAuthor(book.getAuthor());
        existing.setState(book.getState());
        existing.setCategory(book.getCategory());
        existing.setAvailableCopies(book.getAvailableCopies());
        existing.setDeleted(book.getDeleted());
        return bookRepository.save(existing);
    }

    @Override
    public Book delete(Long id) {
        Book existing = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %d not found!", id)));
        bookRepository.delete(existing);
        return existing;
    }

    @Override
    public Book softDelete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %d not found!", id)));
        book.setDeleted(true);
        return bookRepository.save(book);
    }

    @Override
    public Book markAsRented(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %d not found!", id)));

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException(id);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        applicationEventPublisher.publishEvent(
                new BookRentEvent(book.getId(), book.getName(), book.getAvailableCopies())
        );

        return book;
    }

    @Override
    public Page<Book> listBooks(
            BookCategory category,
            BookState state,
            String authorName,
            Boolean availableCopies,
            int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Specification<Book> spec = (root, query, cb) -> cb.conjunction();

        Specification<Book> notDeletedSpec = BookSpecification.notDeleted();
        Specification<Book> categorySpec = BookSpecification.byCategory(category);
        Specification<Book> conditionSpec = BookSpecification.byCondition(state);
        Specification<Book> authorSpec = BookSpecification.byAuthor(authorName);
        Specification<Book> availableSpec = BookSpecification.hasAvailableCopies(availableCopies);

        spec = spec.and(notDeletedSpec);
        if (categorySpec != null) spec = spec.and(categorySpec);
        if (conditionSpec != null) spec = spec.and(conditionSpec);
        if (authorSpec != null) spec = spec.and(authorSpec);
        if (availableSpec != null) spec = spec.and(availableSpec);

        return bookRepository.findAll(spec, pageable);
    }

    @Override
    public List<ShortBookProjection> findAllBy() {
        return bookRepository.findAllBy();
    }

    @Override
    public List<LongBookProjection> findAllProjectedBy()  {
        return bookRepository.findAllProjectedBy();
    }

    @Override
    public List<Book> findWithEntityGraph() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByDatePublished() {
        return bookRepository.findTop10ByOrderByDatePublishedDesc();
    }
}

