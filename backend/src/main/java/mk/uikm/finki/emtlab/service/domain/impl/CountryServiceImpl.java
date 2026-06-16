package mk.uikm.finki.emtlab.service.domain.impl;

import mk.uikm.finki.emtlab.model.domain.Country;
import mk.uikm.finki.emtlab.model.exception.ResourceNotFoundException;
import mk.uikm.finki.emtlab.repository.CountryRepository;
import mk.uikm.finki.emtlab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Country with id %d not found!", id)));
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country update(Long id, Country country) {
        Country existing = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Country with id %d not found!", id)));
        existing.setName(country.getName());
        existing.setContinent(country.getContinent());
        return countryRepository.save(existing);
    }

    @Override
    public Country delete(Long id) {
        Country existing = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Country with id %d not found!", id)));
        countryRepository.delete(existing);
        return existing;
    }
}
