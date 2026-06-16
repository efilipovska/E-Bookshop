package mk.uikm.finki.emtlab.service.application;

import mk.uikm.finki.emtlab.model.domain.Book;
import mk.uikm.finki.emtlab.model.dto.CreateBookDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookDetailsDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookDto;
import mk.uikm.finki.emtlab.model.dto.DisplayBookEGDto;
import mk.uikm.finki.emtlab.model.enums.BookCategory;
import mk.uikm.finki.emtlab.model.enums.BookState;
import mk.uikm.finki.emtlab.model.projection.LongBookProjection;
import mk.uikm.finki.emtlab.model.projection.ShortBookProjection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    DisplayBookDto findById(Long id);

    List<DisplayBookDto> findAll(BookCategory category);

    DisplayBookDto create(CreateBookDto createBookDto);

    DisplayBookDto update(Long id, CreateBookDto createBookDto);

    DisplayBookDto delete(Long id);

    DisplayBookDto softDelete(Long id);

    DisplayBookDto markAsRented(Long id);

    Page<DisplayBookDto> listBooks(
            BookCategory category,
            BookState state,
            String authorName,
            Boolean available,
            int page, int size, String sortBy);

    List<ShortBookProjection> findAllBy();

    List<LongBookProjection> findAllProjectedBy();

    List<DisplayBookEGDto> findWithEntityGraph();

    List<DisplayBookDto> findByDatePublished();

    DisplayBookDetailsDto findWithDetailsById(Long id);

}
