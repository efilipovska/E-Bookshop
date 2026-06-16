package mk.uikm.finki.emtlab.service.domain.impl;

import mk.uikm.finki.emtlab.model.domain.BookActivityLogs;
import mk.uikm.finki.emtlab.repository.BookActivityLogsRepository;
import mk.uikm.finki.emtlab.service.domain.BookActivityLogsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookActivityLogsServiceImpl implements BookActivityLogsService {
    private final BookActivityLogsRepository bookActivityLogsRepository;

    public BookActivityLogsServiceImpl(BookActivityLogsRepository bookActivityLogsRepository) {
        this.bookActivityLogsRepository = bookActivityLogsRepository;
    }

    @Override
    public List<BookActivityLogs> findAll() {
        return bookActivityLogsRepository.findAll();
    }
}
