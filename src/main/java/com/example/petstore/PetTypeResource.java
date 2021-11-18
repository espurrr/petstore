package com.example.petstore;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.data.PetTypeData;
import com.example.petstore.template.PetType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/petTypes")
@Produces("application/json")
public class PetTypeResource {

    private PetTypeData petTypeList = PetTypeData.getPetTypeData();

//  GET ALL PET TYPES
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @GET
    public Response getPetTypes() {
        List<PetType> petTypes = petTypeList.getAllPetTypes();
        return Response.ok(petTypes).build();
    }

//  CREATE NEW PET TYPE
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Failed to add the pet type. Please try again later..") })
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPetType(PetType newPetType) {
        PetType newestPetType = petTypeList.addPetType(newPetType);
        return Response.ok(newestPetType).build();
    }

//  UPDATE BY PET TYPE ID
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Failed to update the pet type. Please try again later..") })
    @PUT
    @Path("update/{ptId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePetType(@PathParam("ptId") Integer ptId, PetType putPetType) {
        PetType editedPetType = petTypeList.updatePetType(ptId, putPetType);
        return Response.ok(editedPetType).build();
    }

//  DELETE BY PET TYPE ID
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Failed to delete the pet type. Please try again later..") })
    @DELETE
    @Path("delete/{ptId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePetType(@PathParam("ptId") Integer ptId) {
        boolean result = petTypeList.deletePetType(ptId);
        return Response.ok(result).build();
    }
}
