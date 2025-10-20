package day02;

import base_urls.EmploysBaseUrl;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class Task03  extends EmploysBaseUrl {
/*
Example 3
Given
https://dummy.restapiexample.com/api/v1/employees
When
User sends GET request
Then
Status code is 200
And
There are 24 employees
And
"Tiger Nixon" and "Garrett Winters" are among them
And
Highest age = 66
And
Youngest = "Tatyana Fitzpatrick"
And
Total salary = 6,644,770
 */
    @Test
    public void task03() {

        spec.pathParam("first","employees");
        Response response =given(spec).get("{first}");
        response.prettyPrint();

        response.then().statusCode(200);
            JsonPath jsonPath = response.jsonPath();

        //    There are 24 employees
        int totalEmpliyees = jsonPath.getInt("data.size()");
        System.out.println("totalEmpliyees = " + totalEmpliyees);
        //assert totalEmpliyees==24
        Assert.assertEquals(totalEmpliyees,24);


        //"Tiger Nixon" and "Garrett Winters" are among them
       List<String> AllName = jsonPath.getList("data.employee_name");
       boolean isAmong = AllName.contains("Tiger Nixon") && AllName.contains("Garrett Winters");
        System.out.println("isAmong = " + isAmong);
        //assert isAmong == true
        Assert.assertTrue(isAmong);

        //    Highest age = 66
        int highestAge = jsonPath.getInt("data.max{it.employee_age}.employee_age");
        System.out.println("highestAge = " + highestAge);
        Assert.assertEquals(highestAge,66);


        //      Youngest = "Tatyana Fitzpatrick"
        String youngest = jsonPath.getString("data.min{it.employee_age}.employee_name");
        System.out.println("youngest = " + youngest);
        Assert.assertEquals(youngest,"Tatyana Fitzpatrick");


        //    Total salary = 6,644,770
        List<String> allSalaries = response.jsonPath().getList("data.findAll{it.employee_salary}.employee_salary");

        int totalSalary = 0;
        for (String salary : allSalaries) {
            totalSalary += Integer.parseInt(salary);
        }
        System.out.println("" + totalSalary);
        Assert.assertEquals(totalSalary, 6644770);











    }
}
