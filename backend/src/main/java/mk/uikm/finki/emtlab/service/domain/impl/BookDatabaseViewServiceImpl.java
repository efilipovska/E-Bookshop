package mk.uikm.finki.emtlab.service.domain.impl;

import mk.uikm.finki.emtlab.model.view.BookDatabaseView;
import mk.uikm.finki.emtlab.repository.BookDatabaseViewRepository;
import mk.uikm.finki.emtlab.service.domain.BookDatabaseViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDatabaseViewServiceImpl implements BookDatabaseViewService{
    private final BookDatabaseViewRepository bookDatabaseViewRepository;

    public BookDatabaseViewServiceImpl(BookDatabaseViewRepository bookDatabaseViewRepository) {
        this.bookDatabaseViewRepository = bookDatabaseViewRepository;
    }

    @Override
    public List<BookDatabaseView> findAll() {
        return bookDatabaseViewRepository.findAll();
    }
}
