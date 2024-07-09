import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TC01_GET {
    @Test
    public void test() {
        Response response= RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
    }
}
