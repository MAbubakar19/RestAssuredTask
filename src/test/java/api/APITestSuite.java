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
    public void testListUsers() {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        Response response = given()
                .when().get(Endpoints.LIST_USERS);

        Assertions.assertListUsers(response);
    }


    @Test(dataProvider = "userIds", dataProviderClass = UserIdProvider.class)
    @Parameters("userId")
    public void testDeleteUser(String userId) {
        RestAssured.baseURI = BaseUrl.REGRESS_BASE_URL;

        if (userId.equals("3")) {
            Response response = RestAssured.delete(Endpoints.GET_SINGLE_USER + "/" + userId);

            Assertions.assertDeleteUser(response);
        } else {
            // Do nothing or perform other assertions/logic as needed
        }
    }
}
