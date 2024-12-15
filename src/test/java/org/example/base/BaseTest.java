package org.example.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.asserts.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.modules.Payload_Manager;
import org.testng.annotations.BeforeTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class BaseTest {

   public RequestSpecification requestSpecification;
   public Response response;
   public AssertActions assertActions;
   public Payload_Manager payloadManager;
   public JsonPath jsonpath;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp(){
    // Base URL nd content Type are same for all  tests
       payloadManager=new Payload_Manager();
       assertActions=new AssertActions();
       requestSpecification= RestAssured.given()
               .baseUri(APIConstants.BaseUrl)
               .contentType(ContentType.JSON)
               .log().all();


    assertThat(requestSpecification).isNotNull();

    }

public String getToken(){

        requestSpecification=RestAssured
                .given()
                .baseUri(APIConstants.BaseUrl)
                .basePath(APIConstants.auth_url);

        String payoad= payloadManager.setAuthPayload();

        response=requestSpecification.contentType(ContentType.JSON).body(payoad).when().post();

        String token=payloadManager.getTokenFromJSON(response.asString());
        return token;

}


}
