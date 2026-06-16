package mk.uikm.finki.emtlab.service.application.impl;

import mk.uikm.finki.emtlab.model.domain.Country;
import mk.uikm.finki.emtlab.model.dto.CreateAuthorDto;
import mk.uikm.finki.emtlab.model.dto.DisplayAuthorDetailsDto;
import mk.uikm.finki.emtlab.model.dto.DisplayAuthorDto;
import mk.uikm.finki.emtlab.service.application.AuthorApplicationService;
import mk.uikm.finki.emtlab.service.domain.AuthorService;
import mk.uikm.finki.emtlab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public DisplayAuthorDto findById(Long id) {
        return DisplayAuthorDto.from(authorService.findById(id));
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public DisplayAuthorDto create(CreateAuthorDto createAuthorDto) {
        Country country = countryService.findById(createAuthorDto.countryId());
        return DisplayAuthorDto.from(authorService.create(createAuthorDto.toAuthor(country)));
    }

    @Override
    public DisplayAuthorDto update(Long id, CreateAuthorDto createAuthorDto) {
        Country country = countryService.findById(createAuthorDto.countryId());
        return DisplayAuthorDto.from(authorService.update(id, createAuthorDto.toAuthor(country)));
    }

    @Override
    public DisplayAuthorDto delete(Long id) {
        return DisplayAuthorDto.from(authorService.delete(id));
    }

    @Override
    public DisplayAuthorDetailsDto findWithDetailsById(Long id) {
        return DisplayAuthorDetailsDto.from(authorService.findById(id));
    }
}
