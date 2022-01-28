package app.model.mapper;

import app.model.dto.CountryDTO;
import app.model.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper implements IModelMapper<CountryDTO, Country> {
    @Override
    public CountryDTO toDto(Country entity) {
        if (entity == null) return null;

        return new CountryDTO(entity.getId(),entity.getCountryName(),entity.getCountryCode(),
                entity.getSlug(),entity.getNewConfirmed(),entity.getTotalConfirmed(),entity.getNewDeaths(),
                entity.getTotalDeaths(),entity.getNewRecovered(),
                entity.getTotalRecovered(),entity.getDate());
    }

    @Override
    public Country toEntity(CountryDTO dto) {
        if (dto == null) return null;

        return new Country(dto.getId(),dto.getCountryName(),dto.getCountryCode(),
                dto.getSlug(),dto.getNewConfirmed(),dto.getTotalConfirmed(),dto.getNewDeaths(),
                dto.getTotalDeaths(),dto.getNewRecovered(),
                dto.getTotalRecovered(),dto.getDate());
    }
}
