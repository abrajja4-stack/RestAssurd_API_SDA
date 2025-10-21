package tests.day03;

import base_urls.PetStoreUserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.CreateUserPojo;
import pojo.SuccefulUserCreationPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUseronPetstoreAPI extends PetStoreUserBaseUrl {

    /*
 Task: Write an automation test that creates a 'user' using the
Petstore API at https://petstore.swagger.io
 Requirements:
 1. Review the Petstore API documentation
 2. Identify the endpoint for creating users (/user)
 3. Create User POJO with all required fields
 4. Implement POST request to create user
 5. Validate successful creation with assertions
 */

    /* "id": "13",
            "username": "Laley",
            "firstName": "Layla",
           "lastName": "Aali",
           "email": "AliaLaila@gmail.com",
          "password": "AllLiala",
           "phone": "0500000000",
          "userStatus": 0
*/


    @Test
    public void testName() {
    SuccefulUserCreationPojo expectedData = new SuccefulUserCreationPojo(200, "unknown", 13+"");
    CreateUserPojo payload  = new CreateUserPojo(13, "Laley", "Layla", "Aali", "AliaLaila@gmail.com","AllLiala","0500000000",0);
        Response response = given(spec).body(payload ).post("/v2/user");
        response.prettyPrint();

        SuccefulUserCreationPojo actualData = response .as(SuccefulUserCreationPojo.class);
        assertEquals(actualData.getType() ,  expectedData .getType());
        assertEquals(actualData.getMessage() ,  expectedData .getMessage());
        assertEquals(actualData.getCode() ,  expectedData .getCode());









    }
}
