# new feature
# Tags: optional

Feature: Smoke test of Maantic Ilearning Application

  Scenario Outline: Create and Delete a Course
    Given user is on home page
    When user creates a new course "<courseName>", "<status>","<priority>", "<trainer>"
    And user verifies the new course is created successfully "<courseName>", "<status>","<expectedPriority>"
    Then user deletes the course "<courseName>"
    Then user verifies course "<courseName>" is deleted successfully

    Examples:
      | courseName   | status    | priority | expectedPriority | trainer      |
      | Test Course_ | Requested | Medium   | Medium           | Josh Johnson |
      | Test Course_ | Planned   | High     | Medium           | Test User    |