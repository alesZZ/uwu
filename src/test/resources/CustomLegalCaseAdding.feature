Feature: CustomLegalCase Adding
  As a Developer
  I want to add CustomLegalCase through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http://localhost:8080/api/v1/customlegalcases" is available

  @customLegalCase-adding
  Scenario: Add CustomLegalCase
    When A CustomLegalCase Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 200 is received
    And A CustomLegalCase Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is included in Response Body

  @customLegalCase-duplicated
  Scenario: Add CustomLegalCase with existing Title
    Given A CustomLegalCase Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is already stored
    When A CustomLegalCase Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 400 is received
    And A Message with value "A CustomLegalCase with the same title already exists" is included in Response Body