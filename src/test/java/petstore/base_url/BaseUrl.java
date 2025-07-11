package petstore.base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static petstore.utilities.Authentication.generateToken;

public class BaseUrl {
    public static RequestSpecification spec;

    public static void settingup(String email, String password) {
        spec = new RequestSpecBuilder()
                .setBaseUri("")
                .setContentType(ContentType.JSON)
                .addHeader("authorization", "Bearer " + generateToken(email, password))
                .build();
    }
}