package rest_utility;

import com.mongodb.util.JSON;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ui_automator.MainPanel;

//Static imports to direstly use the Keywords without RestAssured prefixed
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredPractice {

    public void RestAssuredBasicTest() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        RestAssured.given().get("");
        get("");
        System.out.println(response.statusCode());
        System.out.println(response.getBody().toString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
    }

    public void StaticImports() {
        Response response = get("https://reqres.in/api/users?page=2");

        given().get("https://reqres.in/api/users?page=2").then().log().all();
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200);
        given().get("https://reqres.in/api/users?page=2").then().body("data.id[1]",equalTo(2));


        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).log().all();
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[1]",equalTo(2)).log().all();

        given().
                header("content-type", "application/json").
                accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
        when().
                get("https://reqres.in/api/users?page=2").
        then().
                statusCode(200).
                body("data.id[1]",equalTo(2)).
                rootPath("profile.data").
                body("data.names", hasItems("kiran", "naksh")).
                body("data.name.text()",equalTo("kiran")).
                detachRootPath("profile").
                body( hasXPath("/data/name"), contains("naksh")).
                body( "/data/name", contains("naksh")).
                body( hasXPath("/data/invoice[text()='40']")).
                log().all();

        given().
                header("content-type", "application/json").
                and().
                params("parameterName", "parameterValue").
                get("https://reqres.in/api/users?page=2").
        then().
                statusCode(200).
                body("firstnames", hasItems("lisa","david")).
                log().all();

    }

    public void differerntMethods() {
        Map<String, Object> post = new HashMap<String, Object>();
        post.put("name", "naksh");
        post.put("age", "2");
        System.out.println(post);
        JSONObject jObj1 = new JSONObject(post);
        //              OR
        JSONObject jObj2 = new JSONObject();
        jObj2.put("name", "naksh");
        jObj2.put("age", "2");

        baseURI = "https://reqres.in/";
        //POST
        given().
                header("content-type", "application/json").
                cookie("name", "value").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jObj2.toString()).
        when().
                post("api/users").
        then().
                statusCode(201).
                log().all();

        baseURI = "https://reqres.in/";
        //DELETE
        when().
                delete("api/users/2").
        then().
                statusCode(204).
                log().all();


/*
        list users          https://reqres.in/api/users
        get single user     https://reqres.in/api/users/2
        get single user     https://reqres.in/api/users?id=2

        filters             https://reqres.in/api/Users
                            https://reqres.in/api/subjects
                            https://reqres.in/api/subjects/1
                            https://reqres.in/api/subjects/1/users
                            https://reqres.in/api/subjects?id=1
                            https://reqres.in/api/subjects?name=automation

*/

        baseURI = "https://reqres.in/";
        given().
                header("content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                params("id", "2").
        when().
                get("api/users").
        then().
                statusCode(201).
                log().all();

    }

    @Parameters({"firstname", "lastname", "age"})
    //OR
    @Test(dataProvider = "dataForRest")
    public void postMth(String firstname, String lastname, String age) {
        JSONObject jObj2 = new JSONObject();
        jObj2.put("firstname", firstname);
        jObj2.put("lastname", lastname);
        jObj2.put("age", age);

        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.port = 8080;
        RestAssured.basePath = "country/";

        given().
                header("content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jObj2.toString()).
        when().
                post("api/users").
        then().
                statusCode(201).
                log().all().
                log().cookies().log().headers().log().body().
                log().ifStatusCodeIsEqualTo(200).
                log().ifError();
        Object n = 1;

    }

    @DataProvider(name="dataForRest")
    public Object[][]  testData(){
        return new Object[][] {
                {"naksh", "kiran", "2"},
                {"kiran", "shankar", "39"},
                {"ashvini", "ranganath", "35"}
        };
    }




/*
create your own API
websites, json server, Macoon
Link: https://github.com/typicode/json-server
install nodeJS
goto cmd
npm install -g json-server
** -g to install glabally

start json server
json-server --watch db.json

can host on localhost:3000
add json data in db.json file
 */



}
