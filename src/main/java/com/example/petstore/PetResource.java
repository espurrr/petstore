package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.data.PetData;
import com.example.petstore.template.Pet;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	private PetData petList = PetData.getPetData();

//	GET ALL PETS
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> pets = petList.getAllPets();
		return Response.ok(pets).build();
	}

//	CREATE NEW PET
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Failed to add the pet. Please try again later..") })
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPet(Pet newPet) {
		Pet newestPet = petList.addPet(newPet);
		return Response.ok(newestPet).build();
	}

//	UPDATE BY PET ID
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Failed to update the pet. Please try again later..") })
	@PUT
	@Path("update/{pId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePet(@PathParam("pId") Integer pId, Pet putPet) {
		Pet editedPet = petList.updatePet(pId, putPet);
		return Response.ok(editedPet).build();
	}

//	DELETE BY PET TYPE ID
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Failed to delete the pet. Please try again later..") })
	@DELETE
	@Path("delete/{pId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePet(@PathParam("pId") Integer pId) {
		boolean result = petList.deletePet(pId);
		if (result) return Response.ok(true).build();
		else return Response.status(Status.NOT_FOUND).build();
	}

//	SEARCH BY ID
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet found successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Failed to find any pet by the id.") })
	@GET
	@Path("{pId}")
	public Response getPetById(@PathParam("pId") Integer pId) {
		List<Pet> pets = petList.getPetById(pId);
		if(pets == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pets).build();
	}

//	SEARCH BY PET NAME
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet found successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Failed to find any pet by the name.") })
	@GET
	@Path("name/{pName}")
	public Response getPetByName(@PathParam("pName") String pName) {
		List<Pet> pets = petList.getPetByName(pName);
		if(pets == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pets).build();
	}


//	SEARCH BY AGE
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pets found successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Failed to find any pet by the age.") })
	@GET
	@Path("age/{pAge}")
	public Response getPetByName(@PathParam("pAge") Integer pAge) {
		List<Pet> pets = petList.getPetByAge(pAge);
		if(pets == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pets).build();
	}

//  SEARCH BY PET TYPE
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pets found successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Failed to find any pet by the type.") })
	@GET
	@Path("type/{pType}")
	public Response getPetByType(@PathParam("pType") String pType) {
		List<Pet> pets = petList.getPetByType(pType);
		if(pets == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pets).build();
	}


}
