package mk.uikm.finki.emtlab.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "book_database_view")
public class BookDatabaseView {

    @Id
    private Long id;

    private String name;
    private String category;
    private String state;
    private Integer availableCopies;
    private String authorFullName;
    private String countryName;
}