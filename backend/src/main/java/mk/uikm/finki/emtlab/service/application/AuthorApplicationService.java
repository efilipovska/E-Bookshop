package mk.uikm.finki.emtlab.service.application;

import mk.uikm.finki.emtlab.model.dto.CreateAuthorDto;
import mk.uikm.finki.emtlab.model.dto.DisplayAuthorDetailsDto;
import mk.uikm.finki.emtlab.model.dto.DisplayAuthorDto;

import java.util.List;

public interface AuthorApplicationService {
    DisplayAuthorDto findById(Long id);

    List<DisplayAuthorDto> findAll();

    DisplayAuthorDto create(CreateAuthorDto createAuthorDto);

    DisplayAuthorDto update(Long id, CreateAuthorDto createAuthorDto);

    DisplayAuthorDto delete(Long id);

    DisplayAuthorDetailsDto findWithDetailsById(Long id);
}
