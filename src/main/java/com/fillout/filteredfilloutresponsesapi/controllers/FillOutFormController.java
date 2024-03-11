package com.fillout.filteredfilloutresponsesapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fillout.filteredfilloutresponsesapi.daos.FormResponseDao;
import com.fillout.filteredfilloutresponsesapi.services.FillOutFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class FillOutFormController {
    private FillOutFormService fillOutFormService;

    public FillOutFormController(FillOutFormService fillOutFormService) {
        this.fillOutFormService = fillOutFormService;
    }

    @GetMapping("/{formId}/filteredResponses")
    public ResponseEntity<FormResponseDao> getFilteredFormResponses(@PathVariable("formId") String formId,
                                                                    @RequestParam("query") String query) throws
            JsonProcessingException {
        return ResponseEntity.ok(fillOutFormService.getFilteredFormResponses(formId, query));
    }
}
