@Web
Feature: Automation testing task #2 (UI)

  Scenario: Automation testing task #2 (UI)
    Given User go to the xm.com
    Then User is on page "Home"
    Then User (open_menu) "Trading"
    Then User (open_menu) "Stocks"
    Then User is on page "Stocks"
    Then User (choose stock filter) "Norway"
    Then User (find_stock_in_table) "Orkla ASA (ORK.OL)"
    Then User (remember_stock_in_table) "Orkla ASA (ORK.OL)"
    Then User (click_read_more_for) "Orkla ASA (ORK.OL)"
    Then User is on page "Stock Certain"
    Then User (check_trading_conditions_for) "Orkla ASA (ORK.OL)"

