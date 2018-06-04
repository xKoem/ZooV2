package pl.xkoem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileReader {

     static List<Integer> readFile(String filename) {
        Optional<BufferedReader> inputReader = readDataFile(filename);
        if(!inputReader.isPresent()) {
            System.out.println("Cannot read file");
            return null;
        }
        BufferedReader reader = inputReader.get();

        List<Integer> data = new ArrayList<>();
        String textLine = readLine(reader);
        do {
            data.add(Integer.valueOf(textLine));
            textLine = readLine(reader);
        } while (textLine != null);

        return data;
    }

    private static String readLine(BufferedReader reader) {
         String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private static Optional<BufferedReader> readDataFile(String filename) {
        Optional<BufferedReader> inputReader = Optional.empty();
        try {
            inputReader = Optional.of(new BufferedReader(new java.io.FileReader(filename)));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + filename);
        }
        return inputReader;
    }
}
