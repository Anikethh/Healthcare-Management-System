package com.company;

import java.util.Scanner;

public class Hospital {

    static int uniqueID = 100000;
    private final int ID;
    private final String name;
    private final int pinCode;

    static Scanner input = new Scanner(System.in);

    public Hospital(int pinCode, String name) {
        this.ID = uniqueID;
        uniqueID++;
        this.name = name;
        this.pinCode = pinCode;
    }

    public static Hospital register(){
        System.out.print("Hospital name: ");
        String hospitalName = input.next();
        System.out.print("PinCode: ");
        int pinCode = input.nextInt();
        System.out.printf("Hospital name: %1$s, PinCode: %2$d, Unique ID: %3$d\n", hospitalName, pinCode, Hospital.uniqueID);
        Hospital newHospital = new Hospital(pinCode, hospitalName);
        return newHospital;
    }

    public int getHospitalID(){
        return ID;
    }

    public String getHospitalName(){
        return name;
    }

    public int getHospitalPinCode(){
        return pinCode;
    }

    public boolean slotAvailable(Slot slot){ return slot.slotBooked[slot.getDayNumber()]; }

}
