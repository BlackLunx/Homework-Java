package com.company;

import java.util.Scanner;

public class Main {

    private static void workWithDictionary() {
        Scanner in = new Scanner(System.in);
        String inputFile = in.next();
        Dictionary d = new Dictionary(inputFile);
        String outputFile = in.next();
        d.printToFile(outputFile);
    }
    private static void workWithElevators() {
        Elevators elevator = new Elevators(4);
        elevator.request(-1, 5, 2);
        elevator.request(1, 4, 8);

    }
    public static void main(String[] args) {
        workWithElevators();
    }
}
