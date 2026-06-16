package mk.uikm.finki.emtlab.service.application;

import mk.uikm.finki.emtlab.model.dto.DisplayBookMaterializedViewDto;

import java.util.List;

public interface BookMaterializedViewApplicationService {
    List<DisplayBookMaterializedViewDto> findAll();
}
