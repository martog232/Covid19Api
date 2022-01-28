package app.controller;

import app.cronjob.CountryExtractor;
import app.model.dto.CountryDTO;
import app.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class CountryController {
    private CountryService countryService;
    private CountryExtractor countryExtractor;

    @GetMapping(value = "country/{country_code}")
    public Optional<CountryDTO> findByCode(@PathVariable(name = "country_code") String countryCode) {
        return countryService.findByCode(countryCode);
    }

    @PutMapping(value = "country/save")
    public void saveAll() {
        countryExtractor.countryExtract();
    }

}
