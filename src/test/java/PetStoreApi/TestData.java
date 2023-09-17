package PetStoreApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class TestData {

    String token = "special-key";

    RequestSpecification reqSpec;

    public static final int petId = 61;

    @BeforeClass
    public void setUp() {
        baseURI = "https://petstore.swagger.io/v2";
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURI)
                .build();
    }



    public static Map<String,Object> getCreateData() {

        Map<String, Object> petBody = new HashMap<>();
        petBody.put("id", petId);

        Map<String, Object> category = new HashMap<>();
        category.put("id", 2);
        category.put("name", "Scottish");
        petBody.put("category", category);

        petBody.put("name", "Weasley");

        String[] photoUrls = {"notImportant"};
        petBody.put("photoUrls", photoUrls);

        Map<String, Object> tag = new HashMap<>();
        tag.put("id", 3);
        tag.put("name", "Tall");
        Object[] tags = {tag};
        petBody.put("tags", tags);

        petBody.put("status", "available");

        return petBody;
    }



    public static Map<String,Object> getUpdateData() {

        Map<String, Object> petBody = new HashMap<>();
        petBody.put("id", petId);

        Map<String, Object> category = new HashMap<>();
        category.put("id", 2);
        category.put("name", "Scottish");
        petBody.put("category", category);

        petBody.put("name", "Harry");

        String[] photoUrls = {"notImportant"};
        petBody.put("photoUrls", photoUrls);

        Map<String, Object> tags = new HashMap<>();
        tags.put("id", 3);
        tags.put("name", "Short");
        Object[] tag = {tags};
        petBody.put("tags", tag);

        petBody.put("status", "pending");

        return petBody;
    }


}
