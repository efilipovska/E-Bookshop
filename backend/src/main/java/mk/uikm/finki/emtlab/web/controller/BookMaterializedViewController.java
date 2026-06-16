package mk.uikm.finki.emtlab.web.controller;

import mk.uikm.finki.emtlab.model.dto.DisplayBookMaterializedViewDto;
import mk.uikm.finki.emtlab.service.application.BookMaterializedViewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book-materialized-view")
public class BookMaterializedViewController {
    private final BookMaterializedViewApplicationService bookMaterializedViewApplicationService;

    public BookMaterializedViewController(BookMaterializedViewApplicationService bookMaterializedViewApplicationService) {
        this.bookMaterializedViewApplicationService = bookMaterializedViewApplicationService;
    }

    @GetMapping()
    public ResponseEntity<List<DisplayBookMaterializedViewDto>> viewBooks() {
        return ResponseEntity.ok(bookMaterializedViewApplicationService.findAll());
    }
}
