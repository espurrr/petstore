package org.acme;

import com.example.petstore.template.PetType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class PetTypeResourceTest {

    @Test
    @Order(1)
    public void testPetTypeEndpoint() {
        given()
          .when().get("/v1/petTypes/")
          .then()
                .statusCode(200)
                .body("petTypeId", notNullValue())
                .body("petTypeName", notNullValue());
    }

    @Test
    @Order(2)
    public void testAddPetTypeEndpoint() {
        given().contentType(ContentType.JSON)
                .body(new PetType(2,  "Butterfly"))
                .when().post("/v1/petTypes/add")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void testUpdatePetTypeEndpoint() {
        Integer pId = 3;
        given().contentType(ContentType.JSON)
                .body(new PetType(pId, "Guinea Pig"))
                .pathParam("pId", pId)
                .when().put("/v1/petTypes/update/{pId}")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void testDeletePetTypeEndpoint() {
        Integer pId = 4;
        given() .contentType(ContentType.JSON)
                .pathParam("pId", pId)
                .when().delete("/v1/petTypes/delete/{pId}")
                .then()
                .statusCode(200);
    }


}