package com.company;
import java.util.Scanner;
import java.util.*;

public class Main {

    private static final ArrayList<Hospital> hospitals = new ArrayList<>();
    private static final ArrayList<Slot> slots = new ArrayList<>();
    private static final ArrayList<Vaccine> vaccines = new ArrayList<>();
    private static final ArrayList<Citizen> citizens = new ArrayList<>();

    public static void main(String[] args) {

        boolean flag = false;

        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------\n" + "CoWin Portal initialized....");

        while (!flag) {
            printMenuOptions();
            int query = input.nextInt();
            switch (query) {
                case 1 -> vaccines.add(Vaccine.addVaccine());
                case 2 -> hospitals.add(Hospital.register());
                case 3 -> Citizen.register(citizens);
                case 4 -> Slot.addSlots(slots, vaccines);
                case 5 -> Slot.bookSlots(slots, hospitals, citizens, vaccines);
                case 6 -> Slot.listSlots(hospitals, slots, vaccines);
                case 7 -> Citizen.checkStatus(citizens);
                case 8 -> {
                    flag = true;
                    System.out.println("Exited successfully!");
                }
                default -> System.out.println("\nInvalid Input, try again!\n");
            }
        }
    }

    private static void printMenuOptions() {
        System.out.println("""
                ---------------------------------
                1. Add Vaccine
                2. Register Hospital
                3. Register Citizen
                4. Add Slot for Vaccination
                5. Book Slot for Vaccination
                6. List all slots for a hospital
                7. Check Vaccination Status
                8. Exit
                ---------------------------------""");
    }
}
