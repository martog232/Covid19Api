package app.service;

import app.controller.error.APIErrorCode;
import app.controller.exception.EntityNotFoundException;
import app.controller.exception.MethodArgumentNotValidException;
import app.model.entity.Country;
import app.model.mapper.CountryMapper;
import app.model.repository.CountryRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
    @Spy
    CountryMapper countryMapper;
    @Mock
    CountryRepository countryRepository;
    @InjectMocks
    CountryService countryService;

    @Test
    public void whenGivenCountryCode_shouldReturnCountry_ifFound() {
        Country country = new Country();
        country.setCountryCode("BG");
        country.setCountryName("Bulgaria");

        Mockito.when(countryRepository.findByCountryCode(country.getCountryCode()))
                .thenReturn(Optional.of(country));

        Country expectedCountry = countryMapper.toEntity(countryService.findByCode(country.getCountryCode()).get());

        Assertions.assertEquals(expectedCountry, country);
    }

    @Test(expected = MethodArgumentNotValidException.class)
    public void whenGivenNotValidCountryCode_shouldReturnBadRequest() {
        Country country = new Country();
        country.setCountryCode("Z1");
        country.setCountryName("Bulgaria");

        countryService.findByCode(country.getCountryCode());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenGivenNotExistingCountryCode_shouldReturnEntityNotFound() {
        Country country = new Country();
        country.setCountryCode("ZZ");
        country.setCountryName("Bulgaria");

        Mockito.when(countryRepository.findByCountryCode(country.getCountryCode()))
                .thenThrow(new EntityNotFoundException(APIErrorCode.ENTITY_NOT_FOUND.getDescription()));

        countryService.findByCode(country.getCountryCode());
    }

    @Test(expected = MethodArgumentNotValidException.class)
    public void whenGivenNotThreeCharactersCountryCode_shouldReturnBadRequest() {
        Country country = new Country();
        country.setCountryCode("ZZZ");
        country.setCountryName("Bulgaria");

        countryService.findByCode(country.getCountryCode());
    }

    @Test
    public void whenAddNewCountryList_shouldSaveItAll() {
        Country country1 = new Country();
        Country country2 = new Country();
        country1.setCountryName("Valoandia");
        country2.setCountryName("Dana");
        List<Country> countries = new ArrayList<>();
        countries.add(country1);
        countries.add(country2);

        Mockito.when(countryRepository.saveAll(countries))
                .thenReturn(countries);

        countryService.saveAll(countries);

        Mockito.verify(countryRepository).saveAll(countries);
    }
}
