package com.papatriz.daily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivityDto {
    private long id;
    private String title;
    private short duration;
    private short weight;
}
