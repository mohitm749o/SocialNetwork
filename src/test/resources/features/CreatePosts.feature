@Regression @MakePosts
Feature: End to end tests for social network's create post API
  Description: The purpose of these tests are to cover different business scenarios for users being able to make posts on Social Network

  Background:
    Given I am an authorised user to make posts on social networking site

  Scenario Outline: Success: Users are able to successfully make posts
    Given I have data loaded for making posts
    And I have valid request to make a post for data row <dataRow>
    When I execute the POST request to make a post
    Then I receive 201 response
    And response body has valid values same as request
    Examples:
      | dataRow |
      | 1       |
      | 2       |

  Scenario Outline: Failure: Validate mandatory fields for make posts API
    Given I have request to make a post with all mandatory fields
    But field "<field>" is not sent
    When I execute the POST request to make a post
    Then I receive 400 response
    Examples:
      | field  |
      | userId |
      | title  |
      | body   |

  Scenario Outline: Failure: Validate field format
    Given I have field format request to make a post with all mandatory fields
    But field "<field>" is set with value "<value>"
    When I execute the POST request to make a post
    Then I receive 400 response
    Examples:
      | field  | value                        |
      | userId | ABC                          |
      | title  | greaterThanallowedCharacters |
      | body   | greaterThanallowedCharacters |


