package api;

import io.restassured.RestAssured;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class APITestSuite {

    @Test
    public void testGetSingleUser() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.GET_SINGLE_USER);

        Assertions.assertUserDetails(response);
    }

    @Test
    public void testGetListUsers() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.LIST_USERS);

        Assertions.assertListUsers(response);
    }

    @Test
    public void testSingleUserNotFound() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.SINGLE_USER_NOT_FOUND);

        Assertions.assertSingleUserNotFound(response);
        System.out.println(response);
    }

    @Test
    public void testListResource() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.LIST_RESOURCE);

        Assertions.assertListResource(response);
        System.out.println(response);
    }

    @Test
    public void testSingleResource() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.SINGLE_RESOURCE);

        Assertions.assertSingleResource(response);
        System.out.println(response);
    }

    @Test
    public void testSingleResourceNotFound() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.SINGLE_RESOURCE_NOT_FOUND);

        Assertions.assertSingleResourceNotFound(response);
        System.out.println(response);
    }

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.CREATE_USER);

        Assertions.assertCreateUser(response);
        System.out.println(response);
    }

    @Test
    public void testUpdateUser() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.UPDATE_USER);

        Assertions.assertUpdateUser(response);
        System.out.println(response);
    }

    @Test(dataProvider = "userIds", dataProviderClass = UserIdProvider.class)
    @Parameters("userId")
    public void testDeleteUser(String userId) {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        if (userId.equals("3")) {
            Response response = RestAssured.delete(Endpoints.GET_SINGLE_USER + "/" + userId);
//            Assertions.assertDeleteUser(response);
        } else {
            System.out.println("User with userId " + userId + " is not eligible for deletion.");
        }
    }
}