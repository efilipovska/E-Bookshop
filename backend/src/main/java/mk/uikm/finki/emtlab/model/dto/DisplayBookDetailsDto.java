package mk.uikm.finki.emtlab.model.dto;

import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DisplayBookDetailsDto(
        Long id,
        String name,
        BookCategory category,
        BookState state,
        Integer availableCopies,
        boolean deleted,
        LocalDate datePublished,
        DisplayAuthorDto author
) {
    public static DisplayBookDetailsDto from(Book book) {
        return new DisplayBookDetailsDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getState(),
                book.getAvailableCopies(),
                book.getDeleted(),
                book.getDatePublished(),
                DisplayAuthorDto.from(book.getAuthor())
        );
    }
}

