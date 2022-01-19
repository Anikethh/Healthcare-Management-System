package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Citizen {
    private final String name;
    private final long ID;
    private final int age;
    private int numberOfDoses = 0;
    private int requiredDoses;
    private String status;
    private String vaccineName;
    private int dueDate;

    static Scanner input = new Scanner(System.in);

    public Citizen(String name, int age, long ID){
        status = "REGISTERED";
        this.name = name;
        this.age = age;
        this.ID = ID;
    }

    public static void register(ArrayList<Citizen> citizens){
        System.out.print("Citizen name: ");
        String citizenName = input.next();
        System.out.print("Age: ");
        int age = input.nextInt();
        System.out.print("Unique ID: ");
        long citizenID = input.nextLong();

        String citID = Long.toString(citizenID);
        if(citID.length() == 12) {

            boolean duplicate = false;
            for (Citizen oldCitizen : citizens) {
                if (oldCitizen.getID() == citizenID) {
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) {
                Citizen newCitizen = new Citizen(citizenName, age, citizenID);
                newCitizen.dueDate = 0;
                citizens.add(newCitizen);
                System.out.printf("Citizen name: %1$s, Age: %2$d, Unique ID: %3$d\n", citizenName, age, citizenID);
                if (age < 18) {
                    System.out.print("Only above 18 are allowed!\n");
                }
            } else {
                System.out.print("Duplicate IDs are not allowed!\n");
            }
        }
        else{
            System.out.println("Citizen ID should be a 12 digit number!");
        }
    }

    public String getName(){
        return name;
    }

    public long getID(){
        return ID;
    }

    public int getNumberOfDoses(){
        return numberOfDoses;
    }

    public String getStatus(){
        return status;
    }

    public void setNumberOfDoses(){
        numberOfDoses++;
    }

    public int getRequiredDoses(){
        return requiredDoses;
    }

    public String getVaccineName(){
        return vaccineName;
    }

    public int getDueDate(){ return dueDate; }

    public void setRequiredDoses(int n){ requiredDoses = n; }

    public void setStatus(String s){
        status = s;
    }

    public void setDueDate(Slot slot) { dueDate = slot.getDayNumber(); }

    public void setVaccineName(String s){
        vaccineName = s;
    }

    public void setDueDate(Vaccine vaccine){ dueDate += vaccine.getGapBetweenDoses(); }

    public static void checkStatus(ArrayList<Citizen> citizens){
        System.out.print("Enter Patient ID: ");
        long enteredID = input.nextLong();
        for(Citizen allCitizens : citizens){
            if (allCitizens.getID() == enteredID){
                if(allCitizens.getStatus().equals("REGISTERED")){
                    System.out.printf("Citizen %s\n", allCitizens.getStatus());
                }
                else if(allCitizens.getStatus().equals("FULLY VACCINATED")){
                    System.out.printf("%s", allCitizens.getStatus());
                    System.out.printf("\nVaccine given: %s", allCitizens.getVaccineName());
                    System.out.printf("\nNumber of doses: %s\n", allCitizens.numberOfDoses);
                }
                else {
                    System.out.printf("%s", allCitizens.status);
                    System.out.printf("\nVaccine given: %s", allCitizens.getVaccineName());
                    System.out.printf("\nNumber of doses: %s", allCitizens.numberOfDoses);
                    System.out.printf("\nNext Dose due date: %s\n", allCitizens.getDueDate());
                }
            }
        }
    }
}
