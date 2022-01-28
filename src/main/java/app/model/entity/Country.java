package app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "countries")
public class Country {

    @JsonProperty("ID")
    private String Id;

    @JsonProperty("Country")
    private String countryName;

    @Id
    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("Slug")
    private String slug;

    @JsonProperty("NewConfirmed")
    private Integer newConfirmed;

    @JsonProperty("TotalConfirmed")
    private Integer totalConfirmed;

    @JsonProperty("NewDeaths")
    private Integer newDeaths;

    @JsonProperty("TotalDeaths")
    private Integer totalDeaths;

    @JsonProperty("NewRecovered")
    private Integer newRecovered;

    @JsonProperty("TotalRecovered")
    private Integer totalRecovered;

    @JsonProperty("Date")
    private String date;

    public Country() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(Id, country.Id) && Objects.equals(countryName, country.countryName) && Objects.equals(countryCode, country.countryCode) && Objects.equals(slug, country.slug) && Objects.equals(newConfirmed, country.newConfirmed) && Objects.equals(totalConfirmed, country.totalConfirmed) && Objects.equals(newDeaths, country.newDeaths) && Objects.equals(totalDeaths, country.totalDeaths) && Objects.equals(newRecovered, country.newRecovered) && Objects.equals(totalRecovered, country.totalRecovered) && Objects.equals(date, country.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, countryName, countryCode, slug, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered, date);
    }
}


