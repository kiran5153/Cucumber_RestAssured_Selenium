@back-end
Feature: Back-end Testcases

  Scenario Outline: API_Test1
    When I retrieve ID for the currency "<currency_symbol>"
    Then I convert "<currency_symbol>" to "<convertTo_Currency>" for "<amount>"
    Examples:
      | currency_symbol | convertTo_Currency  | amount |
      | BTC             | BOLI                | 50     |
      | USDT            | BOLI                | 100    |
      | ETH             | BOLI                | 200    |

  Scenario: API_Test2
    When I retrieve the Technical documentation Website for currency id "1027"
    Then I should see the following fields for currency id "1027"
      | fields        | values                                                         |
      | logo          | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png   |
      | technical_doc | https://github.com/ethereum/wiki/wiki/White-Paper              |
      | symbol        | ETH                                                            |
      | date_added    | 2015-08-07T00:00:00.000Z                                       |
      | platform      | null                                                           |
      | tags          | mineable                                                       |

