package mk.uikm.finki.emtlab.model.dto;

import mk.uikm.finki.emtlab.model.domain.Author;
import mk.uikm.finki.emtlab.model.domain.Country;

public record CreateAuthorDto(
        String name,
        String surname,
        Long countryId
) {
    public Author toAuthor (Country country){
        return new Author(name, surname, country);
    }
}
