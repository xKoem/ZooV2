package pl.xkoem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FileReader {

     static List<ArrayList<ArrayList<Double>>> readFile(String filename) {
        Optional<BufferedReader> inputReader = readDataFile(filename);
        if(!inputReader.isPresent()) {
            System.out.println("Cannot read file");
            return null;
        }
        BufferedReader reader = inputReader.get();

        List<ArrayList<ArrayList<Double>>> data = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            data.add(new ArrayList<>());
        }

        String textLine = readLine(reader);

        do {
            String[] element = textLine.split(",");
            Integer animalClass = Integer.valueOf(element[element.length-1]);
            ArrayList<Double> elementList = new ArrayList<>();
            elementList.addAll(Arrays.asList(changeStringArrayToDoubleArray(element)));

            data.get(animalClass).add(elementList);

            textLine = readLine(reader);
        } while (textLine != null);

        return data;
    }

    private static Double[] changeStringArrayToDoubleArray(String[] strings) {
         return Arrays.stream(strings).map(Double::valueOf).toArray(Double[]::new);
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
