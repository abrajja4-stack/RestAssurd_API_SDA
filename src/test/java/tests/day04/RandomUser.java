package tests.day04;

import base_urls.PetBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RandomUser{
    /*
     Task 1: Random User API - GET Request with POJO Deserialization
    Objective: Write an automation test that validates user data from a random user API endpoint.
    Requirements:
    Send a GET request to https://randomuser.me/api
    The response will contain random user information in nested JSON structure
    Deserialize the response into a POJO class
    Assert that the following fields are NOT null:
    Email
    Username
    Password
    Medium picture URL
     */


