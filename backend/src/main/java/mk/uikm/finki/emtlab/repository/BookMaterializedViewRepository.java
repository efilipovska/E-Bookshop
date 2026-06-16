package mk.uikm.finki.emtlab.repository;

import mk.uikm.finki.emtlab.model.view.BookMaterializedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMaterializedViewRepository extends JpaRepository<BookMaterializedView, Long> {
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW book_materialized_view", nativeQuery = true)
    void refresh();
}
