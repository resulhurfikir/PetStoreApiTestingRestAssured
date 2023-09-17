package PetStoreApi;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class Tests extends TestData {

    @Test(priority = 1)
    public void addANewPetToTheStore() {

        given()
                .spec(reqSpec)
                .body(TestData.getCreateData())
                //.log().body()
                //.log().uri()

                .when()
                .post("/pet")

                .then()
                //.log().body()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test(priority = 2)
    public void findsPetsByStatus() {


        given()
                .spec(reqSpec)
                .params("status", "available")

                .when()
                .get("/pet/findByStatus")

                .then()
                //.log().body()
                .statusCode(200)
                .body("status[0]", containsString("available"))
                .contentType(ContentType.JSON);
    }

    @Test(priority = 3)
    public void findsPetsByStatusNegative() {

        given()
                .spec(reqSpec)
                .params("status", "uygun")

                .when()
                .get("/pet/findByStatus")

                .then()
                //.log().body()
                .body("status", not(containsString("uygun")))
                //.statusCode(200) There might be a bug!
                .contentType(ContentType.JSON);
    }

    @Test(priority = 4, dependsOnMethods = {"addANewPetToTheStore"})
    public void findsPetsById() {

        given()
                .spec(reqSpec)
                .pathParam("petId", petId)

                .when()
                .get("/pet/{petId}")

                .then()
                //.log().body()
                .statusCode(200)
                .body("id",equalTo(petId))
                .contentType(ContentType.JSON);
    }

    @Test(priority = 5)
    public void findsPetsByIdNegative() {

        given()
                .spec(reqSpec)
                .pathParam("petId", 1546554)

                .when()
                .get("/pet/{petId}")

                .then()
                //.log().body()
                .statusCode(404)
                .body("id",not(equalTo(1546554)))
                .contentType(ContentType.JSON);
    }


    @Test(priority = 6, dependsOnMethods = {"addANewPetToTheStore"})
    public void updateAnExistingPet() {

        given()
                .spec(reqSpec)
                .body(TestData.getUpdateData())

                .when()
                .put("/pet")

                .then()
                //.log().body()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }


    @Test(priority = 7, dependsOnMethods = {"addANewPetToTheStore"})
    public void deletesAPet() {
        given()
                .spec(reqSpec)
                .header("authorizationToken", token)

                .when()
                .delete("/pet/" + petId)

                .then()
                .statusCode(200);
    }


    @Test(priority = 8, dependsOnMethods = {"deletesAPet"})
    public void deletesAPetNegative() {
        given()
                .spec(reqSpec)
                .header("authorizationToken", token)

                .when()
                .delete("/pet/" + petId)

                .then()
                .statusCode(404);
    }


}

