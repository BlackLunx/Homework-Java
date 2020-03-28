package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Dictionary {
    private HashMap<Character, Integer> counter;
    public Dictionary(String inputFile) {
        try(FileReader reader = new FileReader(inputFile))
        {
            counter = new HashMap<>();
            int c;
            while((c = reader.read())!=-1){
                char current = (char)c;
                if(current <= 'Z' && current >= 'A' || current <= 'z' && current >= 'a') {
                    if(!counter.containsKey(current)) {
                        counter.put(current, 1);
                    }
                    else{
                        counter.put(current, counter.get(current) + 1);
                    }
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void printToFile(String outputFile) {
        try(FileWriter writer = new FileWriter(outputFile, false))
        {
            final String[] output = {""};
            counter.forEach((k, v) -> output[0] += k + ": " + v + "\n");
            writer.write(output[0]);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
