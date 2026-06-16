package mk.uikm.finki.emtlab.model.dto;


import mk.uikm.finki.emtlab.model.domain.Country;

public record CreateCountryDto(
        String name,
        String continent
) {
    public Country toCountry(){
        return new Country(name, continent);
    }
}
