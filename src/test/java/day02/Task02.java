package day02;

import base_urls.JPHBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Task02 extends JPHBaseUrl {

//    Example 2
//    Given
//    https://jsonplaceholder.typicode.com/todos
//    When
//    I send GET request
//    Then
//1) Status code = 200
//2) Print all ids > 190 (10 total)
//            3) Print userIds with ids < 5 (4 total)
//            4) Verify title “quis eius est sint explicabo”
//            5) Find id where title = "quo adipisci enim quam ut ab"


    @Test
    public void task2() {

        spec.pathParam("first", "todos");
        Response response = given(spec).get("{first}");
      response.prettyPrint();

        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        //2) Print all ids > 190 (10 total)
        System.out.println(jsonPath.getList("findAll{it.id>190}.id"));

        //3) Print userIds with ids < 5 (4 total)
        System.out.println(jsonPath.getList("findAll{it.id<5}.userId"));

        //4) Verify title “quis eius est sint explicabo”
        boolean actualTitle = jsonPath.getBoolean("any{it.title.contains('quis eius est sint explicabo')}");
        System.out.println("actualTitle = " + actualTitle);

        //5) Find id where title = "quo adipisci enim quam ut ab"
      int id = jsonPath.getInt("find{it.title=='quo adipisci enim quam ut ab'}.id");
        System.out.println("id = " + id);


    }
}
