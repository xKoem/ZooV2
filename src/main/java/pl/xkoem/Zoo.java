package pl.xkoem;

import java.util.ArrayList;
import java.util.List;

class Zoo {

    private List<ArrayList<ArrayList<Double>>> animalListGroupByClass;
    private List<Integer> elementsInClass;
    private Integer amountOfElements;
    private Integer sizeOfElement;

    Zoo(String data) {
        animalListGroupByClass = FileReader.readFile(data);
        addAmountOfElementInClass();
        countElements();
        sizeOfElement = animalListGroupByClass.get(1).get(0).size();
    }

    private Double getElementPercentage(int classNumber) {
        return elementsInClass.get(classNumber).doubleValue() / amountOfElements;
    }

    private void countElements() {
        amountOfElements = 0;
        for (Integer i :elementsInClass) {
            amountOfElements += i;
        }
    }

    private void addAmountOfElementInClass() {
        elementsInClass = new ArrayList<>();
        elementsInClass.add(0, 0); //adding 0 at 0 position;

        for (int i = 1; i < animalListGroupByClass.size(); i++) {
            elementsInClass.add(i, getNumberOfItemsInClass(i));
        }
    }

    void printItemsInClass() {
        for (int i = 1; i < animalListGroupByClass.size(); i++) {
            System.out.println("Klasa " + i + " " + elementsInClass.get(i) + "  |  " + getElementPercentage(i));
        }
    }

    private int getNumberOfItemsInClass(int classNumber) {
        return animalListGroupByClass.get(classNumber).size();
    }

    void printAllValues() {
        for (ArrayList<ArrayList<Double>> ss: animalListGroupByClass) {
            for (ArrayList<Double> s :ss) {
                for (Double value: s) {
                    System.out.print(value + " ");
                }
                System.out.print("\n");
            }
            System.out.print("Nowa klasa\n");
        }
    }

    public Integer getAmountOfElements() {
        return amountOfElements;
    }

    public ArrayList<ArrayList<Double>> getElemetsFromClass(int i) {
        return animalListGroupByClass.get(i);
    }

    public List<ArrayList<ArrayList<Double>>> getElemetsFromAllClasses() {
        return animalListGroupByClass;
    }

    public Integer getSizeOfElement() {
        return sizeOfElement;
    }
}
