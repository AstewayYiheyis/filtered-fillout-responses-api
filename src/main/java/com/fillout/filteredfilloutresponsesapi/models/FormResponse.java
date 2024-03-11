package com.fillout.filteredfilloutresponsesapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormResponse {
    private CustomResponse questions;
    private CustomResponse calculations;
    private CustomResponse urlParameters;
    private Quiz quiz;
    private String submissionId;
    private Date submissionTime;
}
