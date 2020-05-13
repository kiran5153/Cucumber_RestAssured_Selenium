package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverFactory;
import utilities.GenericUtility;
import java.util.*;

public class Cryptocurrencies  {

    GenericUtility genericUtility;
    HashMap<String, String> currDetails;

    public Cryptocurrencies () {
        PageFactory.initElements(DriverFactory.getWebDriver(), this);
        genericUtility = new GenericUtility();
    }


//********************************OBJECTS*********************************************

    @FindBy(xpath = "//div[contains(@class, 'cmc-table__column-name')]//a")
    private List<WebElement> cryptoCurrList;

    @FindBy(xpath = "//div[contains(@class, 'cmc-split')]//a[@href='/all/views/all/']")
    private WebElement viewAllButton;

    @FindBy(xpath = "a[class*='cmc-view-all__back-button']")
    private WebElement backToTop100;
    
    @FindBy(xpath = "//span[contains(@class, 'openStateToggleIcon')]")
    private WebElement surveyIcon;

    @FindBy(xpath = "//div[contains(@class, 'cmc-table__column-name')]//a/ancestor::td/following-sibling::td//div[@class='cmc-popover__trigger']")
    private List<WebElement> ellipsis;

    @FindBy(css = "ul span[class='cmc-link']")
    private WebElement addToWatchlist;

    @FindBy(xpath = "//a[@href='/watchlist/']")
    private WebElement watchlistTab;

    @FindBy(css = ".cmc-cookie-policy-banner__close")
    private WebElement cookieX;

    @FindBy(xpath = "//li[contains(@class, 'cmc-tab__trigger')]//span[text()='Cryptocurrencies']")
    private WebElement cryptoCurrTab;

    @FindBy(css = "li>a[href='/all/views/all/']")
    private WebElement fullListMenuOption;

    @FindBy(css = "button[data-qa-id='table-listing-filters-toggle']")
    private WebElement filtersButton;

    @FindBy(css = "div[data-qa-id='range-filter-price']")
    private WebElement priceFilter;

    @FindBy(css = "div[data-qa-id='range-filter-mcap']")
    private WebElement mcapFilter;

    @FindBy(css = "input[data-qa-id='range-filter-input-min']")
    private WebElement minRangeValue;

    @FindBy(css = "input[data-qa-id='range-filter-input-max']")
    private WebElement maxRangeValue;

    @FindBy(css = "button[data-qa-id='filter-dd-button-apply']")
    private WebElement applyFilter;

    @FindBy(xpath = "//td[contains(@class, 'sort-by__symbol')]/div")
    private List<WebElement> currSymbolsList;

    @FindBy(xpath = "//td[contains(@class, 'market-cap')]/div")
    private List<WebElement> mcapList;

    @FindBy(xpath = "//td[contains(@class, 'sort-by__price')]/a")
    private List<WebElement> priceList;


//**********************************METHODS*****************************************

    public void verifyCoinMarketcapHomepageisOpened () {
        try {
            genericUtility.waitForPageLoad();
            Assert.assertTrue("Coin Marketcap page is not opened/loaded", DriverFactory.getWebDriver().getCurrentUrl().equalsIgnoreCase(GenericUtility.readConfigs("coinmarketcapURL")));
            cookieX.click();
//            if (surveyIcon.isDisplayed())
//                surveyIcon.click();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void verifyresultsDisplayed (String results) {
        try {
            int intlCurrCnt = cryptoCurrList.size();
            viewAllButton.click();
            genericUtility.waitForPageLoad();
            genericUtility.waitForVisibilityOfElement(backToTop100);
            int CurrCnt = cryptoCurrList.size();
            Assert.assertTrue("100 results are not displayed on accessing ViewAll link. Expected: "+ results +
                    ", Actual: " + (CurrCnt-intlCurrCnt) , CurrCnt==intlCurrCnt+Integer.parseInt(results));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void addToWatchlist (List currencies) {
        try {
            boolean currFound = false;
            for (int i=0; i<currencies.size(); i++) {
                for (int j = 0; j < cryptoCurrList.size(); j++) {
                    // check if currency is listed
                    if (cryptoCurrList.get(j).getText().trim().equalsIgnoreCase(currencies.get(i).toString().trim())){
                        currFound = true;
                        System.out.println(currencies.get(i).toString()+" : added to watchlist");
                        //add to watchlist
                        ellipsis.get(j).click();
                        addToWatchlist.click();
                        break;
                    }
                }
                Assert.assertFalse("Crypto Currency is not listed: "+currencies.get(i).toString(), currFound==false);
                currFound = false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void OpenHeaderTabsInNewWindow (String tabName) {
        try {
            switch (tabName.toLowerCase()) {
                case "watchlist":
                {
                    //open watchlist in a new tab
                    watchlistTab.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
                    //switch to new tab
                    boolean brwSwitch = genericUtility.switchToNewWindow(tabName);
                    Assert.assertTrue("Failed to switch to " +tabName+ " new window tab", brwSwitch);
                    break;
                }
                default:
                    Assert.fail("Invalid Header tabname: " +tabName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void verifyCurrenciesInWatchlist (List currencies) {
        try {
            boolean currFound = false;
            for (int i=0; i<currencies.size(); i++) {
                for (int j = 0; j < cryptoCurrList.size(); j++) {
                    // check if currency is listed
                    if (cryptoCurrList.get(j).getText().trim().equalsIgnoreCase(currencies.get(i).toString().trim())){
                        currFound = true;
                        System.out.println(currencies.get(i).toString()+" : is present watchlist");
                        break;
                    }
                }
                Assert.assertFalse("Crypto Currency is not present in wishlist: "+currencies.get(i).toString(), currFound==false);
                currFound = false;

            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void selectMenuOptionOnCryptocurrenciesDropdown (String menuItem) {
        try {
            switch (menuItem.toLowerCase()) {
                case "full list":
                {
                    cryptoCurrTab.click();
                    fullListMenuOption.click();
                    genericUtility.waitForPageLoad();
                    break;
                }
                default:
                    Assert.fail("Invalid menu option in Cryptocurrencies dropdown: " +menuItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void recordCurrencyDetails () {
        try {
            if (cryptoCurrList.size() != currSymbolsList.size() && cryptoCurrList.size() !=  mcapList.size()
                    && cryptoCurrList.size() != priceList.size())
                Assert.fail("XPATH issue: Count of Cryptocurrenicies, Symbols, Mcaps, Price do NOT match");

            currDetails = new HashMap<>();
            currDetails.put("name", cryptoCurrList.get(0).getText());
            currDetails.put("symbol", currSymbolsList.get(0).getText());
            currDetails.put("mcap", mcapList.get(0).getText());
            currDetails.put("price", priceList.get(0).getText());

            System.out.println("Currency details captured for " + cryptoCurrList.get(0).getText() + "(" + currSymbolsList.get(0).getText() +
                    ") - mcap '" + mcapList.get(0).getText() + "', price '" + priceList.get(0).getText() +"'");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void applyFilters (String filterName, String range) {
        try {
            String minRange="", maxRange="";
            if (filtersButton.getAttribute("class").contains("krkHSX"))
                filtersButton.click();

            switch (filterName.trim().toLowerCase()) {
                case "price":
                    minRange = String.valueOf(Double.parseDouble(genericUtility.formatCurrency(currDetails.get("price")))-Double.parseDouble(range));
                    maxRange = String.valueOf(Double.parseDouble(genericUtility.formatCurrency(currDetails.get("price")))+Double.parseDouble(range));
                    priceFilter.click();
                    break;
                case "marketcap":
                    minRange = String.valueOf(Long.parseLong(genericUtility.formatCurrency(currDetails.get("mcap"))) - Long.parseLong(range));
                    maxRange = String.valueOf(Long.parseLong(genericUtility.formatCurrency(currDetails.get("mcap"))) + Long.parseLong(range));
                    mcapFilter.click();
                    break;
                default:
                    Assert.fail("Invalid filter name: " +filterName);
            }
            minRangeValue.sendKeys(minRange);
            maxRangeValue.sendKeys(maxRange);
            applyFilter.click();
            genericUtility.waitForPageLoad();
            System.out.println("filter applied: " + filterName + "min range-" + minRange + ", max range- " + maxRange);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void checkAgainstTheDataRecorded () {
        try {
            genericUtility.waitForPageLoad();
            if (cryptoCurrList.size() != currSymbolsList.size() && cryptoCurrList.size() !=  mcapList.size()
                    && cryptoCurrList.size() != priceList.size())
                Assert.fail("XPATH issue: Count of Cryptocurrenicies, Symbols, Mcaps, Price do NOT match");

            boolean currFound = false;
            for (int i = 0; i < cryptoCurrList.size(); i++) {
                // check if currency is listed in filtered list
                if (cryptoCurrList.get(i).getText().trim().equalsIgnoreCase(currDetails.get("name"))) {
                    currFound = true;
                    Assert.assertTrue(currDetails.get("symbol")+ "- Crypto currency symbol does not match the earlier recorded data, Actual: "
                            + currSymbolsList.get(i).getText(), currSymbolsList.get(i).getText().trim().equalsIgnoreCase(currDetails.get("symbol")));
//                    Assert.assertTrue(currDetails.get("price")+ "- Price does not match the earlier recorded data, Actual: "
//                            + priceList.get(i).getText(), priceList.get(i).getText().trim().equalsIgnoreCase(currDetails.get("price")));
//                    Assert.assertTrue(currDetails.get("mcap")+ "- Marketcap does not match the earlier recorded data, Actual: "
//                            + mcapList.get(i).getText(), mcapList.get(i).getText().trim().equalsIgnoreCase(currDetails.get("mcap")));
                    System.out.println(currDetails.get("name")+ "- Crypto Currency is listed in the applied filter");
                    break;
                }
            }
            Assert.assertFalse(currDetails.get("name")+ "- Crypto Currency is not listed in the applied filter", currFound == false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
