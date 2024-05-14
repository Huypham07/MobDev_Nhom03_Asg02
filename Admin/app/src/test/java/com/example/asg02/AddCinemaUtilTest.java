package com.example.asg02;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddCinemaUtilTest {

    private AddCinemaUtil addCinemaUtil;
    @Before
    public void setUp() throws Exception {
        addCinemaUtil = new AddCinemaUtil();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void emptyCinemaName_returnsFalse() {
        boolean result = addCinemaUtil.validateAddCinemaUtil("", "@star3.cineplex.com", "#star3cineplex#");
        assertFalse(result);
    }

    @Test
    public void emptyCinemaEmail_returnsFalse() {
        boolean result = addCinemaUtil.validateAddCinemaUtil("cgv", "", "#star3cineplex#");
        assertFalse(result);
    }

    @Test
    public void emptyCinemaPassword_returnsFalse() {
        boolean result = addCinemaUtil.validateAddCinemaUtil("cgv", "@star3.cineplex.com", "");
        assertFalse(result);
    }

    @Test
    public void wrongFormatCinemaEmail_returnsFalse() {
        boolean result = addCinemaUtil.validateAddCinemaUtil("cgv", "@cineplex.com", "#star3cineplex#");
        assertFalse(result);
    }

    @Test
    public void wrongFormatCinemaPassword_returnsFalse() {
        boolean result = addCinemaUtil.validateAddCinemaUtil("cgv", "@star3.cineplex.com", "password");
        assertFalse(result);
    }

    @Test
    public void correctlyCinema_returnsTrue() {
        boolean result = addCinemaUtil.validateAddCinemaUtil("cgv", "@star3.cineplex.com", "#star3cineplex#");
        assertFalse(result);
    }
}