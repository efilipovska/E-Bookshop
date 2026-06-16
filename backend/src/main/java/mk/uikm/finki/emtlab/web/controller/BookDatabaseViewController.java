package mk.uikm.finki.emtlab.web.controller;

import mk.uikm.finki.emtlab.model.dto.DisplayBookDatabaseViewDto;
import mk.uikm.finki.emtlab.service.application.BookDatabaseViewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book-db-view")
public class BookDatabaseViewController {
    private final BookDatabaseViewApplicationService bookDatabaseViewApplicationService;

    public BookDatabaseViewController(BookDatabaseViewApplicationService bookDatabaseViewApplicationService) {
        this.bookDatabaseViewApplicationService = bookDatabaseViewApplicationService;
    }

    @GetMapping()
    public ResponseEntity<List<DisplayBookDatabaseViewDto>> viewBooks() {
        return ResponseEntity.ok(bookDatabaseViewApplicationService.findAll());
    }
}
