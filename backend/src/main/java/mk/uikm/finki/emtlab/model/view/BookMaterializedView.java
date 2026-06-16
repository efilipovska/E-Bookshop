package mk.uikm.finki.emtlab.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "book_materialized_view")
public class BookMaterializedView {
    @Id
    private String category;

    private int totalBooks;
    private int totalAvailable;
    private int notGoodCondition;
}
