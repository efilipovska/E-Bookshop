package mk.uikm.finki.emtlab.model.dto;

import mk.uikm.finki.emtlab.model.view.BookDatabaseView;

import java.util.List;

public record DisplayBookDatabaseViewDto(
        Long id,
        String name,
        String category,
        String state,
        Integer availableCopies,
        String authorFullName,
        String countryName

) {
    public static DisplayBookDatabaseViewDto from(BookDatabaseView bookDatabaseView) {
        return new DisplayBookDatabaseViewDto(
                bookDatabaseView.getId(),
                bookDatabaseView.getName(),
                bookDatabaseView.getCategory(),
                bookDatabaseView.getState(),
                bookDatabaseView.getAvailableCopies(),
                bookDatabaseView.getAuthorFullName(),
                bookDatabaseView.getCountryName()
        );
    }

    public static List<DisplayBookDatabaseViewDto> from(List<BookDatabaseView> bookDatabaseView) {
        return bookDatabaseView
                .stream()
                .map(DisplayBookDatabaseViewDto::from)
                .toList();
    }
}

