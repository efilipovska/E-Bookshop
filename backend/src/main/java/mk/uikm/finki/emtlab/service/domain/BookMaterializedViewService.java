package mk.uikm.finki.emtlab.service.domain;

import mk.uikm.finki.emtlab.model.view.BookMaterializedView;

import java.util.List;

public interface BookMaterializedViewService {
    List<BookMaterializedView> findAll();
}
