package com.legiz.terasoftproject.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.legiz.terasoftproject.payment.resource.CreateSubscriptionResource;
import com.legiz.terasoftproject.payment.resource.SubscriptionResource;
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

public class SubscriptionAddingStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;

    @Given("The Endpoint {string} is available")
    public void theEndpointAvailable(String endPointPath) {
        this.endPointPath = String.format("http://localhost:%d/api/v1/subscription", randomServerPort);
    }

    /*
    @When("A Subscription Request is sent with values {string}, {string}, {string}")
    public void aSubscriptionRequestIsSentWithValues(String description, String arg1, String arg2) {
        CreateSubscriptionResource resource = new CreateSubscriptionResource()
                .withDescription(description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSubscriptionResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received")
    public void aResponseIsReceivedWithStatus(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Subscription Resource with values {string}, {string}, {string} is included in Response Body")
    public void aSubscriptionResourceWithValuesIsIncludedInResponseBody(String description, String arg1, String arg2) {
        SubscriptionResource expectedResource = new SubscriptionResource()
                .withDescription(description);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        SubscriptionResource actualResource;
        try {
            actualResource = mapper.readValue(value, SubscriptionResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new SubscriptionResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A Subscription Resource with values {string}, {string}, {string} is already stored")
    public void aSubscriptionResourceWithValuesIsAlreadyStored(String description, String arg1, String arg2) {
        CreateSubscriptionResource resource = new CreateSubscriptionResource()
                .withDescription(description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSubscriptionResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }


    @And("A Message with value {string} is included in Response Body")
    public void aMessageIsIncludedInResponseBodyWithValue(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
     */
}
