package com.searchbook;

import com.searchbook.mappers.Items;
import com.searchbook.mappers.VolumeList;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;

@SpringBootApplication
@RestController
@Validated
public class SearchBookAPI {

    Logger logger = LoggerFactory.getLogger(SearchBookAPI.class);

    private final String BASE_URI = "https://www.googleapis.com/books/v1/volumes?q=";
    private final String API_KEY = "AIzaSyDN37rkI-vxrauZmKi7rVQ5re-8lng8wzo";
    private final String SPACE = " ";
    private final String ADD = "+";
    private final String ORDER_BY = "newest";
    private final String MAX_RESULTS = "10";

    public static void main(String[] args) {
        SpringApplication.run(SearchBookAPI.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return String.format("Hello %s!", name);
    }

    @GetMapping("/findBooksByText")
    public VolumeList findBooksByText(@RequestParam(value = "searchText", defaultValue = "Bible") String searchText,
                                      @Size(min = 2, max = 2, message = "Language should be two letters")
                                      @RequestParam(value = "searchLanguage", defaultValue = "en") String searchLanguage) {

        String uri = BASE_URI +
                searchText.replaceAll(SPACE, ADD) + //Replace spaces with "+"
                "&langRestrict={langRestrict}" +
                "&maxResults={maxResults}" +
                "&orderBy={orderBy}" +
                "&key={key}";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<VolumeList> response
                = restTemplate.getForEntity(uri, VolumeList.class, searchLanguage, MAX_RESULTS, ORDER_BY, API_KEY);

        if (response.getBody().getItems() != null) {
            response.getBody().getItems().sort(new Comparator<Items>() {
                @Override
                public int compare(Items item1, Items item2) {
                    return item2.getVolumeInfo().getPublishedDate().compareTo(item1.getVolumeInfo().getPublishedDate());
                }
            });
        }

        return response.getBody();
    }
}