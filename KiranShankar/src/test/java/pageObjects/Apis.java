package pageObjects;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.GenericUtility;
import utilities.RestUtility;
import java.util.HashMap;

public class Apis {

    RestUtility restUtility;
    public Apis () {
        restUtility = new RestUtility();
    }

    private static String currID=null;
    private static Response response=null;

//**********************************METHODS*****************************************

    public void retrieveCurrencyID (String currency_symbol){
        try {
            String endPoint = GenericUtility.readConfigs("endpoint_cryptocurrencyMap");
            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("symbol", currency_symbol);
            Response response = restUtility.executeRequest(endPoint, RestUtility.RequestMethod.GET, parameter, 200);

            currID =  restUtility.getResponseValue(response, "data.id[0]");
            Assert.assertTrue("Currency ID value is Null", currID!=null);
            Assert.assertTrue("Error while execute the request",
                    Integer.parseInt(restUtility.getResponseValue(response, "status.error_code"))==0);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void convertToCurrency(String currency_symbol, String convertTo_Currency, int amount){
        try{
            String endPoint = GenericUtility.readConfigs("endpoint_priceConvertion");
            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("id", currID);
            parameter.put("amount", String.valueOf(amount));
            parameter.put("convert", convertTo_Currency);

            response = restUtility.executeRequest(endPoint, RestUtility.RequestMethod.GET, parameter, 200);

            String attribute = "data.quote." + convertTo_Currency.toUpperCase() +".price";
            String convrtedAmount  =  restUtility.getResponseValue(response, attribute);
            Assert.assertTrue("Failed to convert amount '"+amount +"', from " +  currency_symbol + " to " + convertTo_Currency, !convrtedAmount.isEmpty());
            Assert.assertTrue("Error while execute the request",
                    Integer.parseInt(restUtility.getResponseValue(response, "status.error_code"))==0);

        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

    public void retrieveTechnicalDoc (int currency_ID){
        try {
            String endPoint = GenericUtility.readConfigs("endpoint_metaData");
            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("id", String.valueOf(currency_ID));
            response = restUtility.executeRequest(endPoint, RestUtility.RequestMethod.GET, parameter, 200);

            String attribute = "data." + currency_ID + ".urls.website[0]";
            String techDocWebsite = restUtility.getResponseValue(response, attribute);
            Assert.assertTrue("Technical Documentation Website is Null", techDocWebsite!=null);
            Assert.assertTrue("Error while execute the request",
                    Integer.parseInt(restUtility.getResponseValue(response, "status.error_code"))==0);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void verifyFields (int currency_ID, String expectedField, String expectedValues){
        try {
            String attribute;
            switch (expectedField.toLowerCase()){
                case "technical_doc":
                    attribute = "data." + currency_ID + ".urls." + expectedField.toLowerCase() + "[0]";
                    break;
                case "tags":
                    attribute = "data." + currency_ID + "." + expectedField.toLowerCase() + "[0]";
                    break;
                default:
                    attribute = "data." + currency_ID + "." + expectedField.toLowerCase();
            }
            String value = String.valueOf(restUtility.getResponseValue(response, attribute));

            Assert.assertTrue(expectedField+ "-" + expectedValues+" mismatch", expectedValues.equalsIgnoreCase(value));
            System.out.println("**********"+ expectedField + "---" + expectedValues+" validated successfully**********");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
