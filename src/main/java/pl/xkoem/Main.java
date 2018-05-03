package pl.xkoem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ArrayList<ArrayList<String>>> list = FileReader.readFile("/Users/koem/IdeaProjects/ZooV2/src/main/resources/zoo.data");

        for (ArrayList<ArrayList<String>> ss: list) {
            for (ArrayList<String> s :ss) {
                for (String value: s) {
                    System.out.print(value + " ");
                }
                System.out.print("\n");
            }
            System.out.print("Nowa klasa\n");
        }
    }
}
