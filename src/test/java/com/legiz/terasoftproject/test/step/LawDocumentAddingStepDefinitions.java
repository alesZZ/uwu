package com.legiz.terasoftproject.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.legiz.terasoftproject.services.resource.CreateLawDocumentResource;
import com.legiz.terasoftproject.services.resource.LawDocumentResource;
import com.legiz.terasoftproject.userProfile.resource.CreateCustomerResource;
import com.legiz.terasoftproject.userProfile.resource.CustomerResource;
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

public class LawDocumentAddingStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;

    @Given("The Endpoint {string} is available")
    public void theEndpointAvailable(String endPointPath) {
        this.endPointPath = String.format("http://localhost:%d/api/v1/lawdocument", randomServerPort);
    }

    /*
    @When("A LawDocument Request is sent with values {string}, {string}, {string}")
    public void aLawDocumentRequestIsSentWithValues(String title, String path, String arg2) {
        CreateLawDocumentResource resource = new CreateLawDocumentResource()
                .withTitle(title)
                .withPath(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateLawDocumentResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received")
    public void aResponseIsReceivedWithStatus(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A LawDocument Resource with values {string}, {string}, {string} is included in Response Body")
    public void aLawDocumentResourceIsIncludedInResponseBodyWithValues(String title, String path, String arg2) {
        LawDocumentResource expectedResource = new LawDocumentResource()
                .withTitle(title)
                .withPath(path);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        LawDocumentResource actualResource;
        try {
            actualResource = mapper.readValue(value, LawDocumentResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new LawDocumentResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A LawDocument Resource with values {string}, {string}, {string} is already stored")
    public void aLawDocumentResourceWithValuesIsAlreadyStored(String title, String path, String arg2) {
        CreateLawDocumentResource resource = new CreateLawDocumentResource()
                .withTitle(title)
                .withPath(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateLawDocumentResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }


    @And("A Message with value {string} is included in Response Body")
    public void aMessageIsIncludedInResponseBodyWithValue(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
     */
}
