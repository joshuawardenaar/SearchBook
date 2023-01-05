package com.searchbook.mappers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeInfoTest {

    @Test
    void setPublishedDate() {
        VolumeInfo volumeInfo = new VolumeInfo();

        volumeInfo.setPublishedDate("");
        assertNull(volumeInfo.getPublishedDate());

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate(null);
        assertNull(volumeInfo.getPublishedDate());

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997");
        assertNotNull(volumeInfo.getPublishedDate());
        assertTrue(volumeInfo.getPublishedDate().getYear() == 1997);
        assertTrue(volumeInfo.getPublishedDate().getMonthValue() == 1);
        assertTrue(volumeInfo.getPublishedDate().getDayOfMonth() == 1);

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11");
        assertNotNull(volumeInfo.getPublishedDate());
        assertTrue(volumeInfo.getPublishedDate().getYear() == 1997);
        assertTrue(volumeInfo.getPublishedDate().getMonthValue() == 11);
        assertTrue(volumeInfo.getPublishedDate().getDayOfMonth() == 1);

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11-20");
        assertTrue(volumeInfo.getPublishedDate().getYear() == 1997);
        assertTrue(volumeInfo.getPublishedDate().getMonthValue() == 11);
        assertTrue(volumeInfo.getPublishedDate().getDayOfMonth() == 20);

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("Some kind of mess");
        assertNull(volumeInfo.getPublishedDate());
    }

    @Test
    void getFormattedPublishedDate() {
        VolumeInfo volumeInfo = new VolumeInfo();

        volumeInfo.setPublishedDate("");
        assertNull(volumeInfo.getFormattedPublishedDate());

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate(null);
        assertNull(volumeInfo.getFormattedPublishedDate());

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997");
        assertNotNull(volumeInfo.getFormattedPublishedDate());
        assertEquals("1 januari 1997", volumeInfo.getFormattedPublishedDate());

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11");
        assertNotNull(volumeInfo.getFormattedPublishedDate());
        assertEquals("1 november 1997", volumeInfo.getFormattedPublishedDate());


        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("1997-11-20");
        assertEquals("20 november 1997", volumeInfo.getFormattedPublishedDate());

        volumeInfo = new VolumeInfo();
        volumeInfo.setPublishedDate("Some kind of mess");
        assertNull(volumeInfo.getFormattedPublishedDate());
    }
}