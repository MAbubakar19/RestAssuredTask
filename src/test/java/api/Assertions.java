package api;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class Assertions {

    public static void assertUserDetails(Response response) {
        response.then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    public static void assertListUsers(Response response) {
        response.then()
                .statusCode(200)
                .body("data", not(empty()));
    }

    public static void assertDeleteUser(Response response) {
        response.then()
                .statusCode(204);
    }
    // Add more assertion methods as needed
}
