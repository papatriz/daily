package com.papatriz.daily.validator;

import com.papatriz.daily.dto.ActivityDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActivityRequestValidator {
    private Map<String, String> errorMap = new HashMap<>();
    {
        errorMap.put("title","Activity title is not present or too short");
        errorMap.put("weight", "Weight should be between 1 and 10");
        errorMap.put("duration", "Duration should be between 30 and 600 minutes");
    }

    public List<String> validate(ActivityDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getTitle() == null || dto.getTitle().isBlank()) errors.add(errorMap.get("title"));
        if (dto.getDuration() < 30 || dto.getDuration() > 600) errors.add(errorMap.get("duration"));
        if (dto.getWeight() < 1 || dto.getWeight() > 10) errors.add(errorMap.get("weight"));

        return errors;
    }
}
