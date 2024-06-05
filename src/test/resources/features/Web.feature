@Web
Feature: Automation testing task #2 (UI)

  Scenario: Automation testing task #2 (UI)
    Given User go to the xm.com
    Then User is on page "Home"
    Then User (open_menu) "Trading"
    Then User (open_menu) "Stocks"
    Then User is on page "Stocks"
    Then User (choose stock filter) "Norway"


#  Use Case:
#  1. Open Home page (make any check here if needed).
#  2. Click the “Trading” link located at the top menu (make any check here if needed).
#  3. Click on “Stocks” (make any check here if needed).
#  4. Choose the &quot;Norway&quot; stock filter.
#  5. Get data from the table for the &quot;Orkla ASA (ORK.OL)&quot;  (make any check here if
#  needed).
#  6. Click on the &quot;READ MORE&quot; at this brand.
#  7. Compare the data from the previous table with data in &quot;Trading Conditions&quot;.