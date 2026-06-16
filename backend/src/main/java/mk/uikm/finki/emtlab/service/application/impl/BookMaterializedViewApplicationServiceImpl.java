package mk.uikm.finki.emtlab.service.application.impl;

import mk.uikm.finki.emtlab.model.dto.DisplayBookMaterializedViewDto;
import mk.uikm.finki.emtlab.service.application.BookMaterializedViewApplicationService;
import mk.uikm.finki.emtlab.service.domain.BookMaterializedViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMaterializedViewApplicationServiceImpl implements BookMaterializedViewApplicationService {
    private final BookMaterializedViewService bookMaterializedViewService;

    public BookMaterializedViewApplicationServiceImpl(BookMaterializedViewService bookMaterializedViewService) {
        this.bookMaterializedViewService = bookMaterializedViewService;
    }

    @Override
    public List<DisplayBookMaterializedViewDto> findAll() {
        return DisplayBookMaterializedViewDto.from(bookMaterializedViewService.findAll());
    }
}
