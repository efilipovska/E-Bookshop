package mk.uikm.finki.emtlab.model.dto;

import jakarta.validation.constraints.Positive;
import mk.uikm.finki.emtlab.model.domain.Author;
import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateBookDto(
        String name,
        BookCategory category,
        Long authorId,
        BookState state,
        @Positive
        Integer availableCopies,
        boolean deleted,
        LocalDate datePublished
) {
    public Book toBook(Author author){
        return new Book(name, category, author, state, availableCopies, deleted, datePublished);
    }
}
