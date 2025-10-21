package tests.day04;

import base_urls.PetBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.ResponsePetPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetstoreAPITest  extends PetBaseUrl {
    /*
  Task 2: Petstore API - Create a Pet with POJO Payload
Objective: Create a new pet in the Petstore API using a POJO as the request payload.
Requirements:
Reference the API documentation at https://petstore.swagger.io/
Create a POJO class representing a Pet object with properties like:
id
name
category
photoUrls
status
tags
Send a POST request to the create pet endpoint with your POJO as the body
Assert that the response status code is successful (200 or 201)
Assert that the returned pet object contains the data you sent
     */

    @Test
    public void testRandomUser() {
    String stpojo  = """
            {
              "id": 509,
              "category": {
                "id": 0,
                "name": "Bird"
              },
              "name": "Twete",
              "photoUrls": [
                "string"
              ],
              "tags": [
                {
                  "id": 0,
                  "name": "Blue"
                }
              ],
              "status": "solt"
            }
            """;

        ResponsePetPojo payload = ObjectMapperUtils.convertJsonToJava(stpojo,ResponsePetPojo.class);

        Response response = given(spec).body(payload).post("/v2/pet");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body("id", equalTo(payload.getId()),
                        "category.name", equalTo(payload.getCategory().getName()),
                        "name", equalTo(payload.getName()),
                        "photoUrls", equalTo(payload.getPhotoUrls()),
                        "status", equalTo(payload.getStatus())
                );



    }
}




