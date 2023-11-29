# TODO - Create 1 scenario outline and create scenario or scenario outlines for page
# https://kristinek.github.io/site/tasks/list_of_people.html OR
# https://kristinek.github.io/site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
@task2
Feature: Task2 feature

  Background:
    Given I am on List of people page

  Scenario Outline: add people scenario
    When I click Add button
    Then I enter values for: "<name>", "<surname>", "<status>"
    Then I click another add button
    Examples:
      | name   | surname  | status     |
      | Stefano | Di Mauro  | Contractor |
      | Arturs  | Jermaks  | Employee   |
      | Paolo   | Rossi | Intern     |

  Scenario Outline: edit two existing employees scenario
    When I click Edit button for "<name>", "<surname>"
    Then I editing "<job>", "<status>"
    And I click Edit button
    Examples:
      | name | surname | job           | status   |
      | Mike | Kid     | QA Engineer   | Intern   |
      | Jill | Watson  | Web Developer | Employee |

  Scenario Outline: remove two users scenario
    When I click Remove Button for "<name>", "<surname>"
    Then Removed persons should not be displayed for "<name>", "<surname>"
    Examples:
      | name | surname |
      | Mike | Kid     |
      | Jill | Watson  |

  Scenario: functionality reset scenario
    When I click Reset list button
    Then User list should be reset

  Scenario Outline: clearing button on Add Person scenario
    When I click Add button
    Then I fill all fields "<name>", "<surname>", "<job>", "<dateOfBirth>"
    Then I select gender
    And I click Clear all fields button
    Then I verify that all the fields are cleared
    And I verify that status and language are default
    #The default language (English) and Employee status (Employee) are set up.
    #Note: No need to mention languages & status in examples.
    Examples:
      | name   | surname | job         | dateOfBirth |
      | Arnold | Wayne   | TA Engineer | 11/03/1976  |

# - add a new person
# - edit a person
# - remove a person
# - reset original list after:
#   * adding a person
#   * editing a person
#   * removing a person
# - check that clear button on adding a user works correctly
