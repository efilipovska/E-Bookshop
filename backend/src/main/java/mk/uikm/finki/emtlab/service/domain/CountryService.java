package mk.uikm.finki.emtlab.service.domain;

import mk.uikm.finki.emtlab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country findById(Long id);

    List<Country> findAll();

    Country create(Country country);

    Country update(Long id, Country country);

    Country delete(Long id);
}
