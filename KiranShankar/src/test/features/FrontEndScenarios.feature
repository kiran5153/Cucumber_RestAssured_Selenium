@front-end
Feature: Front-end Testcases

  Background: Open browser
    Given User is on Coin Marketcap homepage

  Scenario: UI_Test1
    When I access View All link "100" results are displayed

  Scenario: UI_Test2
    When I click on currency ellipsis and Add to Watchlist
      | Bitcoin |
      | Litecoin |
      | XRP |
      | Ethereum |
      | Tether |
    And I open "Watchlist" in a different browser tab and access it
    Then I verify all currencies are added to the watchlist
      | Bitcoin |
      | Litecoin |
      | XRP |
      | Ethereum |
      | Tether |

  Scenario: UI_Test3
    When I access the dropdown menu on the Cryptocurrencies tab and select "Full List" option on this menu
    And I record data for first Crypto currency displayed
    And I apply filters
      | filterName | Range      |
      | price      | 500        |
      | marketcap  | 100000000  |
    Then I verify against the data recorded earlier

