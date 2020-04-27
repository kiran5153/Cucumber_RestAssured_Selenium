package stepDefinations;

import basePage.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.List;
import java.util.Map;

public class UI_Stepdefs {

    private BasePage basePage = new BasePage();

    @Given("^User is on Coin Marketcap homepage$")
    public void verifyUserIsOnCoinMarketcapHomepage() {
        basePage.cryptoCurrPgObj.verifyCoinMarketcapHomepageisOpened();
    }

    @When("^I access View All link \"(.*)\" results are displayed$")
    public void verifyViewAllLinkDisplaysResults(String results) {
        basePage.cryptoCurrPgObj.verifyresultsDisplayed(results);
    }

    @When("^I click on currency ellipsis and Add to Watchlist$")
    public void addCurrencyToWatchlist(DataTable cryptocurrencies) {
        List<String> currencies=  cryptocurrencies.asList();
        basePage.cryptoCurrPgObj.addToWatchlist(currencies);
    }

    @And("^I open \"(.*)\" in a different browser tab and access it$")
    public void OpenHeaderTabInADifferentWindow(String tabName) {
        basePage.cryptoCurrPgObj.OpenHeaderTabsInNewWindow(tabName);
    }

    @Then("^I verify all currencies are added to the watchlist$")
    public void verifyAllCurrenciesInWatchlist(DataTable cryptocurrencies) {
        List<String> currencies=  cryptocurrencies.asList();
        basePage.cryptoCurrPgObj.verifyCurrenciesInWatchlist(currencies);
    }

    @When("^I access the dropdown menu on the Cryptocurrencies tab and select \"(.*)\" option on this menu$")
    public void selectMenuOptionOnCryptocurrenciesTab(String menuItem) {
        basePage.cryptoCurrPgObj.selectMenuOptionOnCryptocurrenciesDropdown(menuItem);
    }

    @And("I record data for first Crypto currency displayed")
    public void iRecordDataForFirstCryptoCurrencyDisplayed() {
        basePage.cryptoCurrPgObj.recordCurrencyDetails();
    }

    @And("^I apply filters$")
    public void iApplyFilters(DataTable filters) {
        for (Map<Object, Object> filter : filters.asMaps(String.class, String.class)) {
            basePage.cryptoCurrPgObj.applyFilters((String) filter.get("filterName"), (String) filter.get("Range"));
        }

    }

    @Then("^I verify against the data recorded earlier$")
    public void checkAgainstTheDataRecorded() {
        basePage.cryptoCurrPgObj.checkAgainstTheDataRecorded();
    }


}
