package com.papatriz.daily.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.papatriz.daily.dto.ActivityDto;

@SpringBootTest
public class ActivityRequestValidatorTest {

    @Autowired
    private ActivityRequestValidator validator;

    @Test
    public void testValidate_whenTitleIsNull_returnsError() {
        ActivityDto dto = new ActivityDto(0L, null, (short) 60, (short) 5);

        List<String> errors = validator.validate(dto);

        assertEquals(1, errors.size());
        assertTrue(errors.contains("Activity title is not present or too short"));
    }

    @Test
    public void testValidate_whenTitleIsBlank_returnsError() {
        ActivityDto dto = new ActivityDto(0L, "", (short) 60, (short) 5);

        List<String> errors = validator.validate(dto);

        assertEquals(1, errors.size());
        assertTrue(errors.contains("Activity title is not present or too short"));
    }

    @Test
    public void testValidate_whenDurationIsTooShort_returnsError() {
        ActivityDto dto = new ActivityDto(0L, "Activity Title", (short) 29, (short) 5);

        List<String> errors = validator.validate(dto);

        assertEquals(1, errors.size());
        assertTrue(errors.contains("Duration should be between 30 and 600 minutes"));
    }

    @Test
    public void testValidate_whenDurationIsTooLong_returnsError() {
        ActivityDto dto = new ActivityDto(0L, "Activity Title", (short) 601, (short) 5);

        List<String> errors = validator.validate(dto);

        assertEquals(1, errors.size());
        assertTrue(errors.contains("Duration should be between 30 and 600 minutes"));
    }

    @Test
    public void testValidate_whenWeightIsTooLow_returnsError() {
        ActivityDto dto = new ActivityDto(0L, "Activity Title", (short) 60, (short) 0);

        List<String> errors = validator.validate(dto);

        assertEquals(1, errors.size());
        assertTrue(errors.contains("Weight should be between 1 and 10"));
    }

    @Test
    public void testValidate_whenWeightIsTooHigh_returnsError() {
        ActivityDto dto = new ActivityDto(0L, "Activity Title", (short) 60, (short) 11);

        List<String> errors = validator.validate(dto);

        assertEquals(1, errors.size());
        assertTrue(errors.contains("Weight should be between 1 and 10"));
    }

    @Test
    public void testValidate_whenAllFieldsAreValid_returnsNoErrors() {
        ActivityDto dto = new ActivityDto(0L, "Activity Title", (short) 60, (short) 5);

        List<String> errors = validator.validate(dto);

        assertTrue(errors.isEmpty());
    }
}

