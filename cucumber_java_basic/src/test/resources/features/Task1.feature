# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://kristinek.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:

@task1

Feature: Enter a Number Page
  Scenario Outline: Error cases
    Given I am on enter a number page
  #   * enter number too small
  #   * enter number too big
  #   * enter text instead of the number
  # - Scenario for correct number
    When I enter "<input>"
    Then I should see an error message

    Examples:
      | input  |
      |  10    |
      |  101   |
      |  ABC   |

  Scenario Outline: Correct number entered
    Given I am on enter a number page
    When I enter "<number>"
    Then I should see a success message
    Examples:
      | number |
      | 64     |