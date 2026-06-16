package mk.uikm.finki.emtlab.web.controller;

import mk.uikm.finki.emtlab.model.dto.CreateCountryDto;
import mk.uikm.finki.emtlab.model.dto.DisplayCountryDto;
import mk.uikm.finki.emtlab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayCountryDto>> findAll() {
        return ResponseEntity.ok(countryApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(countryApplicationService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> create(@RequestBody CreateCountryDto createCountryDto) {
        return ResponseEntity.ok(countryApplicationService.create(createCountryDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayCountryDto> update(
            @PathVariable Long id,
            @RequestBody CreateCountryDto createCountryDto) {
        return ResponseEntity.ok(countryApplicationService.update(id, createCountryDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayCountryDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(countryApplicationService.delete(id));
    }
}
