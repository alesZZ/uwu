Feature: Customer Adding
As a Developer
I want to add Customer through API
So that It can be available to applications

Background:
Given The Endpoint "http://localhost:8080/api/v1/customers" is available

@customer-adding
Scenario: Add Customer
When A Customer Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
Then A Response with Status 200 is received
And A Customer Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is included in Response Body

@customer-duplicated
Scenario: Add Customer with existing Username
Given A Customer Resource with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices." is already stored
When A Customer Request is sent with values "BDD Best Practices", "A summary with the Best Practice for making BDD tests.", "In the article we bill be explaining the 10 best practices."
Then A Response with Status 400 is received
And A Message with value "A Customer with the same username already exists" is included in Response Body
