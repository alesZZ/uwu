Feature: Lawyer Adding
  As a Developer
  I want to add Lawyer through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http://localhost:8080/api/v1/lawyers" is available

  @lawyer-adding
  Scenario: Add Lawyer
    When A Lawyer Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 200 is received
    And A Lawyer Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is included in Response Body

  @lawyer-duplicated
  Scenario: Add Lawyer with existing Username
    Given A Lawyer Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is already stored
    When A Lawyer Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 400 is received
    And A Message with value "A Lawyer with the same username already exists" is included in Response Body