package mk.uikm.finki.emtlab.web.controller;

import jakarta.validation.Valid;
import mk.uikm.finki.emtlab.model.dto.CreateBookDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookDetailsDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookEGDto;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;
import mk.uikm.finki.emtlab.model.projection.LongBookProjection;
import mk.uikm.finki.emtlab.model.projection.ShortBookProjection;
import mk.uikm.finki.emtlab.service.application.BookApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookApplicationService bookAplicationService;

    public BookController(BookApplicationService bookAplicationService) {
        this.bookAplicationService = bookAplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll(
            @RequestParam(required = false) BookCategory category
    ){
        return ResponseEntity.ok(bookAplicationService.findAll(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookAplicationService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> create(@RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.ok(bookAplicationService.create(createBookDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayBookDto> update(
            @PathVariable Long id,
            @RequestBody CreateBookDto createBookDto
    ) {
        return ResponseEntity.ok(bookAplicationService.update(id, createBookDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayBookDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(bookAplicationService.delete(id));
    }

    @PatchMapping("/{id}/softDelete")
    public ResponseEntity<DisplayBookDto> softDelete(@PathVariable Long id) {
        return ResponseEntity.ok(bookAplicationService.softDelete(id));
    }

    @PatchMapping("/{id}/rent")
    public ResponseEntity<DisplayBookDto> markAsRented(@PathVariable Long id) {
        return ResponseEntity.ok(bookAplicationService.markAsRented(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DisplayBookDto>> findAll(
            @RequestParam(required = false) BookCategory category,
            @RequestParam(required = false) BookState state,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        return ResponseEntity.ok(bookAplicationService.listBooks(category, state, authorName, available, page, size, sortBy));
    }

    @GetMapping("/longprojection")
    public ResponseEntity<List<LongBookProjection>> findAllLongBookPrj() {
        return ResponseEntity.ok(bookAplicationService.findAllProjectedBy());
    }

    @GetMapping("/shortprojection")
    public ResponseEntity<List<ShortBookProjection>> findAllShortBookPrj() {
        return ResponseEntity.ok(bookAplicationService.findAllBy());
    }

    @GetMapping("/entity-graph")
    public ResponseEntity<List<DisplayBookEGDto>> listWithEntityGraph() {
        return ResponseEntity.ok(bookAplicationService.findWithEntityGraph());
    }

    @GetMapping("/top-books")
    public ResponseEntity<List<DisplayBookDto>> findTopBooks() {
        return ResponseEntity.ok(bookAplicationService.findByDatePublished());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<DisplayBookDetailsDto> findWithDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(bookAplicationService.findWithDetailsById(id));
    }
}