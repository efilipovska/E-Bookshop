package mk.uikm.finki.emtlab.web.controller;

import mk.uikm.finki.emtlab.model.domain.BookActivityLogs;
import mk.uikm.finki.emtlab.service.domain.BookActivityLogsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book-activities")
public class BookActivityLogsController {
    private final BookActivityLogsService bookActivityLogsService;

    public BookActivityLogsController(BookActivityLogsService bookActivityLogsService) {
        this.bookActivityLogsService = bookActivityLogsService;
    }

    @GetMapping
    public List<BookActivityLogs> getActivities() {
        return bookActivityLogsService.findAll();
    }
}
