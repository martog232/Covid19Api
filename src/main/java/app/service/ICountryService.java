package app.service;

import app.model.dto.CountryDTO;
import app.model.entity.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    Optional<CountryDTO> findByCode(String countryCode);

    void saveAll(List<Country> countries);
}
