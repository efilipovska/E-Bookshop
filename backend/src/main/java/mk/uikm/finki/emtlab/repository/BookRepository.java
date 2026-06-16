package mk.uikm.finki.emtlab.repository;

import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.projection.LongBookProjection;
import mk.uikm.finki.emtlab.model.projection.ShortBookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<ShortBookProjection> findAllBy();

    List<LongBookProjection> findAllProjectedBy();

    @EntityGraph(value = "book-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAll();

    List<Book> findTop10ByOrderByDatePublishedDesc();
}
