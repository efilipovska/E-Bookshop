package mk.uikm.finki.emtlab.listener;

import lombok.extern.slf4j.Slf4j;
import mk.uikm.finki.emtlab.event.BookRentEvent;
import mk.uikm.finki.emtlab.model.domain.BookActivityLogs;
import mk.uikm.finki.emtlab.repository.BookActivityLogsRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class BookUnavailableListener {
    private final BookActivityLogsRepository bookActivityLogsRepository;

    public BookUnavailableListener(BookActivityLogsRepository bookActivityLogsRepository) {
        this.bookActivityLogsRepository = bookActivityLogsRepository;
    }

    @EventListener
    public void onBookRented(BookRentEvent event) {
        log.info("Book rented: {} (remaining copies: {})", event.bookName(), event.remainingCopies());

        BookActivityLogs activity = new BookActivityLogs();
        activity.setBookName(event.bookName());
        activity.setEventTime(LocalDateTime.now());
        activity.setEventType("BOOK_RENTED");

        bookActivityLogsRepository.save(activity);

        if (event.remainingCopies() == 0) {
            log.warn("Book '{}' is now UNAVAILABLE!", event.bookName());

            BookActivityLogs unavailableActivity = new BookActivityLogs();
            unavailableActivity.setBookName(event.bookName());
            unavailableActivity.setEventTime(LocalDateTime.now());
            unavailableActivity.setEventType("BOOK_UNAVAILABLE");

            bookActivityLogsRepository.save(unavailableActivity);
        }
    }
}