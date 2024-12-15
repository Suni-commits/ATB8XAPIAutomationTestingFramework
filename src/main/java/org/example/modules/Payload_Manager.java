package org.example.modules;

import com.google.gson.Gson;
import org.example.pojos.*;


public class Payload_Manager {
    // Converting JAVA object to String
    // Using GSON library object will convert to String

 Gson gson=new Gson();


    public  String createPayloadBookingAsString(){

        Booking booking=new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brouse");
        booking.setTotalprice(2021);
        booking.setDepositpaid(true);

        Bookingdates bookingdate=new Bookingdates();
        bookingdate.setCheckin("2024-04-02");
        bookingdate.setCheckout("2024-08-02");
        booking.setBookingdates(bookingdate);
        booking.setAdditionalneeds("Lunch & Dinner");

        Gson gson=  new Gson();
        String jsonStringpayload=gson.toJson(booking);

        System.out.println(jsonStringpayload);
        return jsonStringpayload;

    }

    public BookingResponse bookingResponseJava(String responseString){
        //Converting back to STRING to  JAVA Object
        BookingResponse bookingResponse=gson.fromJson(responseString,BookingResponse.class);
        return bookingResponse;

    }

    public Booking getResponseFromJSON(String getResponse){

        Booking booking=gson.fromJson(getResponse,Booking.class);
        return booking;
    }

    public String setAuthPayload() {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String gsonpayloadString = gson.toJson(auth);
        System.out.println("Payload set to the --> " + gsonpayloadString);
        return gsonpayloadString;

    }

    public String getTokenFromJSON(String tokenResponse){
        gson=new Gson();
        TokenResponse tokenResponse1=gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();

    }

    public String fullUpdatePayloadAsString(){
        Booking booking=new Booking();
        booking.setFirstname("Ramesh");
        booking.setLastname("Raj");
        booking.setTotalprice(1121);
        booking.setDepositpaid(true);

        Bookingdates bookingdate=new Bookingdates();
        bookingdate.setCheckin("2024-03-02");
        bookingdate.setCheckout("2024-08-02");
        booking.setBookingdates(bookingdate);
        booking.setAdditionalneeds("BreakFast & Dinner");

        Gson gson=  new Gson();
        String udatejsonStringpayload=gson.toJson(booking);

        return udatejsonStringpayload;
    }

}
