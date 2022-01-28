package app.service;

import app.controller.error.APIErrorCode;
import app.controller.exception.MethodArgumentNotValidException;
import app.controller.exception.EntityNotFoundException;
import app.model.dto.CountryDTO;
import app.model.entity.Country;
import app.model.mapper.CountryMapper;
import app.model.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements ICountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Autowired
    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public Optional<CountryDTO> findByCode(String countryCode) {
        if(countryCode.length()==2 && countryCode.matches("[A-Z]{2}"))
            return Optional.ofNullable(countryRepository.findByCountryCode(countryCode).map(countryMapper::toDto).orElseThrow(()
                -> new EntityNotFoundException(APIErrorCode.ENTITY_NOT_FOUND.getDescription())));
        else throw new MethodArgumentNotValidException(APIErrorCode.METHOD_ARG_NOT_VALID.getDescription());

    }

    @Override
    public void saveAll(List<Country> countries) {
        countryRepository.saveAll(countries);
    }
}
