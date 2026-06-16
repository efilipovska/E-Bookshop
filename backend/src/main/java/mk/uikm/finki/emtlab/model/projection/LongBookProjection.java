package mk.uikm.finki.emtlab.model.projection;

import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;

public interface LongBookProjection {
    Long getId();
    String getName();
    BookCategory getCategory();
    BookState getState();
    Integer getAvailableCopies();

    AuthorInfo getAuthor();

    interface AuthorInfo {
        String getName();
        String getSurname();
        CountryInfo getCountry();

        interface CountryInfo {
            String getName();  // or state field
        }
    }
}