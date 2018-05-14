package pl.xkoem;

import libsvm.svm_node;

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

    private void addObjectsFromClass(ArrayList<ArrayList<Double>> elemetsFromClass) {
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

    void addObjectsFromAllClasses(List<ArrayList<ArrayList<Double>>> elementsFromAllClasses) {
        for (ArrayList<ArrayList<Double>> elemetsFromAllClass : elementsFromAllClasses) {
            addObjectsFromClass(elemetsFromAllClass);
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

    public double[][] returnContainerAsArray(int containerNumber) {
        ArrayList<ArrayList<Double>> container = zooContainers.get(containerNumber);
        double[][] arrayContainer = new double[container.size()][container.get(0).size()];
        for (int i = 0; i < container.size(); i++) {
            for (int j = 0; j < container.get(i).size(); j++) {
                arrayContainer[i][j] = container.get(i).get(j);
            }
        }
        return arrayContainer;
    }

    public double[] getClasses(int containerNumber) {
        ArrayList<ArrayList<Double>> container = zooContainers.get(containerNumber);
        double[] classes = new double[container.size()];
        for (int i = 0; i < container.size(); i++) {
            int classPosition = container.get(i).size() - 1;
            classes[i] = container.get(i).get(classPosition);
        }
        return classes;
    }

    public svm_node[][] getNodes(int containerNumber) {
        ArrayList<ArrayList<Double>> container = zooContainers.get(containerNumber);
        int elements = container.size();
        int values = container.get(0).size();

        svm_node[][] nodes = new svm_node[elements][values];

        for (int i = 0; i < elements; i++) {
            svm_node[] innerNodes;
            List<svm_node> nodeList = new ArrayList<svm_node>();
            for (int j = 0; j < values - 1; j++) {
                svm_node node = new svm_node();
                node.index = j + 1;
                node.value = container.get(i).get(j);
               if (node.value != 0) {
                   nodeList.add(node);
               }
            }
            svm_node node = new svm_node();
            node.index = -1;
            node.value = 0.0;
            nodeList.add(node);
            int k;
            for (k = 0; k < nodeList.size(); k++) {
                nodes[i][k] = nodeList.get(k);
            }
            for (k = k; k < values; k++ ) {
                nodes[i][k] = node;
            }

        }
        return nodes;
    }
}
