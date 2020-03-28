package com.company;

public class Main {

    public static void main(String[] args) {
        int[][] firstArr = {{1, 2}, {2, 3}};
        int[][] secondArr = {{2, 1, 1}, {3, 2, 2}};
        Matrix m1 = new Matrix(firstArr);
        Matrix m2 = new Matrix(secondArr);
        try {
            m1.add(m2);
            System.out.print(m1);
        } catch (Exception e) {
            System.out.println("Неверная размерность");
        }

    }
}
