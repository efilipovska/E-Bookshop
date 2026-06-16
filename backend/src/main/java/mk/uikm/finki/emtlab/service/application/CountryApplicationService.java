package mk.uikm.finki.emtlab.service.application;

import mk.uikm.finki.emtlab.model.dto.CreateAuthorDto;
import mk.uikm.finki.emtlab.model.dto.CreateCountryDto;
import mk.uikm.finki.emtlab.model.dto.DisplayAuthorDto;
import mk.uikm.finki.emtlab.model.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    DisplayCountryDto findById(Long id);

    List<DisplayCountryDto> findAll();

    DisplayCountryDto create(CreateCountryDto createCountryDto);

    DisplayCountryDto update(Long id, CreateCountryDto createCountryDto);

    DisplayCountryDto delete(Long id);
}
