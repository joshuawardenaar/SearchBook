package com.searchbook.mappers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeInfoTest {

    @Test
    void setPublishedDateEmpty() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("");
        assertNull(volumeInfo.getPublishedDate());
    }

    @Test
    void setPublishedDateEmptyNull() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate(null);
        assertNull(volumeInfo.getPublishedDate());
    }

    @Test
    void setPublishedDateYear() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997");
        assertNotNull(volumeInfo.getPublishedDate());
        assertEquals(volumeInfo.getPublishedDate().getYear(), 1997);
        assertEquals(volumeInfo.getPublishedDate().getMonthValue() , 1);
        assertEquals(volumeInfo.getPublishedDate().getDayOfMonth() , 1);
    }

    @Test
    void setPublishedDateYearMonth() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11");
        assertNotNull(volumeInfo.getPublishedDate());
        assertEquals(volumeInfo.getPublishedDate().getYear() , 1997);
        assertEquals(volumeInfo.getPublishedDate().getMonthValue() , 11);
        assertEquals(volumeInfo.getPublishedDate().getDayOfMonth() , 1);
    }

    @Test
    void setPublishedDateYearMonthDay() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11-20");
        assertEquals(volumeInfo.getPublishedDate().getYear() , 1997);
        assertEquals(volumeInfo.getPublishedDate().getMonthValue() , 11);
        assertEquals(volumeInfo.getPublishedDate().getDayOfMonth() , 20);
    }

    @Test
    void setPublishedDateYearWrongFormat() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("Some kind of mess");
        assertNull(volumeInfo.getPublishedDate());
    }

    @Test
    void getFormattedPublishedDateEmpty() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("");
        assertNull(volumeInfo.getFormattedPublishedDate());
    }

    @Test
    void getFormattedPublishedNotSet() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate(null);
        assertNull(volumeInfo.getFormattedPublishedDate());
    }

    @Test
    void getFormattedPublishedDateYear() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997");
        assertNotNull(volumeInfo.getFormattedPublishedDate());
        assertEquals("1 januari 1997", volumeInfo.getFormattedPublishedDate());
    }

    @Test
    void getFormattedPublishedDateYearMonth() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11");
        assertNotNull(volumeInfo.getFormattedPublishedDate());
        assertEquals("1 november 1997", volumeInfo.getFormattedPublishedDate());
    }

    @Test
    void getFormattedPublishedDateYearFaultyMonth() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-1");
        assertNull(volumeInfo.getFormattedPublishedDate());
    }

    @Test
    void getFormattedPublishedDateYearMonthDay() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11-20");
        assertEquals("20 november 1997", volumeInfo.getFormattedPublishedDate());
    }

    @Test
    void getFormattedPublishedDateWrongFormat() {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("Some kind of mess");
        assertNull(volumeInfo.getFormattedPublishedDate());
    }
}