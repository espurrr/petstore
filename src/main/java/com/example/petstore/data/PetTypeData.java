package com.example.petstore.data;

import java.util.*;
import com.example.petstore.template.PetType;

public class PetTypeData {
    private List<PetType> petTypeList =new ArrayList<PetType>();

//  We'll use the following array to store our data for now
    private static PetTypeData myPetTypes = new PetTypeData();

//  constructor will generate the initial data
    private PetTypeData() {
        this.addPetType(new PetType(1, "Cat"));
        this.addPetType(new PetType(2, "Dog"));
        this.addPetType(new PetType(3, "Parrot"));
        this.addPetType(new PetType(4, "Tortoise"));
        this.addPetType(new PetType(5, "Rabbit"));
    }

    public static PetTypeData getPetTypeData() {     // check thissssssssssssssssssssssssss
        return myPetTypes;
    }

//  CREATE
    public PetType addPetType(PetType newType) {
        petTypeList.add(newType);
        return newType;
    }

//  VIEW
    public List<PetType> getAllPetTypes() {
        return petTypeList;
    }

//  UPDATE
    public PetType updatePetType(Integer ptId, PetType putPetType) {
        for (PetType pt : petTypeList) {
            if (pt.getPetTypeId().equals(ptId)) {
                pt.setPetTypeName(putPetType.getPetTypeName());
                return pt;
            }
        }
        return null;
    }

//  DELETE
    public boolean deletePetType(Integer ptId) {
        for (PetType pt : petTypeList) {
            if (pt.getPetTypeId().equals(ptId)) {
                petTypeList.remove(pt);
                return true;
            }
        }
        return false;
    }




}
