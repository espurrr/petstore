package com.example.petstore.template;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "Pet")
public class Pet {

	@Schema(required = true, description = "Pet id")
	@JsonProperty("pet_id")
	private Integer petId;

	@Schema(required = true, description = "Pet type")
	@JsonProperty("pet_type")
	private String petType;

	@Schema(required = true, description = "Pet name")
	@JsonProperty("pet_name")
	private String petName;

    @Schema(required = true, description = "Pet age")
    @JsonProperty("pet_age")
    private Integer petAge;

    public Pet() { }

	public Pet(int petId, int petAge, String petName, String petType) {
		this.petId = petId;
		this.petAge = petAge;
		this.petName = petName;
		this.petType = petType;
	}

	public Integer getPetId() {
		return petId;
	}
	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getPetType() {
		return petType;
	}
	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Integer getPetAge() {
		return petAge;
	}
	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}

}
