package com.legiz.terasoftproject.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.legiz.terasoftproject.userProfile.resource.CreateLawyerResource;
import com.legiz.terasoftproject.userProfile.resource.LawyerResource;
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

public class LawyerAddingStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;

    @Given("The Endpoint {string} is available")
    public void theEndpointAvailable(String endPointPath) {
        this.endPointPath = String.format("http://localhost:%d/api/v1/lawyers", randomServerPort);
    }

    /*
    @When("A Lawyer Request is sent with values {string}, {string}, {string}")
    public void aLawyerRequestIsSentWithValues(String username, String password, String email) {
        CreateLawyerResource resource = new CreateLawyerResource()
                .withUsername(username)
                .withPassword(password)
                .withEmail(email);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateLawyerResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received")
    public void aResponseIsReceivedWithStatus(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Lawyer Resource with values {string}, {string}, {string} is included in Response Body")
    public void aLawyerResourceIsIncludedInResponseBodyWithValues(String username, String password, String email) {
        LawyerResource expectedResource = new LawyerResource()
                .withUsername(username)
                .withPassword(password)
                .withEmail(email);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        LawyerResource actualResource;
        try {
            actualResource = mapper.readValue(value, LawyerResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new LawyerResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A Lawyer Resource with values {string}, {string}, {string} is already stored")
    public void aLawyerResourceIsAlreadyStoredWithValues(String username, String password, String email) {
        CreateLawyerResource resource = new CreateLawyerResource()
                .withUsername(username)
                .withPassword(password)
                .withEmail(email)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateLawyerResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }


    @And("A Message with value {string} is included in Response Body")
    public void aMessageIsIncludedInResponseBodyWithValue(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
    */
}
