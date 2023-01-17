package com.papatriz.daily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ActivityDto {
    private long id;
    private String title;
    private short duration;
    private short weight;
    private LocalDate startDate;
}
