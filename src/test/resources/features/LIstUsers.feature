@Regression @ListUsers
Feature: End to end tests for social network's list users API
  Description: The purpose of these tests are to cover different business scenarios to list different users on Social Network

  Background:
    Given I am an authorised user to view list of users

  #Happy path scenarios
  Scenario: Authorised user is able to view list of all users within social network site
    Given I have valid request to view all the users
    When I execute the GET request
    Then I receive 200 response
    And I am able to view all the users within social network site
    And I am able to verify all the fields in response are present for all users

  Scenario Outline: Authorised user is able to view a given user within social network site
    Given I have valid request to view a given user "<userID>"
    When I execute the GET request
    Then I receive 200 response
    And I am able to view given user within social network site
    And I am able to verify all the fields in response are present for given user
    Examples:
      | userID |
      | 1      |
      | 2      |
      | 3      |
      | 4      |

  #Negative scenarios
  Scenario Outline: Error received if user ID passed is invalid
    Given I have valid request to view a given invalid user "<userID>"
    When I execute the GET request
    Then I receive 404 response
    And response body is empty
    Examples:
      | userID |
      | 1000   |
      | ASDSD  |
      | A&^&2  |
      | 0      |
      | 000    |