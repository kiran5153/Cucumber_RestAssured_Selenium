$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/features/BackEndScenarios.feature");
formatter.feature({
  "name": "Back-end Testcases",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@back-end"
    }
  ]
});
formatter.scenarioOutline({
  "name": "API_Test1",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I retrieve ID for the currency \"\u003ccurrency_symbol\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "I convert \"\u003ccurrency_symbol\u003e\" to \"\u003cconvertTo_Currency\u003e\" for \"\u003camount\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "currency_symbol",
        "convertTo_Currency",
        "amount"
      ]
    },
    {
      "cells": [
        "BTC",
        "BOLI",
        "50"
      ]
    },
    {
      "cells": [
        "USDT",
        "BOLI",
        "100"
      ]
    },
    {
      "cells": [
        "ETH",
        "BOLI",
        "200"
      ]
    }
  ]
});
formatter.scenario({
  "name": "API_Test1",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@back-end"
    }
  ]
});
formatter.step({
  "name": "I retrieve ID for the currency \"BTC\"",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iRetrieveTheIDOfCurrency(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I convert \"BTC\" to \"BOLI\" for \"50\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iConvertToFor(java.lang.String,java.lang.String,int)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "API_Test1",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@back-end"
    }
  ]
});
formatter.step({
  "name": "I retrieve ID for the currency \"USDT\"",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iRetrieveTheIDOfCurrency(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I convert \"USDT\" to \"BOLI\" for \"100\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iConvertToFor(java.lang.String,java.lang.String,int)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "API_Test1",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@back-end"
    }
  ]
});
formatter.step({
  "name": "I retrieve ID for the currency \"ETH\"",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iRetrieveTheIDOfCurrency(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I convert \"ETH\" to \"BOLI\" for \"200\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iConvertToFor(java.lang.String,java.lang.String,int)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "API_Test2",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@back-end"
    }
  ]
});
formatter.step({
  "name": "I retrieve the Technical documentation Website for currency id \"1027\"",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iRetrieveTheTechnicalDocumentationWebsiteForCurrencyWithId(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should see the following fields for currency id \"1027\"",
  "rows": [
    {},
    {},
    {},
    {},
    {},
    {},
    {}
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinations.API_Stepdefs.iShouldSeeTheFollowingFields(int,io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("file:src/test/features/FrontEndScenarios.feature");
formatter.feature({
  "name": "Front-end Testcases",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@front-end"
    }
  ]
});
formatter.background({
  "name": "Open browser",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User is on Coin Marketcap homepage",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.verifyUserIsOnCoinMarketcapHomepage()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "UI_Test1",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@front-end"
    }
  ]
});
formatter.step({
  "name": "I access View All link \"100\" results are displayed",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.verifyViewAllLinkDisplaysResults(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.background({
  "name": "Open browser",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User is on Coin Marketcap homepage",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.verifyUserIsOnCoinMarketcapHomepage()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "UI_Test2",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@front-end"
    }
  ]
});
formatter.step({
  "name": "I click on currency ellipsis and Add to Watchlist",
  "rows": [
    {},
    {},
    {},
    {},
    {}
  ],
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.addCurrencyToWatchlist(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I open \"Watchlist\" in a different browser tab and access it",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.OpenHeaderTabInADifferentWindow(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify all currencies are added to the watchlist",
  "rows": [
    {},
    {},
    {},
    {},
    {}
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.verifyAllCurrenciesInWatchlist(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.background({
  "name": "Open browser",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User is on Coin Marketcap homepage",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.verifyUserIsOnCoinMarketcapHomepage()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "UI_Test3",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@front-end"
    }
  ]
});
formatter.step({
  "name": "I access the dropdown menu on the Cryptocurrencies tab and select \"Full List\" option on this menu",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.selectMenuOptionOnCryptocurrenciesTab(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I record data for first Crypto currency displayed",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.iRecordDataForFirstCryptoCurrencyDisplayed()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I apply filters",
  "rows": [
    {},
    {},
    {}
  ],
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.iApplyFilters(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify against the data recorded earlier",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinations.UI_Stepdefs.checkAgainstTheDataRecorded()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});