Feature: Testing

  @test
  Scenario Outline: a new scenario with 1-row table
    Given I am on action page
    When I clicked on checkboxes modified:
      | <chbx1> |
      | <chbx2> |
      | <chbx3> |
    And I wait 5 seconds
    Examples:
      | chbx1    | chbx2    | chbx3    |
      | Option 1 | Option 2 |          |
      |          | Option 2 |          |
      | Option 1 |          | Option 3 |

  Scenario: Feedback with only languages
    Given I am on feedback page
    When I sum the numbers
      | 18.5  |
      | 28.5  |
      | 108.5 |