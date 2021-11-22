Feature: Subscription Adding
  As a Developer
  I want to add Subscription through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http://localhost:8080/api/v1/subscription" is available

  @subscription-adding
  Scenario: Add Subscription
    When A Subscription Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 200 is received
    And A Subscription Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is included in Response Body

  @subscription-duplicated
  Scenario: Add Subscription with existing Description
    Given A Subscription Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is already stored
    When A Subscription Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
    Then A Response with Status 400 is received
    And A Message with value "A Subscription with the same description already exists" is included in Response Body