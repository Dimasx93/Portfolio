#@test - valid place for tag
Feature: Examples for using stuff

#  @test - NOT valid place for tag, do not do this
#  This step executes before each scenario - equivalent to having the commented lines in the scenario (going to link) uncommented
  Background:
    Given I go to URL "https://kristinek.github.io/site/examples/actions"

#  @test - valid place for tag
  Scenario: Cucumber Expressions examples
    # Demonstrates use of custom parameter object
    And I click feisty link
    And I click nasty link
    And I click great link
    # Demonstates use of alternative text
    And I enter bad text
    And I enter good text
    And I enter mediocre text
    # These are all covered by a single step implementation
    And I eat one banana
    And I eat two bananas
    And I eat three bananas
    And I eat quadrillion banana
    And I eat very, very, very, very many bananas
    # Can enter both positive and negative
    And I enter number 5
    And I enter number -5

  Scenario: Go to Actions page 1
#    When I go to URL "https://kristinek.github.io/site/examples/actions"
    And I enter name "Janis" into text field
    And I enter integer 555 into number field
    And I wait 3 seconds
    And I enter double 5555.55 into number field
    And I wait 3 seconds
    And I enter commadouble 7777,77 into number field

  Scenario: Go to Actions page 2
#    When I go to URL "https://kristinek.github.io/site/examples/actions"
    And I enter name "Igors" into text field
