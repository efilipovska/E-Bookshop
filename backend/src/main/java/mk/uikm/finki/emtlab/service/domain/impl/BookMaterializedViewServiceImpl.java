package mk.uikm.finki.emtlab.service.domain.impl;

import mk.uikm.finki.emtlab.model.view.BookMaterializedView;
import mk.uikm.finki.emtlab.repository.BookMaterializedViewRepository;
import mk.uikm.finki.emtlab.service.domain.BookMaterializedViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMaterializedViewServiceImpl implements BookMaterializedViewService {
    private final BookMaterializedViewRepository bookMaterializedViewRepository;

    public BookMaterializedViewServiceImpl(BookMaterializedViewRepository bookMaterializedViewRepository) {
        this.bookMaterializedViewRepository = bookMaterializedViewRepository;
    }

    @Override
    public List<BookMaterializedView> findAll() {
        return bookMaterializedViewRepository.findAll();
    }
}
