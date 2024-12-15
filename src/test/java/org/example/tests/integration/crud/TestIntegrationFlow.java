package org.example.tests.integration.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.pojos.Booking;
import org.example.pojos.BookingResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TestIntegrationFlow extends BaseTest {

    @Description("TC#1 : Verify the creation of new booking")
    @Owner("Suneetha")
    @Test(groups="Integration",priority=1)
    public void testCreateBooking(ITestContext context){

        requestSpecification
                .basePath(APIConstants.Create_update_url);
        response=
                RestAssured.given(requestSpecification)
                        .when()
                        .body(payloadManager.createPayloadBookingAsString())
                        .post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

        context.setAttribute("bookingid",bookingResponse.getBookingid());
    }
    @Description("TC#2 : Verify booking id which was created previous test")
    @Owner("Suneetha")
    @Test(groups="Integration",priority=2)
    public void testVerifyBookingId(ITestContext context){
        Integer bookingid=(Integer)context.getAttribute("bookingid");

        String basePathGET=APIConstants.Create_update_url +"/"+bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response=RestAssured.given(requestSpecification)
                .when().get();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        Booking booking= payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotBlank().isNotNull().isNotEmpty();
        assertThat(booking.getFirstname()).isEqualTo("James");

    }

    @Description("TC#3 : Verify update booking details ")
    @Owner("Suneetha")
    @Test(groups="Integration",priority=3)
    public void testUpdateBooking(ITestContext context){

    String token=getToken();
    context.setAttribute("token",token);
    Integer bookingid=(Integer)context.getAttribute("bookingid");
    String basePathPUTPATCH=APIConstants.Create_update_url +"/" +bookingid;

    System.out.println(basePathPUTPATCH);

    requestSpecification.basePath(basePathPUTPATCH);
            response=RestAssured.given(requestSpecification).cookie("token",token)

            .when().body(payloadManager.fullUpdatePayloadAsString()).put();
    validatableResponse=response.then().log().all();

    Booking booking= payloadManager.getResponseFromJSON(response.asString());

    // assert J
        assertThat(booking.getFirstname()).isNotEmpty().isNotNull();
        assertThat(booking.getTotalprice()).isNotNegative().isNotZero();
        assertThat(booking.getLastname()).isNotEmpty().isEqualTo("Raj");
    }
    @Description("TC#3 : Verify deletion of booking details ")
    @Owner("Suneetha")
    @Test(groups="Integration",priority=4)
    public void testDeleteBooking(ITestContext context){
        String token=(String)context.getAttribute("token");
        Integer bookingid=(Integer)context.getAttribute("bookingid");

        String basePathDELETE=APIConstants.Create_update_url +"/"+bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token",token);
        validatableResponse=RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);


    }
}
