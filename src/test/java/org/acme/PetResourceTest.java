package org.acme;

import com.example.petstore.template.Pet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class PetResourceTest {

    @Test
    @Order(1)
    public void testPetEndpoint() {
        given()
          .when().get("/v1/pets/")
          .then()
                .statusCode(200)
                .body("petId", notNullValue())
                .body("petAge", notNullValue())
                .body("petName", notNullValue())
                .body("petType", notNullValue());
    }

    @Test
    @Order(2)
    public void testExactPetEndpoint() {
        Integer pId = 1;
        given().pathParam("pid", pId)
                .when().get("/v1/pets/{pid}")
                .then()
                    .statusCode(200)
                    .body("petId", notNullValue())
                    .body("petAge", notNullValue())
                    .body("petName", notNullValue())
                    .body("petType", notNullValue());
    }

    @Test
    @Order(3)
    public void testNegIdPetEndpoint() {
        Integer pId = -1;
        given().pathParam("pid", pId)
                .when().get("/v1/pets/{pid}")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(4)
    public void testJoeyEndpoint() {
        String pName = "Joey";
        given().pathParam("pName", pName)
                .when().get("/v1/pets/name/{pName}")
                .then()
                .statusCode(200)
                .body("petId", notNullValue())
                .body("petAge", notNullValue())
                .body("petName", equalTo(Arrays.asList("Joey")))
                .body("petType", notNullValue());
    }

    @Test
    @Order(5)
    public void testAddPetEndpoint() {
        given().contentType(ContentType.JSON)
                .body(new Pet(7, 45, "Flash", "Tortoise"))
                .when().post("/v1/pets/add")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(6)
    public void testUpdatePetEndpoint() {
        Integer pId = 1;
        given().contentType(ContentType.JSON)
                .body(new Pet(pId, 3, "Momo", "Cat"))
                .pathParam("pId", pId)
                .when().put("/v1/pets/update/{pId}")
                .then()
                .statusCode(200);
    }


    @Test
    @Order(7)
    public void testDeletePetEndpoint() {
        Integer pId = 3;
        given() .contentType(ContentType.JSON)
                .pathParam("pId", pId)
                .when().delete("/v1/pets/delete/{pId}")
                .then()
                .assertThat()
                .statusCode(200);
    }



}