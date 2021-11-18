package com.example.petstore.data;

import java.util.*;
import com.example.petstore.template.Pet;

public class PetData {
    private List<Pet> petList =new ArrayList<Pet>();

//  We'll use the following array to store our data for now
    private static PetData myPets = new PetData();

//  constructor will generate the initial data
    private PetData() {
        this.addPet(new Pet(1, 3, "Joey", "Cat"));
        this.addPet(new Pet(2, 2, "Pinky", "Dog"));
        this.addPet(new Pet(2, 2, "Rocky", "Dog"));
        this.addPet(new Pet(3, 1, "Peththappu", "Parrot"));
        this.addPet(new Pet(4, 1, "Flash", "Tortoise"));
        this.addPet(new Pet(5, 1, "Puncha", "Rabbit"));
    }

    public static PetData getPetData() {     // check thissssssssssssssssssssssssss
        return myPets;
    }

//  CREATE
    public Pet addPet(Pet newPet) {
        petList.add(newPet);
        return newPet;
    }

//  VIEW
    public List<Pet> getAllPets() {
        return petList;
    }

//  UPDATE
    public Pet updatePet(Integer pId, Pet putPet) {
        for (Pet p : petList) {
            if (p.getPetId().equals(pId)) {
                p.setPetAge(putPet.getPetAge());
                p.setPetName(putPet.getPetName());
                p.setPetType(putPet.getPetType());
                return p;
            }
        }
        return null;
    }

//  DELETE
    public boolean deletePet(Integer pId) {
        for (Pet p : petList) {
            if (p.getPetId().equals(pId)) {
                petList.remove(p);
                return true;
            }
        }
        return false;
    }

//  SEARCH BY NAME
    public List<Pet> getPetByName(String name) {
        List<Pet> searchList = new ArrayList<Pet>();
        for (Pet p : petList) {
            if (p.getPetName().equals(name)) {
                searchList.add(p);
            }
        }
        if(!searchList.isEmpty()){
            return searchList;
        }
        return null;
    }

//  SEARCH BY AGE
    public List<Pet> getPetByAge(Integer age) {
        List<Pet> searchList = new ArrayList<Pet>();
        for (Pet p : petList) {
            if (p.getPetAge().equals(age)) {
                searchList.add(p);
            }
        }
        if(!searchList.isEmpty()){
            return searchList;
        }
        return null;
    }

//  SEARCH BY TYPE
    public List<Pet> getPetByType(String type) {
        List<Pet> searchList = new ArrayList<Pet>();
        for (Pet p : petList) {
            if (p.getPetType().equals(type)) {
                searchList.add(p);
            }
        }
        if(!searchList.isEmpty()){
            return searchList;
        }
        return null;
    }



}
