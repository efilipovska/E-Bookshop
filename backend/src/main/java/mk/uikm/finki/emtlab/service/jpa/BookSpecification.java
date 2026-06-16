package mk.uikm.finki.emtlab.service.jpa;

import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> notDeleted() {
        return (root, query, cb) -> cb.equal(root.get("deleted"), false);
    }

    public static Specification<Book> byCategory(BookCategory category) {
        return FieldFilterSpecification.filterEqualsV(Book.class, "category", category);
    }

    public static Specification<Book> byCondition(BookState condition) {
        return FieldFilterSpecification.filterEqualsV(Book.class, "condition", condition);
    }

    public static Specification<Book> byAuthor(String author) {
        return FieldFilterSpecification.filterContainsText(Book.class, "author", author);
    }

    public static Specification<Book> hasAvailableCopies(Boolean available) {
        if (available == null) return null;
        return (root, query, cb) -> {
            if (available) {
                return cb.greaterThan(root.get("availableCopies"), 0);
            } else {
                return cb.equal(root.get("availableCopies"), 0);
            }
        };
    }
}