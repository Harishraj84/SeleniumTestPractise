package StepDefinitions;

import POJO.MappingThePojo;
import POJO.SamplePojoClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;


public class APIStepDefinitions {

    private RequestSpecification requestSpecification(String URL) {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }

    @Given("User sends a GET request to {string}")
    public void user_sends_a_get_request_to(String url) {
        // Implement the logic to send a GET request to the specified URL
        Response response = RestAssured.given()
                .spec(requestSpecification(url))
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        // print the response
        System.out.println(response.getBody().asString());

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        MappingThePojo mappingThePojo = new MappingThePojo();
        try {
            mappingThePojo.mapThePojo(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("user sends put request that exists")
    public void user_sends_put_request_that_exists() {
        Response response = RestAssured.given()
                .spec(requestSpecification("https://jsonplaceholder.typicode.com"))
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"title\": \"foo\",\n" +
                        "  \"body\": \"bar\",\n" +
                        "  \"userId\": 1\n" +
                        "}")
                .when()
                .put("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getBody().asString());
    }

    @And("user sends put request that does not exists")
    public void user_sends_put_request_that_does_not_exists() {
        Response response = RestAssured.given()
                .spec(requestSpecification("https://jsonplaceholder.typicode.com"))
                .body("{\n" +
                        "  \"id\": 101,\n" +
                        "  \"title\": \"foo\",\n" +
                        "  \"body\": \"bar\",\n" +
                        "  \"userId\": 101\n" +
                        "}")
                .when()
                .put("/posts/101")
                .then()
                .extract().response();
        System.out.println(response.getBody().asString());

    }

    @And("user sends delete request that exists")
    public void user_sends_delete_request_that_exists() {
        Response response = RestAssured.given()
                .spec(requestSpecification("https://jsonplaceholder.typicode.com"))
                .when()
                .delete("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
    }

    @And("user sends delete request that does not exists")
    public void user_sends_delete_request_that_does_not_exists() {
        Response response = RestAssured.given()
                .spec(requestSpecification("https://jsonplaceholder.typicode.com"))
                .when()
                .delete("/posts/1001")
                .then()
                .extract().response();
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println(response.getStatusCode());
    }

    @And("user do patch request that exists")
    public void user_do_patch_request_that_exists() {
        Response response = RestAssured.given()
                .spec(requestSpecification("https://jsonplaceholder.typicode.com"))
                .body("{\n" +
                        "  \"title\": \"foo updated\"\n" +
                        "}")
                .when()
                .patch("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getBody().asString());
    }

    @And("user do patch request that does not exists")
    public void user_do_patch_request_that_does_not_exists() {
        Response response = RestAssured.given()
                .spec(requestSpecification("https://jsonplaceholder.typicode.com"))
                .body("{\n" +
                        "  \"title\": \"foo updated\"\n" +
                        "}")
                .when()
                .patch("/posts/1001")
                .then()
                .extract().response();
        System.out.println("patch does not exist"+response.getBody().asString());
    }

    // all types of Authorization steps can be added here
    @And("user sends get request with Bearer Token to {string}")
    public void user_sends_get_request_with_bearer_token_to(String url) {
        Response response = RestAssured.given()
                .spec(requestSpecification(url))
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @And("user sends get request with Basic Auth to {string}")
    public void user_sends_get_request_with_basic_auth_to(String url) {
        Response response = RestAssured.given()
                .spec(requestSpecification(url))
                .auth().preemptive().basic("username", "password")
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @And("user sends get request with API Key to {string}")
    public void user_sends_get_request_with_api_key_to(String url) {
        Response response = RestAssured.given()
                .spec(requestSpecification(url))
                .header("x-api-key", "your_api_key_here")
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @And("user sends get request with OAuth2 to {string}")
    public void user_sends_get_request_with_oauth2_to(String url) {
        Response response = RestAssured.given()
                .spec(requestSpecification(url))
                .auth().oauth2("your_oauth2_token_here")
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @And("user sends get request with Digest Auth to {string}")
    public void user_sends_get_request_with_digest_auth_to(String url) {
        Response response = RestAssured.given()
                .spec(requestSpecification(url))
                .auth().digest("username", "password")
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @And("check on the rate limit from the response")
    public void check_on_the_rate_limit_from_the_response() {
        Response response = RestAssured.given()
                .spec(requestSpecification("https://jsonplaceholder.typicode.com"))
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        String rateLimit = response.getHeader("X-Rate-Limit-Limit");
        String rateRemaining = response.getHeader("X-Rate-Limit-Remaining");
        String rateReset = response.getHeader("X-Rate-Limit-Reset");

        System.out.println("Rate Limit: " + rateLimit);
        System.out.println("Rate Remaining: " + rateRemaining);
        System.out.println("Rate Reset: " + rateReset);



    }

    @And("upload a file via API to {string}")
    public void upload_a_file_via_api_to(String url) {
        Response response = RestAssured.given()
                .spec(requestSpecification(url))
                .multiPart("file", new java.io.File("src/test/resources/sample.txt"))
                .when()
                .post("/upload")
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
