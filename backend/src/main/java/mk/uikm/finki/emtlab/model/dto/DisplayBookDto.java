package mk.uikm.finki.emtlab.model.dto;

import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DisplayBookDto(
        Long id,
        String name,
        BookCategory category,
        Long authorId,
        BookState state,
        Integer availableCopies,
        boolean deleted,
        LocalDate datePublished

) {
    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getState(),
                book.getAvailableCopies(),
                book.getDeleted(),
                book.getDatePublished()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books){
        return books
                .stream()
                .map(DisplayBookDto::from)
                .toList();
    }
}
