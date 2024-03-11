package com.fillout.filteredfilloutresponsesapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fillout.filteredfilloutresponsesapi.daos.FormResponseDao;
import com.fillout.filteredfilloutresponsesapi.models.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FillOutFormService {
    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(FillOutFormService.class);

    public FillOutFormService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FormResponseDao getFilteredFormResponses(String formId, String query) throws JsonProcessingException {
        FormResponseDao formResponseDao = getFormResponses(formId);
        ObjectMapper objectMapper = new ObjectMapper();

        List<CustomResponse> customResponses = objectMapper.readValue(query, new TypeReference<List<CustomResponse>>() {});
        formResponseDao.setResponses(formResponseDao.getResponses().stream().filter
                (res -> customResponses.contains(res)).collect(Collectors.toList()));

        return formResponseDao;
    }

    private FormResponseDao getFormResponses(String formId) {
        String baseUrl = "https://api.fillout.com";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                                                           .path("/v1/api/forms/{formId}/submissions")
                                                           .queryParam("formId", formId);

        String bearerToken = "sk_prod_TfMbARhdgues5AuIosvvdAC9WsA5kXiZlW8HZPaRDlIbCpSpLsXBeZO7dCVZQwH" +
                "AY3P4VSBPiiC33poZ1tdUj2ljOzdTCCOSpUZ_3912";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the HTTP GET request with parameters
        ResponseEntity<FormResponseDao> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                FormResponseDao.class);

        FormResponseDao responseBody = null;

        // Handle the response
        if (response.getStatusCode().is2xxSuccessful()) {
            responseBody = response.getBody();
        } else {
            logger.error("Request failed with status code: " + response.getStatusCode());
        }


        return responseBody;
    }
}
