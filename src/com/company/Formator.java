package com.company;

import com.sun.nio.file.ExtendedCopyOption;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Scanner;

public class Formator {
    private String input;
    private String format;
    Scanner in = new Scanner(System.in);

    Formator() throws Exception{
        input = in.nextLine();
        formatInput();
    }

    public void formatInput() throws Exception{
        String[] splited = input.split(" ");
        String otch = splited[2];
        format = splited[0] + " " + splited[1].charAt(0) + "." + splited[2].charAt(0) + ". ";

        try {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            df.setLenient(false);
            df.parse(splited[3]);
        }
        catch (Exception e) {
            throw e;
        }

        String[] splitedYear = splited[3].split("\\.");
        LocalDate firstDate = LocalDate.of(Integer.parseInt(splitedYear[2]), Integer.parseInt(splitedYear[1]) - 1, Integer.parseInt(splitedYear[0])); //
        LocalDate secondDate = LocalDate.of(2020, 1, 17); //
        format += ChronoUnit.YEARS.between(firstDate, secondDate);
        if (otch.charAt(otch.length() - 1) == 'а') {
            format += " Женщина";
        }
        else {
            format += " Мужчина";
        }
    }

    public String toString() {
        return format;
    }
}
