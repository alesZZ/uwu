package com.legiz.terasoftproject.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.legiz.terasoftproject.services.resource.CreateCustomLegalCaseResource;
import com.legiz.terasoftproject.services.resource.CustomLegalCaseResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;

import java.net.http.HttpHeaders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomLegalCaseAddingStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;

    @Given("The Endpoint {string} is available")
    public void theEndpointAvailable(String endPointPath) {
        this.endPointPath = String.format("http://localhost:%d/api/v1/customlegalcases", randomServerPort);
    }

    /*
    @When("A CustomLegalCase Request is sent with values {string}, {string}, {string}")
    public void aCustomLegalCaseRequestIsSentWithValues(String statusLawService, String title, String typeMeet) {
        CreateCustomLegalCaseResource resource = new CreateCustomLegalCaseResource()
                .withStatusLawService(statusLawService)
                .withTitle(title)
                .withTypeMeet(typeMeet);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCustomLegalCaseResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received")
    public void aResponseIsReceivedWithStatus(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A CustomLegalCase Resource with values {string}, {string}, {string} is included in Response Body")
    public void aCustomLegalCaseResourceIsIncludedInResponseBodyWithValues(String statusLawService, String title, String typeMeet) {
        CustomLegalCaseResource expectedResource = new CustomLegalCaseResource()
                .withStatusLawService(statusLawService)
                .withTitle(title)
                .withTypeMeet(typeMeet);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        CustomLegalCaseResource actualResource;
        try {
            actualResource = mapper.readValue(value, CustomLegalCaseResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new CustomLegalCaseResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A CustomLegalCase Resource with values {string}, {string}, {string} is already stored")
    public void aCustomLegalCaseResourceWithValuesIsAlreadyStored(String statusLawService, String title, String typeMeet) {
        CreateCustomLegalCaseResource resource = new CreateCustomLegalCaseResource()
                .withStatusLawService(statusLawService)
                .withTitle(title)
                .withTypeMeet(typeMeet);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCustomLegalCaseResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }


    @And("A Message with value {string} is included in Response Body")
    public void aMessageIsIncludedInResponseBodyWithValue(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
     */
}
