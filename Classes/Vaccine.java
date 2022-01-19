package com.company;

import java.util.Scanner;

public class Vaccine {

    public String type;
    private final int numberOfDoses;
    private int gapBetweenDoses;

    static Scanner input = new Scanner(System.in);

    public Vaccine(String type, int numberOfDoses){
        this.type = type;
        this.numberOfDoses = numberOfDoses;
//        this.gapBetweenDoses = gapBetweenDoses;
    }

    public static Vaccine addVaccine(){
        System.out.print("Vaccine name: ");
        String vaccineName = input.next();
        System.out.print("Number of doses: ");
        int numberOfDoses = input.nextInt();
        int gap = 0;
        if(numberOfDoses == 1){
            System.out.printf("Vaccine name: %1$s, Number of doses: %2$d, Gap between doses: 0\n", vaccineName, numberOfDoses);
        }
        else {
            System.out.print("Gap between doses: ");
            gap = input.nextInt();
            System.out.printf("Vaccine name: %1$s, Number of doses: %2$d, Gap between doses: %3$d\n", vaccineName, numberOfDoses, gap);
            }
        Vaccine newVaccine = new Vaccine(vaccineName, numberOfDoses);
        newVaccine.setGapBetweenDoses(gap);
        return newVaccine;
    }

    public int getNumberOfDoses() { return numberOfDoses; }
    public int getGapBetweenDoses() { return gapBetweenDoses; }
    public void setGapBetweenDoses(int n) { gapBetweenDoses = n; }
}
