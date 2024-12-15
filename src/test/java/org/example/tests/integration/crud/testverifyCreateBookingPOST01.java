package org.example.tests.integration.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.AssertActions;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.pojos.BookingResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertTrue;


public class testverifyCreateBookingPOST01 extends BaseTest {

  @Owner("Suneetha")
     @Description("Verify that the POST request is working fine")
    @Test
    public void testverifyCreateBookingPOST01(){

        requestSpecification
                .basePath(APIConstants.Create_update_url);
        response=
                RestAssured.given(requestSpecification)
                        .when()
                        .body(payloadManager.createPayloadBookingAsString())
                        .post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        //Asserts by default rest assured

        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));

        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

        //assert TestNg
       /* Assert.assertEquals(bookingResponse.getBooking().getFirstname(),Matchers.equalTo("James"));
      Assert.assertEquals(bookingResponse.getBooking().getLastname(),Matchers.equalTo("Brouse"));
        Assert.assertEquals(bookingResponse.getBooking().getTotalprice(),Matchers.equalTo(2021)); */

        //asssert actions
        AssertActions.verifyStatusCode(response,200);
        AssertActions.verifyResponseBody(bookingResponse.getBooking().getFirstname(),"James","Hello firstname is James");

        // AssertJ

        assertThat(bookingResponse.getBookingid()).isNotZero().isPositive();
        assertThat(bookingResponse.getBookingid()).isNotZero().isNotNull().isNotNegative();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotBlank().isEqualTo("James");


        //TestNg assertion
        assertTrue(bookingResponse.getBooking().getDepositpaid().booleanValue());

    }

}
