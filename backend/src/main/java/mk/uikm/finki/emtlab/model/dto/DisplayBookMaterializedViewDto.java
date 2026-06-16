package mk.uikm.finki.emtlab.model.dto;

import mk.uikm.finki.emtlab.model.view.BookMaterializedView;

import java.util.List;

public record DisplayBookMaterializedViewDto(
        String category,
        int totalBooks,
        int totalAvailable,
        int notGoodCondition

) {
    public static DisplayBookMaterializedViewDto from(BookMaterializedView bookMaterializedView) {
        return new DisplayBookMaterializedViewDto(
                bookMaterializedView.getCategory(),
                bookMaterializedView.getTotalBooks(),
                bookMaterializedView.getTotalAvailable(),
                bookMaterializedView.getNotGoodCondition()
        );
    }

    public static List<DisplayBookMaterializedViewDto> from(List<BookMaterializedView> bookMaterializedViews) {
        return bookMaterializedViews
                .stream()
                .map(DisplayBookMaterializedViewDto::from)
                .toList();
    }
}
