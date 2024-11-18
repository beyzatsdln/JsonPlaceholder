import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Test;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


public class RestTest {
    String url = "https://jsonplaceholder.typicode.com/posts";
    String contentType = ContentType.JSON.toString();

    @Test

    public void StatusCodeTest() {

        Response response = RestAssured.given()
                .contentType(contentType)
                .when().log().body()
                .get(url);

        response.then().log().all()
                .statusCode(200);

        Assert.assertEquals(response.getStatusCode(),200);

    }
     @Test
     public void JsonTest() {
            Response response = RestAssured.given()
                    .contentType(contentType)
                    .when().log().body()
                    .get(url);
            response.then().log().all()
                    .contentType(ContentType.JSON)
                    .body("userId", everyItem(notNullValue()))
                    .body("id", everyItem(notNullValue()))
                    .body("title", everyItem(notNullValue()))
                    .body("body", everyItem(notNullValue()));
        }
     @Test
     public void  IDTest(){
         Response response = RestAssured.given()
                 .contentType(contentType)
                 .when().log().body()
                 .get(url);
         response.then().log().all()
                .body("find { it.id == 1 }.title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));

    }

    @Test
     public void ArrayTest(){
        Response response = RestAssured.given()
                .contentType(contentType)
                .when().log().body()
                .get(url);
        response.then().log().all()
                .body("size()", greaterThanOrEqualTo(10));

}

    @Test
    public void UserIdTest(){
        Response response = RestAssured.given()
                .contentType(contentType)
                .when().log().body()
                .get(url);
        response.then().log().all()
                .body("userId", everyItem(greaterThan(0)));
    }
}
