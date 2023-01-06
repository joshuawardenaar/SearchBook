package com.searchbook.mappers;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Getter
@Setter
public class VolumeInfo {
    private String title;
    private List<String> authors;
    private List<IndustryIdentifier> industryIdentifiers;
    private String language;
    private String formattedPublishedDate;
    private LocalDate publishedDate;

    public void setPublishedDate(String publishedDate) {

        String strSimplePattern = "^\\d{4}(-\\d{2})?(-\\d{2})?$";
        if (publishedDate != null && publishedDate.matches(strSimplePattern)) {
            try {
                this.publishedDate = LocalDate.parse(createParsebleDateString(publishedDate));
                formattedPublishedDate = this.publishedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    Set month or day to 01, if none are given.
     */
    private String createParsebleDateString(String date) {
        //Input string matches "^\\d{4}(-\\d{2})?(-\\d{2})?$"; and is not null
        String[] dateParts;
        dateParts = date.split("-");

        if (dateParts.length == 2) {
            date = date.concat("-01");
        } else if (dateParts.length == 1) {
            date = date.concat("-01-01");
        }

        return date;
    }
}