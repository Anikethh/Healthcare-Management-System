package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Slot {

    private final int hospitalID;
    private final int dayNumber;
    private int quantity;
    private final String type;
    public static int slotCount = 0;
    private final int indieSlotCount;
    private int[] maximumSlots = new int[100];
    public boolean[] slotBooked = new boolean[100];

    static Scanner input = new Scanner(System.in);

    public Slot(int hospitalID, int dayNumber, int quantity, String type){
        indieSlotCount = slotCount++;
        this.hospitalID = hospitalID;
        this.dayNumber = dayNumber;
        this.quantity = quantity;
        this.type = type;
    }

    public static void addSlots(ArrayList<Slot> slots, ArrayList<Vaccine> vaccines){
        System.out.print("Enter Hospital ID: ");
        int hospitalID = input.nextInt();
        System.out.print("Enter number of slots to be added: ");
        int numberOfSlots = input.nextInt();
        while (numberOfSlots > 0) {
            System.out.print("Enter Day Number: ");
            int dayNumber = input.nextInt();
            System.out.print("Enter Quantity: ");
            int quantity = input.nextInt();
            System.out.print("Select Vaccine\n");
            for(int i = 0; i < vaccines.size() ; i++){
                System.out.printf("%1$d. %2$s\n", i, vaccines.get(i).type);
            }
            int vaccine = input.nextInt();

            for(int i = 0; i < vaccines.size() ; i++) {
                if (vaccine == i) {
                    System.out.printf("Slot added by Hospital %1$d for Day: %2$d dayNumber, Available quantity: %3$d of Vaccine %4$s\n", hospitalID, dayNumber, quantity, vaccines.get(i).type);
                    Slot newSlot = new Slot(hospitalID, dayNumber, quantity, vaccines.get(i).type);
                    newSlot.maximumSlots[dayNumber] += quantity;
//                    newSlot.indieSlotCount = slotCount++;
                    slots.add(newSlot);
                    break;
                }
            }
            numberOfSlots--;
        }
    }

    public static void listSlots(ArrayList<Hospital> hospitals, ArrayList<Slot> slots, ArrayList<Vaccine> vaccines) {
        System.out.print("Enter Hospital ID: ");
        int hospitalID = input.nextInt();
        for (Slot hospitalSlots : slots) {
            if (hospitalSlots.getHospitalID() == hospitalID) {
                System.out.printf("Day: %1$d Vaccine: %2$s Available Qty: %3$d\n", hospitalSlots.dayNumber, hospitalSlots.type, hospitalSlots.quantity);
            }
        }
    }

    public static void bookSlots(ArrayList<Slot> slots, ArrayList<Hospital> hospitals, ArrayList<Citizen> citizens, ArrayList<Vaccine> vaccines) {
        System.out.print("Enter patient Unique ID: ");
        long ID = input.nextLong();
        System.out.println("""
                1. Search by area
                2. Search by Vaccine
                3. Exit""");
        System.out.print("Enter option: ");
        int option = input.nextInt();

        if (option == 1) {
            System.out.print("Enter PinCode: ");
            int pinCode = input.nextInt();
            for (Hospital allHospitals : hospitals) {
                if (allHospitals.getHospitalPinCode() == pinCode) {
                    System.out.printf("%1$d %2$s", allHospitals.getHospitalID(), allHospitals.getHospitalName());
                }
            }
            boolean printFlag = false;
            System.out.print("\nEnter Hospital ID: ");
            int pc = input.nextInt();
            for (Slot allSlots : slots) {
                if (allSlots.hospitalID == pc) {
                    if (allSlots.maximumSlots[allSlots.dayNumber] > 0) {
//                        if (!allSlots.slotBooked[allSlots.dayNumber]){
                            printFlag = true;
                            System.out.printf("%1$d -> Day: %2$d Available Qty: %3$d Vaccine: %4$s\n", allSlots.indieSlotCount, allSlots.dayNumber, allSlots.quantity, allSlots.type);
                        }
//                    }
                }
            }
            boolean flag3 = false;
            if (printFlag) {
                System.out.print("Choose slot: ");
                int chosenSlot = input.nextInt();
                for (Slot allSlots : slots) {
                    if (allSlots.hospitalID == pc) {
                        if (allSlots.indieSlotCount == chosenSlot) {
                            for (Citizen allCitizens : citizens) {
                                if (allCitizens.getID() == ID) {
                                    for (Vaccine allVaccines : vaccines) {
                                        if (allVaccines.type.equals(allSlots.type)) {
                                            if (allCitizens.getVaccineName() == null || allCitizens.getVaccineName().equals(allVaccines.type)) {
                                                flag3 = true;
                                                if (allCitizens.getDueDate() == 0) {
                                                    allCitizens.setDueDate(allSlots);
                                                    allCitizens.setDueDate(allVaccines);
                                                } else {
                                                    allCitizens.setDueDate(allVaccines);
                                                }
                                                allCitizens.setRequiredDoses(allVaccines.getNumberOfDoses());
                                                allCitizens.setNumberOfDoses();
                                                allSlots.slotBooked[allSlots.dayNumber] = true;
                                                allSlots.setQuantity(allSlots.getQuantity() - 1);
                                                System.out.printf("%1$s vaccinated with %2$s\n", allCitizens.getName(), allSlots.type);
                                                allCitizens.setVaccineName(allSlots.type);
                                                if (allCitizens.getRequiredDoses() - allCitizens.getNumberOfDoses() > 0) {
                                                    allCitizens.setStatus("PARTIALLY VACCINATED");
                                                } else {
                                                    allCitizens.setStatus("FULLY VACCINATED");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!flag3) {
                    System.out.print("Vaccines cannot mixed!\n");
                }
            }
            else {
                System.out.println("Sorry, no available slots!");
            }
        } else if (option == 2) {
            System.out.print("Enter Vaccine Name: ");
            String vaccine = input.next();
            boolean printFlag = false;
            for (Hospital allHospitals : hospitals) {
                for (Slot allSlots : slots) {
                    if (allSlots.type.equals(vaccine)) {
                        if (allSlots.maximumSlots[allSlots.dayNumber] > 0) {
                            if (allHospitals.getHospitalID() == allSlots.getHospitalID()) {
                                printFlag = true;
                                System.out.printf("%1$d %2$s \n", allHospitals.getHospitalID(), allHospitals.getHospitalName());
                            }
                        }
                    }
                }
            }
            boolean flag2 = false;
            if (printFlag) {
                System.out.print("Enter Hospital ID: ");
                int chosenID = input.nextInt();
                for (Hospital allHospitals : hospitals) {
                    if (allHospitals.getHospitalID() == chosenID) {
                        for (Slot allSlots : slots) {
                            if (allSlots.getHospitalID() == chosenID && allSlots.getType().equals(vaccine)) {
                                if (!allSlots.slotBooked[allSlots.dayNumber]) {
                                    flag2 = true;
                                    System.out.printf("%1$d -> Day: %2$d Available Qty: %3$d Vaccine: %4$s", allSlots.indieSlotCount, allSlots.dayNumber, allSlots.quantity, allSlots.type);
                                }
                            }
                        }
                        if(flag2) {
                            System.out.print("\nChoose slot: ");
                            int inp = input.nextInt();
                            for (Slot allSlots : slots) {
                                if (allSlots.getType().equals(vaccine) && allSlots.indieSlotCount == inp) {
                                    for (Citizen allCitizens : citizens) {
                                        if (allCitizens.getID() == ID) {
//                                            if(allCitizens.getVaccineName().equals(vaccine)) {
                                                for (Vaccine allVaccines : vaccines) {
                                                    if (allVaccines.type.equals(vaccine)) {
                                                        allCitizens.setRequiredDoses(allVaccines.getNumberOfDoses());
                                                        allCitizens.setNumberOfDoses();
                                                        allCitizens.setDueDate(allVaccines);
                                                        allSlots.setQuantity(allSlots.getQuantity() - 1);
                                                        System.out.printf("%1$s vaccinated with %2$s\n", allCitizens.getName(), allSlots.type);
                                                        allCitizens.setVaccineName(allSlots.type);
                                                        if (allCitizens.getRequiredDoses() - allCitizens.getNumberOfDoses() > 0) {
                                                            allCitizens.setStatus("PARTIALLY VACCINATED");
                                                        } else {
                                                            allCitizens.setStatus("FULLY VACCINATED");
                                                        }
                                                    }
                                                }
//                                            }
//                                            else{
//                                                System.out.print("\nVaccines cannot mixed!");
//                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("Sorry, no available slots!");
                        }
                    }

                }
            }
        } else {
            System.out.println("Sorry, no available slots!");
        }
    }

    public int getHospitalID(){
        return hospitalID;
    }
    public int getDayNumber(){
        return dayNumber;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getType(){
        return type;
    }
    public int getIndieSlotCount(){
        return indieSlotCount;
    }
    public int getMaximumSlots(int day){
        return maximumSlots[day];
    }

    public void setQuantity(int n){ quantity = n;}
}
