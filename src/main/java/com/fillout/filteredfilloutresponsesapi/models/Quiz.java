package com.fillout.filteredfilloutresponsesapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
    private int score;
    private int maxScore;
}
