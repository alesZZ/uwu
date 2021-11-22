Feature: LegalAdvice Adding
  As a Developer
  I want to add LegalAdvice through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http://localhost:8080/api/v1/legaladvice" is available

  @legalAdvice-adding
  Scenario: Add LegalAdvice
    When A LegalAdvice Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 200 is received
    And A LegalAdvice Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is included in Response Body

  @legalAdvice-duplicated
  Scenario: Add LegalAdvice with existing Title
    Given A LegalAdvice Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is already stored
    When A LegalAdvice Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 400 is received
    And A Message with value "A LegalAdvice with the same title already exists" is included in Response Body