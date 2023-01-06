package com.searchbook;

import com.searchbook.mappers.VolumeList;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/findBooksByText")
    public VolumeList findBooksByText(@RequestParam(value = "searchText", defaultValue = "Bible") String searchText,
                                      @Size(min = 2, max = 2, message = "Language should be two letters")
                                      @RequestParam(value = "searchLanguage", defaultValue = "en") String searchLanguage) {

        logger.info("findBooksByText - searchText: " + searchText + " and searchLanguage: " + searchLanguage);

        String uri = BASE_URI +
                searchText.replaceAll(SPACE, ADD) + //Replace spaces with "+"
                "&langRestrict={langRestrict}" +
                "&maxResults={maxResults}" +
                "&orderBy={orderBy}" +
                "&key={key}";

        logger.info("findBooksByText - The constructed uri with placeholders: " + uri);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<VolumeList> response = restTemplate.getForEntity(uri, VolumeList.class, searchLanguage, MAX_RESULTS, ORDER_BY, API_KEY);

        try {
            response.getBody().getItems().sort((item1, item2)
                    -> item2.getVolumeInfo().getPublishedDate().compareTo(item1.getVolumeInfo().getPublishedDate()));
        } catch (NullPointerException e) {
            logger.error("There are no items found or some other error occurred. response.getBody().getItems() = null");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No (book) items found or some service error occurred \n");
        }

        return response.getBody();
    }
}