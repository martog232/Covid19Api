package app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="global")
public class Global {
    @Id
    private String Id;

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
}
