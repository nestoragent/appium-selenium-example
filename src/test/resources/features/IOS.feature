@IOS
Feature: IOS Tiramisu test

  Scenario: IOS Tiramisu test
    Then User is on page "Sign In"
    Then User (click on the bottom button do it later)
    Then User is on page "Home Page"
    When User (search for) "tiramisu"
    Then User is on page "Search Result"
    When User (open recipe) "1"
    Then User is on page "Recipe"
    When User (open tab) "Ingredients"
    When User (add ingredient to the shopping list) "-1"
    When User (open shopping list)
    When User (close popup)
    Then User is on page "Shopping List"
    When User (open recipes tab)
    When User (open recipe) "Tiramisù"
    Then User is on page "Recipe"
    When User (open tab) "Ingredients"
    When User (verify that the ingredient added) "-1"
