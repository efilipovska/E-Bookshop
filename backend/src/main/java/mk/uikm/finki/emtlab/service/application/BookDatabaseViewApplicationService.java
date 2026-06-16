package mk.uikm.finki.emtlab.service.application;

import mk.uikm.finki.emtlab.model.dto.DisplayBookDatabaseViewDto;

import java.util.List;

public interface BookDatabaseViewApplicationService {
    List<DisplayBookDatabaseViewDto> findAll();
}
