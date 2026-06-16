package mk.uikm.finki.emtlab.repository;

import mk.uikm.finki.emtlab.model.domain.BookActivityLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookActivityLogsRepository extends JpaRepository<BookActivityLogs, Long> {
}
