package pl.xkoem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MistakeArray {

    private List<List<Integer>> mistakeArray;

    MistakeArray(int size) {
       createArray(size);
    }

    private void createArray(int size) {
        mistakeArray = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mistakeArray.add(Stream.generate(() -> 0).limit(size).collect(Collectors.toList()));
        }
    }

    void buildArray(List<Integer> expected, List<Integer> actual) {
        for (int i = 0; i < expected.size(); i++) {
            Integer value = mistakeArray.get(expected.get(i) - 1).get(actual.get(i) -1);
            mistakeArray.get(expected.get(i) - 1).set(actual.get(i) - 1, value + 1);
        }
    }

    void printArray() {
        printTopBar();
        int classNum = 0;
        for (List<Integer> list: mistakeArray) {
            System.out.print(++classNum + "| ");
            for(Integer i: list) {
               printCell(i);
            }
            System.out.println();
        }
    }

    private void printTopBar() {
        System.out.print(" ");
        for (int i = 1; i < mistakeArray.size() + 1; i++) {
            System.out.print("__"+ i);
        }
        System.out.println();
    }

    private void printCell(Integer i) {
        if (i > 9)
            System.out.print(i + " ");
        else
            System.out.print(i + "  ");
    }
}
