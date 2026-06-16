package mk.uikm.finki.emtlab.jobs;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mk.uikm.finki.emtlab.repository.BookMaterializedViewRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MaterializedViewRefreshScheduler {
    private final BookMaterializedViewRepository bookMaterializedViewRepository;

    public MaterializedViewRefreshScheduler(BookMaterializedViewRepository bookMaterializedViewRepository) {
        this.bookMaterializedViewRepository = bookMaterializedViewRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void refreshBookView() {
        log.info("Refreshing BOOK_VIEW...");
        bookMaterializedViewRepository.refresh();
        log.info("BOOK_VIEW successfully refreshed.");
    }

}
