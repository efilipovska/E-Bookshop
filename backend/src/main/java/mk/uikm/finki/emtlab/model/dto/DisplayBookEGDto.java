package mk.uikm.finki.emtlab.model.dto;

import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;

import java.util.List;

public record DisplayBookEGDto(
        Long id,
        String name,
        BookCategory category,
        BookState state,
        Integer availableCopies,
        boolean deleted,
        String authorFirstName,
        String authorLastName,
        String countryName
) {
    public static DisplayBookEGDto from(Book book) {
        return new DisplayBookEGDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getState(),
                book.getAvailableCopies(),
                book.getDeleted(),
                book.getAuthor().getName(),
                book.getAuthor().getSurname(),
                book.getAuthor().getCountry().getName()
        );
    }

    public static List<DisplayBookEGDto> from(List<Book> books) {
        return books.stream()
                .map(DisplayBookEGDto::from)
                .toList();
    }
}