package utilities;

import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

public class RestUtility {

    // Request Method types
    public enum RequestMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    // executes request and returns response
    public Response executeRequest(String endPoint, RequestMethod requestMth, HashMap<String, String> parameter, int statusCode) {
        Response response = null;
        try {
            String baseURI = GenericUtility.readConfigs("baseURI");
            String basePath = GenericUtility.readConfigs("basePath");
            String accessKey = GenericUtility.readConfigs("accessKey");

            RequestSpecBuilder reqSpecBuild = new RequestSpecBuilder();
            reqSpecBuild.setBaseUri(baseURI);
            reqSpecBuild.setBasePath(basePath);
            reqSpecBuild.setAccept(ContentType.JSON);
            reqSpecBuild.addHeader("X-CMC_PRO_API_KEY", accessKey);
            reqSpecBuild.addParams(parameter);
            RequestSpecification requestSpecification = reqSpecBuild.build();

            ResponseSpecBuilder resSpecBuild = new ResponseSpecBuilder();
            resSpecBuild.expectStatusCode(statusCode);
            resSpecBuild.expectContentType(ContentType.JSON);
            ResponseSpecification responseSpecification = resSpecBuild.build();

            requestSpecification.log().all();
            System.out.println("*************** REQUEST*****************************");

            response = given().spec(requestSpecification).
                    when().request(requestMth.toString(), endPoint.trim());
            response.then().spec(responseSpecification);

            System.out.println("*************** RESPONSE*****************************");
            response.then().log().all();
//            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return response;
    }

    //Get Response value of a attribute
    public String getResponseValue(Response response, String attribute) {
        String responseVal=null;
        try {
            if (response.body().path(attribute)==null)
                responseVal = String.valueOf(responseVal);
            else
                responseVal = response.body().path(attribute).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return responseVal;
    }

}
