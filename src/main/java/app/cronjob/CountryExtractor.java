package app.cronjob;

import app.model.entity.Country;
import app.service.CountryService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor
@JsonIgnoreProperties(value = {"Premium"})
public class CountryExtractor {
    private static final String URL_STRING = "https://api.covid19api.com/summary";
    private CountryService countryService;

    @Scheduled(cron = "0 */6 * * *")
    public void countryExtract() {

        try {

            URL url = new URL(URL_STRING);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);

            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();
                JSONParser parse = new JSONParser();

                ObjectMapper mapper = new ObjectMapper();

                JSONObject jsonObject = (JSONObject) parse.parse(inline);

                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                JSONArray arr = (JSONArray) jsonObject.get("Countries");

                List<Country> countries = mapper.readValue(arr.toString(), new TypeReference<>() {
                });
                countryService.saveAll(countries);

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
