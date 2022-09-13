@Regression @E2E
Feature: End to end tests for social network's find user, make a post and then comment
  Description: The purpose of these tests are to cover business scenarios for users,
  Being able to fetch user
  Make a post
  Comment on the post

  Background:
    Given I am an authorised user to view list of users

  Scenario Outline: E2E social networking flow
    Given I have valid request to view all the users
    When I execute the GET request
    Then I receive 200 response
    And I am able to view all the users within social network site
    And I pick an user to prepare for making a post
    When I execute the POST request to make a post with all mandatory fields "<post-tittle>" "<post-body>"
    Then I receive 201 response
    And response body has valid values same as post request
    And I prepare to comment on this post
    When I execute request to comment on a post with all mandatory fields "<comment-name>" "<comment-email>" "<comment-body>"
    Then I receive 201 response
    And response body has valid values same as above comments request
    Examples:
      | post-tittle                 | post-body                                        | comment-name | comment-email | comment-body       |
      | Dreaming of working at JPMC | This week I got interviewed at JPMC              | name A       | test1@jp.com  | All the very best! |
      | Preparing for an interview  | I submitted test for test automation             | name B       | test2@jp.com  | Hope for the best! |
      | Received an offer for SDET  | I received an offer for SDET role at JPMC London | name C       | test3@jp.com  | Congratulations!!! |
