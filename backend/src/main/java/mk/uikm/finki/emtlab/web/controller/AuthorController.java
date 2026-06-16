package mk.uikm.finki.emtlab.web.controller;

import mk.uikm.finki.emtlab.model.dto.CreateAuthorDto;
import mk.uikm.finki.emtlab.model.dto.DisplayAuthorDetailsDto;
import mk.uikm.finki.emtlab.model.dto.DisplayAuthorDto;
import mk.uikm.finki.emtlab.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping()
    public ResponseEntity<List<DisplayAuthorDto>> findAll() {
        return ResponseEntity.ok((authorApplicationService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(authorApplicationService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> create(@RequestBody CreateAuthorDto createAuthorDto){
        return ResponseEntity.ok(authorApplicationService.create(createAuthorDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayAuthorDto> update(
            @PathVariable Long id,
            @RequestBody CreateAuthorDto createAuthorDto) {
        return ResponseEntity.ok(authorApplicationService.update(id, createAuthorDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayAuthorDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(authorApplicationService.delete(id));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<DisplayAuthorDetailsDto> findWithDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(authorApplicationService.findWithDetailsById(id));
    }
}
