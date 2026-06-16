package mk.uikm.finki.emtlab.service.application.impl;

import mk.uikm.finki.emtlab.model.domain.Author;
import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.dto.CreateBookDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookDetailsDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookEGDto;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;
import mk.uikm.finki.emtlab.model.projection.LongBookProjection;
import mk.uikm.finki.emtlab.model.projection.ShortBookProjection;
import mk.uikm.finki.emtlab.service.application.BookApplicationService;
import mk.uikm.finki.emtlab.service.domain.AuthorService;
import mk.uikm.finki.emtlab.service.domain.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.SortArgumentResolver;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final SortArgumentResolver sortArgumentResolver;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService, SortArgumentResolver sortArgumentResolver) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.sortArgumentResolver = sortArgumentResolver;
    }

    @Override
    public DisplayBookDto findById(Long id) {
        return DisplayBookDto.from(bookService.findById(id));
    }

    @Override
    public List<DisplayBookDto> findAll(BookCategory category) {
        List<Book> books = bookService.findAll(category);
        return DisplayBookDto.from(books);
    }

    @Override
    public DisplayBookDto create(CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.authorId());
        return DisplayBookDto.from(bookService.create(createBookDto.toBook(author)));
    }

    @Override
    public DisplayBookDto update(Long id, CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.authorId());
        return DisplayBookDto.from(bookService.update(id, createBookDto.toBook(author)));
    }

    @Override
    public DisplayBookDto delete(Long id) {
        return DisplayBookDto.from(bookService.delete(id));
    }

    @Override
    public DisplayBookDto softDelete(Long id) {
        return DisplayBookDto.from(bookService.softDelete(id));
    }

    @Override
    public DisplayBookDto markAsRented(Long id) {
        return DisplayBookDto.from(bookService.markAsRented(id));
    }

    @Override
    public Page<DisplayBookDto> listBooks(
            BookCategory category,
            BookState state,
            String authorName,
            Boolean availableCopies,
            int page, int size, String sortBy) {
        return bookService.listBooks(category, state, authorName, availableCopies, page, size, sortBy).map(DisplayBookDto::from);
    }

    @Override
    public List<ShortBookProjection> findAllBy() {
        return bookService.findAllBy();
    }

    @Override
    public List<LongBookProjection> findAllProjectedBy() {
        return bookService.findAllProjectedBy();
    }

    @Override
    public List<DisplayBookEGDto> findWithEntityGraph() {
        return DisplayBookEGDto.from(bookService.findWithEntityGraph());
    }

    @Override
    public List<DisplayBookDto> findByDatePublished() {
        return DisplayBookDto.from(bookService.findByDatePublished());
    }

    @Override
    public DisplayBookDetailsDto findWithDetailsById(Long id) {
        return DisplayBookDetailsDto.from(bookService.findById(id));
    }
}
