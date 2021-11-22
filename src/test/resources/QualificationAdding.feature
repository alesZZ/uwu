Feature: Qualification Adding
  As a Developer
  I want to add Qualification through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http://localhost:8080/api/v1/qualification" is available

  @qualification-adding
  Scenario: Add Qualification
    When A Qualification Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 200 is received
    And A Qualification Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is included in Response Body

  @qualification-duplicated
  Scenario: Add Qualification with existing Comment
    Given A Qualification Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is already stored
    When A Qualification Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 400 is received
    And A Message with value "A Qualification with the same comment already exists" is included in Response Body