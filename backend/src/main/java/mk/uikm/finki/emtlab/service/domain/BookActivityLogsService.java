package mk.uikm.finki.emtlab.service.domain;

import mk.uikm.finki.emtlab.model.domain.BookActivityLogs;
import java.util.List;

public interface BookActivityLogsService {
    List<BookActivityLogs> findAll();
}
