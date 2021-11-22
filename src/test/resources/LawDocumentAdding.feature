Feature: LawDocument Adding
  As a Developer
  I want to add LawDocument through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http://localhost:8080/api/v1/lawdocument" is available

  @lawDocument-adding
  Scenario: Add LawDocument
    When A LawDocument Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 200 is received
    And A LawDocument Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is included in Response Body

  @lawDocument-duplicated
  Scenario: Add LawDocument with existing Title
    Given A LawDocument Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is already stored
    When A LawDocument Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 400 is received
    And A Message with value "A LawDocument with the same title already exists" is included in Response Body