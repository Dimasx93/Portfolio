Feature: Examples of using data tables in Cucumber

  @OneColumnExamples
  Scenario: Using a data table with one column
    When I receive one column table as DataTable
      | Some  |
      | list  |
      | of    |
      | stuff |
    And I receive one column table as List
      | Some  |
      | list  |
      | of    |
      | stuff |
    And I receive one column integer table as DataTable
      | 1 |
      | 2 |
      | 3 |
      | 4 |
    And I receive one column integer table as List
      | 5  |
      | 10 |
      | 15 |
      | 20 |

  @TwoColumnExamples
  Scenario: Using a data table with two columns
    When I receive two column table as DataTable
      | name     | Igors   |
      | genre    | Male    |
      | language | English |
    And I receive two column table as Map
      | name     | Igors   |
      | genre    | Male    |
      | language | English |
    And I receive two column table as List of strings
      | name     | Igors   |
      | genre    | Male    |
      | language | English |
    And I receive two column table with List of numbers as DataTable
      | 1  | 2  |
      | 5  | 6  |
      | 10 | 20 |
    And I receive two column table with List of numbers as List of List of Integers
      | 1  | 2  |
      | 5  | 6  |
      | 10 | 20 |

    # No limit on amount of columns - could be 3, could be 4 or more, depends on how many you need
  @ThreeColumnExamples
  Scenario: Using a data table with three columns
    When I receive three column table as DataTable
      | 1  | 2  | 3  |
      | 5  | 6  | 7  |
      | 10 | 20 | 30 |
    When I receive three column table with first column as description in DataTable
      | firstSet  | 2  | 3   | 4   | 5 |
      | secondSet | 6  | 7   | 8   |   |
      | thirdSet  | 20 | 30  |     |   |
      | fourthSet |    | 100 | 200 |   |
    When I receive three column table with first column as description
      | firstSet  | 2  | 3   | 4   | 5 |
      | secondSet | 6  | 7   | 8   |   |
      | thirdSet  | 20 | 30  |     |   |
      | fourthSet |    | 100 | 200 |   |
    When I receive four column table with header
      | name  | genre  | language | feedback |
      | Igors | Male   | English  | Bad      |
      | Anete | Female | Spanish  | Ok       |
    When I receive four column table with header and am just messing with you at this point
      |     | name  | genre  | language | feedback |
      | id1 | Igors | Male   | English  | Bad      |
      | id2 | Anete | Female | Spanish  | Ok       |

  @ThreeColumnAdvancedExamples
  Scenario: Using a data table with three columns and custom data types for transformation
    When I receive three column person as a DataTable
      | name     | Igors   |
      | genre    | Male    |
      | language | English |
    When I receive three column person as row with headers
      | name  | genre | language |
      | Igors | Male  | English  |
    When I receive three column person as list
      | name     | Igors   |
      | genre    | Male    |
      | language | English |