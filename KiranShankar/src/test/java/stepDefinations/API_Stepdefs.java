package stepDefinations;

import basePage.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.Map;

public class API_Stepdefs {

    private BasePage basePage = new BasePage();

    @When("^I retrieve ID for the currency \"(.*)\"$")
    public void iRetrieveTheIDOfCurrency(String currency_symbol) {
        basePage.apiObj.retrieveCurrencyID(currency_symbol);
    }

    @Then("^I convert \"(.*)\" to \"(.*)\" for \"(.*)\"$")
    public void iConvertToFor(String currency_symbol, String convertTo_Currency, int amount) {
        basePage.apiObj.convertToCurrency(currency_symbol, convertTo_Currency, amount);
    }

    @When("^I retrieve the Technical documentation Website for currency id \"(.*)\"$")
    public void iRetrieveTheTechnicalDocumentationWebsiteForCurrencyWithId(int currency_ID) {
        basePage.apiObj.retrieveTechnicalDoc(currency_ID);
    }

    @Then("^I should see the following fields for currency id \"(.*)\"$")
    public void iShouldSeeTheFollowingFields(int currency_ID, DataTable fields) {
        for (Map<Object, Object> field : fields.asMaps(String.class, String.class)) {
            basePage.apiObj.verifyFields(currency_ID, (String) field.get("fields"), (String) field.get("values"));
        }
    }

}
