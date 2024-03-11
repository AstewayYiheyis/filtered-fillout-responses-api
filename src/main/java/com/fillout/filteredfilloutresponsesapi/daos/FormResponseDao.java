package com.fillout.filteredfilloutresponsesapi.daos;

import com.fillout.filteredfilloutresponsesapi.models.FormResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormResponseDao {
    List<FormResponse> responses;
    private int totalResponses;
    private int pageCount;
}
