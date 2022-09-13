# JPMC tech assignment

This test automation suite uses Rest Assured and Cucumber framework

## 603 – Software Development Engineer in Test (Backend)

Imagine you are building a social network. Starting from simple functionality. Users are now
able to make posts and comment on them. You are working in the backend team that
exposes the service: https://jsonplaceholder.typicode.com/ which has the following
endpoints:

1. Make posts: https://jsonplaceholder.typicode.com/posts
2. Comment on posts: https://jsonplaceholder.typicode.com/comments
3. List of users: https://jsonplaceholder.typicode.com/users

Using Rest-Assured, Cucumber, and Java, create a few scenarios to test this functionality.

## Test Data

This suite stores test data into external xlsx file which be found at 'src/test/resources/data'

## Instructions to run the test suite

1. If using IDE, create a run config >> JUnit
2. Add VM options "-Dfeature.env=e0"
3. Run the tests using above created Runner
   ![alt text](img.png?raw=true "Title")
4. Once successfully run, refer "test-output" folder to see the extent report of the run.

## Future enhancements/additions

1. Based on specs refine scenarios for each API
2. Add loggers if extent reporting is not sufficient as the suite grows