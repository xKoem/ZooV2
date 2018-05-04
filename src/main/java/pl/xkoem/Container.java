package pl.xkoem;

import java.util.ArrayList;
import java.util.List;

public class Container {

    List<ArrayList<ArrayList<Double>>> zooContainers;
    Integer amountOfContainers;

    Container(Integer amountOfContainers, Integer amountOfElements, Integer sizeOfElement) {
        this.amountOfContainers = amountOfContainers;

        Double containerSize = Math.ceil(amountOfElements.doubleValue()/amountOfContainers);
        prepareContainers(amountOfContainers, containerSize.intValue(), sizeOfElement);
    }

    private void prepareContainers(Integer amountOfContainers, Integer containerSize, Integer sizeOfElement) {
        zooContainers = new ArrayList<>();
        for (int i = 0; i < amountOfContainers; i++) {
//            zooContainers.add(new Double[containerSize][sizeOfElement]);
            zooContainers.add(new ArrayList<>());
        }
    }

    public void addObjectsFromClass(ArrayList<ArrayList<Double>> elemetsFromClass) {
        for (int i = 0; i < elemetsFromClass.size(); i++) {
            if (i%amountOfContainers == 0 && elemetsFromClass.size() - i < amountOfContainers) {
                while (i < elemetsFromClass.size()) {
                    zooContainers.get(amountOfContainers -1).add(elemetsFromClass.get(i));
                    i++;
                }
            } else {
                zooContainers.get(i % amountOfContainers).add(elemetsFromClass.get(i));
            }
        }
    }

    public void addObjectsFromAllClasses(List<ArrayList<ArrayList<Double>>> elemetsFromAllClasses) {
        for (int i = 0; i < elemetsFromAllClasses.size(); i++) {
            addObjectsFromClass(elemetsFromAllClasses.get(i));
        }
    }

    public void printContainers() {
        int index = 0;
        for (ArrayList<ArrayList<Double>> ss: zooContainers) {
            System.out.println("Container " + index++ + ", container size: " + ss.size());
            for (ArrayList<Double> s :ss) {
                for (Double value: s) {
                    System.out.print(value + " ");
                }
                System.out.print("\n");
            }
        }
    }

    public Double[][] returnContainerAsArray(int containerNumber) {
        ArrayList<ArrayList<Double>> container = zooContainers.get(containerNumber);
        Double[][] arrayContainer = new Double[container.size()][container.get(0).size()];
        for (int i = 0; i < container.size(); i++) {
            for (int j = 0; j < container.get(i).size(); j++) {
                arrayContainer[i][j] = container.get(i).get(j);
            }
        }
        return arrayContainer;
    }

}
