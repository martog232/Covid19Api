package app.model.repository;

import app.model.dto.CountryDTO;
import app.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {

    @Query("select c from Country c where c.countryCode = ?1")
    Optional<Country> findByCountryCode(String countryCode);
}
