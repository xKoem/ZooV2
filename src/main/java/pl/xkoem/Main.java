package pl.xkoem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> predictedValues = FileReader.readFile("libsvm/libsvm-3.22/java/predictedValues");
        List<Integer> correctValues = FileReader.readFile("libsvm/libsvm-3.22/java/correctValues");

        printValues(predictedValues, correctValues);

        MistakeArray mistakeArray = new MistakeArray(7);
        mistakeArray.buildArray(correctValues, predictedValues);

        mistakeArray.printArray();

    }

    private static void printValues(List<Integer> predictedValues, List<Integer> correctValues) {
        System.out.print("Expected: ");
        correctValues.forEach(System.out::print);
        System.out.println();
        System.out.print("Actual:   ");
        predictedValues.forEach(System.out::print);
        System.out.println();
    }
}
