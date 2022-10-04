package rest_utility;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;

public class RestTutorials {


    public void test() {

        Response r = RestAssured.get("");

        given().
            get("https://reqres.in/api/users?page=2").
        then().
            rootPath("profile.page.data.person").
            body("name[1]", is("kiran")).
            body("name[2]", equalTo("naksh")).
            body("name[1]", contains("kiran")).
            body("name[1]", hasItems("kiran", "Naksh", "Achu")).
            body(hasXPath("/data/person")).
            body("xpath", notNullValue()).

                detachRootPath("person").
            body("person.name[3]", is("ashvini")).
            log().all();

        given().get("https://reqres.in/api/users?page=2").then().assertThat().statusLine(containsString(""));
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).contentType(ContentType.JSON);
        given().get("https://reqres.in/api/users?page=2").then().assertThat().body("thumbnail url", endsWith("test"));
        given().get("https://reqres.in/api/users?page=2").then().time(lessThan(50L));
        given().get("https://reqres.in/api/users?page=2").then().time(greaterThan(50L), TimeUnit.SECONDS);

        given().get("https://reqres.in/api/users?page=2").then().assertThat().body("thumbnail url", response -> equalTo("test"));

        //get response as string
        String str =  given().get("https://reqres.in/api/users?page=2").asString();
        List<String> ls = from(str).getList("RestResponse.result.name");
        System.out.println("ListSize: " + ls.size());
        for(String country : ls){
            if(country.equals("solomn islands"))
                System.out.println("found my place");

        }

        String str1 =  given().get("https://reqres.in/api/users?page=2").asString();
        List<String> ls1 = from(str).getList("RestResponse.result.findAll {it.name.length() > 40}.name");
        System.out.println("ls");



        //check schema
        given().get("https://reqres.in/api/users?page=2").
            then().assertThat().body(matchesJsonSchemaInClasspath("json schema file path"));
        given().get("https://reqres.in/api/users?page=2").
            then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json schema file path"));

        // groovy features
        given().get("https://reqres.in/api/users?page=2").
        then().body("Restresponse.result.alpha3_code*.length.sum()", greaterThan(40));

        given().get("https://reqres.in/api/users?page=2").
        then().body("Restresponse.result.alpha3_code*.length.sum()", greaterThan(40));


    }


    public void params (){
        given().
                header("content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body("jsonOBJ)").
                queryParam("name", "value").  // used for GET
                formParam("name", "value").        // used for POST
                pathParam("name", "value").        // used for changing urls
                param("name", "value1").  // used for ALL methods, POST, it decides on itw own what params gto use
                param("name", "value1", "value2"). // can pass multiple values or as list obeject
                param("name", new ArrayList<>()). // can pass multiple values or as list obeject
                param("only one param").             // used for ALL methods, POST, it decides on its own what params to use
                when().
                post("api/users/").
//            post("api/users/{user}/{country}").                   used for path parameters
        then().
                statusCode(201).
                assertThat().body("user", equalTo("kiran")).
                log().all();

    }


    public void RequestResponseSpecs (){

        RequestSpecBuilder reqSpecBuild = new RequestSpecBuilder();
        reqSpecBuild.addCookie("Name", "Value");
        reqSpecBuild.addHeader("Name", "Value");
        reqSpecBuild.addHeader("content-type", "application/json");
        reqSpecBuild.setContentType(ContentType.JSON);
        reqSpecBuild.setAccept(ContentType.JSON);
        reqSpecBuild.addParam("Name", "Value");
        JSONObject jObj = new JSONObject();
            jObj.put("name", "value");
        reqSpecBuild.setBody(jObj.toString());
        RequestSpecification reqSpec = reqSpecBuild.build();

        RequestSpecBuilder reqSpecBuild1 = new RequestSpecBuilder();
        RequestSpecification requestSpecification1 = reqSpecBuild1.build();
        requestSpecification1.pathParams("", "");
        requestSpecification1.cookie("", "");
        requestSpecification1.body("jsonBody");
        requestSpecification1.relaxedHTTPSValidation();

        Response rs = given().spec(requestSpecification1).when().request(Method.GET, "https://reqres.in/api/users");


        ResponseSpecBuilder resSpecBuild = new ResponseSpecBuilder();
        resSpecBuild.expectStatusCode(200);
        resSpecBuild.expectCookie("Name", "Value");
        resSpecBuild.expectHeader("Name", "Value");
        resSpecBuild.expectHeader("content-type", "application/json");
        resSpecBuild.expectContentType(ContentType.JSON);




        ResponseSpecification resSpec = resSpecBuild.build();


        Response rs = given().spec(reqSpec).when().request(Method.GET);
        rs.then().spec(resSpec).log().ifError();

        given().spec(reqSpec).
        when().request(Method.GET, "https://reqres.in/api/users").
        then().spec(resSpec).time(lessThan(4000L));

        reqSpec.log().all();
        Response r = given().spec(reqSpec).
        when().request(Method.GET, "https://reqres.in/api/users");

        r.then().spec(resSpec).
        time(lessThan(4000L)).log().all();
        rs.then().log().all();
    }


    public void certificates(){

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.useRelaxedHTTPSValidation("TLS");
        RestAssured.given().relaxedHTTPSValidation().when().get("https://reqres.in/api/users?page=2");
        given().relaxedHTTPSValidation("TLS").when().get("https://reqres.in/api/users?page=2");

        // serilization & deserilization for building complex request bodies

        // if content type is not defined in apis, then we set  default parsers
        RestAssured.defaultParser = Parser.JSON; // XML or TEXT
        given().get("endPoint").then().using().defaultParser(Parser.JSON);

        // for custom parsers
        RestAssured.registerParser("application/vnd.uoml+xml", Parser.XML);
        RestAssured.unregisterParser("application/vnd.uoml+xml");

    }


    public void authentication(){
        RestAssured.authentication = basic ("", "");
        RestAssured.authentication = preemptive().basic("", "");
        RestAssured.authentication = digest ("", "");

        given().get("endpoint").then().statusCode(200);

        // rest assured will NOT send creds to server until it specifically asks - challenged
        given().auth().basic("username", "pasword").get("endpoint").then().statusCode(200);
        // rest assured will send creds to server berfoe it asks - basic pre-emptive
        given().auth().preemptive().basic("username", "pasword").get("endpoint").then().statusCode(200);
        // digestive auth - mmore secure
        given().auth().digest("username", "pasword").get("endpoint").then().statusCode(200);


        
    }

}
