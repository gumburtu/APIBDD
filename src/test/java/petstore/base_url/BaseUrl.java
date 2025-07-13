package petstore.base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseUrl {
    public static RequestSpecification spec;

    public static void settingup() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    // Alternative method if you want to get the spec without setting it up each time
    public static RequestSpecification getSpec() {
        if (spec == null) {
            settingup();
        }
        return spec;
    }
}