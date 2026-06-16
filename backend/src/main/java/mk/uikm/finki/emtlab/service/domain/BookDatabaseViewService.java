package mk.uikm.finki.emtlab.service.domain;

import mk.uikm.finki.emtlab.model.view.BookDatabaseView;

import java.util.List;

public interface BookDatabaseViewService {
    List<BookDatabaseView> findAll();
}
