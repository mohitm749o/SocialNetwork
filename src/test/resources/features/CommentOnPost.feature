@Regression @CommentOnPost
Feature: End to end tests for social network's comment on post API
  Description: The purpose of these tests are to cover different business scenarios to comment on posts on Social Network

  Background:
    Given I am an authorised user to comment on posts

  Scenario Outline: User is able to successfully make comment on a given post
    Given I have data loaded for commenting on posts
    And I have valid request to comment on a post for data row <dataRow>
    When I execute the POST request to comment on a post
    Then I receive 201 response
    And response body has valid values same as comments request
    Examples:
      | dataRow |
      | 1       |
      | 2       |

  Scenario Outline: Failure: Validate mandatory fields for comment on posts API
    Given I have request to comment on a post with all mandatory fields
    But field "<field>" for commenting on a post is not sent
    When I execute the POST request to make a post
    Then I receive 400 response
    Examples:
      | field  |
      | postId |
      | name   |
      | email  |
      | body   |

  Scenario Outline: Failure: Validate field format
    Given I have field format request to comment on a post with all mandatory fields
    But field "<field>" is set with value "<value>" for commenting on a post
    When I execute the POST request to make a post
    Then I receive 400 response
    Examples:
      | field  | value                        |
      | userId | ABC                          |
      | title  | greaterThanallowedCharacters |
      | body   | greaterThanallowedCharacters |

