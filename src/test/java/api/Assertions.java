package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Assertions {

    public static void assertUserDetails(Response response) {
        response.then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));

        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertListUsers(Response response) {
        response.then()
                .statusCode(200);

        response.then()
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2));

        response.then()
                .body("data[0].id", equalTo(7))
                .body("data[0].email", equalTo("michael.lawson@reqres.in"))
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"))
                .body("data[0].avatar", equalTo("https://reqres.in/img/faces/7-image.jpg"));

        response.then()
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertSingleUserNotFound(Response response) {
        response.then()
                .statusCode(404);

        response.then()
                .body("id", nullValue())
                .body("email", nullValue())
                .body("first_name", nullValue())
                .body("last_name", nullValue())
                .body("avatar", nullValue());
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertListResource(Response response) {
        response.then()
                .statusCode(200);
        response.then()
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2));

        List<Map<String, ?>> resources = response.jsonPath().getList("data");

        for (int i = 0; i < resources.size(); i++) {
            Map<String, ?> resource = resources.get(i);

            response.then()
                    .body("data[" + i + "].id", equalTo(resource.get("id")))
                    .body("data[" + i + "].name", equalTo(resource.get("name")))
                    .body("data[" + i + "].year", equalTo(resource.get("year")))
                    .body("data[" + i + "].color", equalTo(resource.get("color")))
                    .body("data[" + i + "].pantone_value", equalTo(resource.get("pantone_value")));
        }

        response.then()
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertSingleResource(Response response) {
        response.then()
                .statusCode(200);

        response.then()
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        response.then()
                .body("data.id", greaterThan(0))
                .body("data.name", is(not(emptyOrNullString())));
        response.then()
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", lessThanOrEqualTo(2023));
        response.then()
                .body("data.year", either(equalTo(2001)).or(equalTo(2002)));
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertSingleResourceNotFound(Response response) {
        response.then()
                .statusCode(404);

        response.then()
                .body("id", nullValue())
                .body("name", nullValue())
                .body("year", nullValue())
                .body("color", nullValue())
                .body("pantone_value", nullValue());
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertCreateUser(Response response) {
        response.then()
                .statusCode(200 )
                .contentType(ContentType.JSON);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("name", "morpheus");
        expectedResponse.put("job", "leader");
        expectedResponse.put("id", "637");
        expectedResponse.put("createdAt", "2023-08-13T15:20:48.630Z");
        Assert.assertNotNull("Name should not be null", response.jsonPath().getString("name"));
        Assert.assertNotEquals("Name should not be undefined", "undefined", response.jsonPath().getString("name"));
        System.out.println("Expected Response: " + expectedResponse);
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertUpdateUser(Response response) {
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        Map<String, Object> UpdatedResponse = new HashMap<>();
        UpdatedResponse.put("name", "morpheus");
        UpdatedResponse.put("job", "zion resident");
        UpdatedResponse.put("updatedAt", "2023-08-13T22:29:57.782Z");
        response.then().header("Content-Type", Matchers.notNullValue());
        System.out.println("Updated Response: " + UpdatedResponse);
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }

    public static void assertDeleteUser(Response response) {
        response.then()
                .statusCode(204);

        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));
    }
}