package day03;

import base_urls.ActivitiesAPIBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.ActivitiesPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CRUDOperationswithActivitiesAPI  extends ActivitiesAPIBaseUrl {
    /*
 Task: Write code that performs all CRUD operations on "activities"
using the Fake REST API at https://fakerestapi.azurewebsites.net
 Requirements:
 1. Use POJO classes for all operations
 2. Implement CREATE (POST) - Add new activity
 3. Implement READ (GET) - Retrieve activity details
 4. Implement UPDATE (PUT) - Modify existing activity
 5. Implement DELETE - Remove activity
 6. Add appropriate assertions for each operation
 */

    @Test
    public void postActivitiestest() {
ActivitiesPojo expectesData = new ActivitiesPojo(12, "Swimming", "2025-10-20T19:35:52.054Z",true);
        Response response = given(spec).body(expectesData).post("/api/v1/Activities");
        response.prettyPrint();
        ActivitiesPojo actualData = response.as(ActivitiesPojo.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getTitle(), expectesData.getTitle());
        assertEquals(actualData.getId(), expectesData.getId());
        assertEquals(actualData.getDueDate(), expectesData.getDueDate());
        assertEquals(actualData.getCompleted(), expectesData.getCompleted());

    }

    @Test
    public void getActivitiestest() {
        ActivitiesPojo newActivity = new ActivitiesPojo(25, "Activity 25", "2025-10-21T10:00:00.000Z", false);
        Response response = given(spec)
                .body(newActivity)
                .post("/api/v1/Activities");
        ActivitiesPojo createdActivity = response.as(ActivitiesPojo.class);
        Integer activityId = createdActivity.getId();
        Response getResponse = given(spec)
                .get("/api/v1/Activities/" + activityId);
        getResponse.prettyPrint();
        ActivitiesPojo retrievedActivity = getResponse.as(ActivitiesPojo.class);
        assertEquals(getResponse.statusCode(), 200);
        assertEquals(retrievedActivity.getId(), createdActivity.getId());
        assertEquals(retrievedActivity.getTitle(),createdActivity.getTitle());
        assertEquals(retrievedActivity.getCompleted(), createdActivity.getCompleted());

    }

    @Test
    public void putActivitiestest() {
        ActivitiesPojo expectesData = new ActivitiesPojo(12, "Swimming", "2025-10-20T19:35:52.054Z",true);
        Response response = given(spec).body(expectesData).post("/api/v1/Activities");
        ActivitiesPojo actualData = response.as(ActivitiesPojo.class);
        actualData.setTitle("Updated Swimming");
        actualData.setCompleted(false);
        Response putResponse = given(spec).body(actualData).put("/api/v1/Activities/"+actualData.getId());
        putResponse.prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getTitle(),"Updated Swimming");
        assertEquals(actualData.getCompleted(), false);

    }

    @Test
    public void deleteActivitiestest() {
        ActivitiesPojo expectesData = new ActivitiesPojo(12, "Swimming", "2025-10-20T19:35:52.054Z",true);
        Response response = given(spec).body(expectesData).post("/api/v1/Activities");
        ActivitiesPojo actualData = response.as(ActivitiesPojo.class);
        Response deleteResponse = given(spec).delete("/api/v1/Activities/"+actualData.getId());
        deleteResponse.prettyPrint();

        assertEquals(response.statusCode(), 200);


    }



    }

