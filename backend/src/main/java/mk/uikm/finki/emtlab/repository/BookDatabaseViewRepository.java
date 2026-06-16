package mk.uikm.finki.emtlab.repository;

import mk.uikm.finki.emtlab.model.view.BookDatabaseView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDatabaseViewRepository extends JpaRepository<BookDatabaseView, Long> {
}
