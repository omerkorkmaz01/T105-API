package test;

import baseURL.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuapp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C24_Post_Deserialization extends HerokuappBaseUrl {

    /*
      https://restful-booker.herokuapp.com/booking url'ine asagidaki
      body'ye sahip bir POST request gonderdigimizde donen response'un
      id haric asagidaki gibi oldugunu test edin.
        Request body
   {
        "firstname" : "Ali",
        "lastname" : "Bak",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
                 "checkin" : "2021-06-01",
                 "checkout" : "2021-06-10"
                          },
        "additionalneeds" : "wi-fi"
    }
       Response Body
       {
       "bookingid":24,
       "booking":{
           "firstname":"Ali",
           "lastname":"Bak",
           "totalprice":500,
           "depositpaid":false,
           "bookingdates":{
               "checkin":"2021-06-01",
               "checkout":"2021-06-10"
           },
           "additionalneeds":"wi-fi"
           }
       }
   */

    @Test
    public void post01(){

        // 1 - URL ve Body hazirla

        specHerokuapp.pathParams("pp1","booking");

        TestDataHerokuapp testDataHerokuapp= new TestDataHerokuapp();

        HashMap<String,Object> reqBody = testDataHerokuapp.bookingOlusturMap();
        // booking olmasının sebebi bizim request body'miz booking body'leri iceriyor

        // 2 - Expected Data hazirla

        HashMap<String,Object> expData= testDataHerokuapp.expBodyOlusturMap();

        // 3 - Response'i kaydet

        Response response = given().
                spec(specHerokuapp).
                contentType(ContentType.JSON).
                when().body(reqBody).
                post("/{pp1}");

        // 4 - Assertion
/*
      {
            "bookingid":24,
            "booking":{
                    "firstname":"Ali",
                    "lastname":"Bak",
                    "totalprice":500,
                    "depositpaid":false,
                    "bookingdates":{
                             "checkin":"2021-06-01",
                             "checkout":"2021-06-10"
                             },
                    "additionalneeds":"wi-fi"
                           }
 */
        HashMap <String,Object> resqMap = response.as(HashMap.class);

        Assert.assertEquals(((Map)(expData.get("booking"))).get("firstname"),
                            ((Map)(resqMap.get("booking"))).get("firstname"));

        Assert.assertEquals(((Map)(expData.get("booking"))).get("lastname"),
                            ((Map)(resqMap.get("booking"))).get("lastname"));

        Assert.assertEquals(((Map)(expData.get("booking"))).get("totalprice"),
                            ((Map)(resqMap.get("booking"))).get("totalprice"));

        Assert.assertEquals(((Map)(expData.get("booking"))).get("depositpaid"),
                            ((Map)(resqMap.get("booking"))).get("depositpaid"));

        Assert.assertEquals(((Map)(expData.get("booking"))).get("additionalneeds"),
                            ((Map)(resqMap.get("booking"))).get("additionalneeds"));

        Assert.assertEquals(((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkin"),
                            ((Map)(((Map)(resqMap.get("booking"))).get("bookingdates"))).get("checkin"));

        Assert.assertEquals(((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkout"),
                            ((Map)(((Map)(resqMap.get("booking"))).get("bookingdates"))).get("checkout"));
    }
}
