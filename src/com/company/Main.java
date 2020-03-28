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

    public static void main(String[] args) {
        workWithDictionary();
    }
}
