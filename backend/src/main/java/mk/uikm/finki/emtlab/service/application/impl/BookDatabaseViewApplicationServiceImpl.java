package mk.uikm.finki.emtlab.service.application.impl;

import mk.uikm.finki.emtlab.model.dto.DisplayBookDatabaseViewDto;
import mk.uikm.finki.emtlab.service.application.BookDatabaseViewApplicationService;
import mk.uikm.finki.emtlab.service.domain.BookDatabaseViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDatabaseViewApplicationServiceImpl implements BookDatabaseViewApplicationService {
    private final BookDatabaseViewService bookDatabaseViewService;

    public BookDatabaseViewApplicationServiceImpl(BookDatabaseViewService bookDatabaseViewService) {
        this.bookDatabaseViewService = bookDatabaseViewService;
    }

    @Override
    public List<DisplayBookDatabaseViewDto> findAll() {
        return DisplayBookDatabaseViewDto.from(bookDatabaseViewService.findAll());
    }
}
