package mk.uikm.finki.emtlab.model.dto;

import mk.uikm.finki.emtlab.model.domain.Author;

public record DisplayAuthorDetailsDto(
        Long id,
        String name,
        String surname,
        DisplayCountryDto country
) {
    public static DisplayAuthorDetailsDto from(Author author){
        return new DisplayAuthorDetailsDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                DisplayCountryDto.from(author.getCountry())
        );
    }
}
