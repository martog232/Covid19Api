package app.controller;

import app.cronjob.CountryExtractor;
import app.service.CountryService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CountryControllerUnitTest {

    @Mock
    CountryService countryService;

    @Mock
    CountryExtractor countryExtractor;

    @InjectMocks
    CountryController countryController;

    @Test
    public void shouldReturnCountry() {
        countryController.findByCode("ZZ");

        Mockito.verify(countryService).findByCode("ZZ");
    }

    @Test
    public void shouldSaveAll() {
        countryController.saveAll();

        Mockito.verify(countryExtractor).countryExtract();
    }
}
