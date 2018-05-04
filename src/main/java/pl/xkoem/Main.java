package pl.xkoem;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Zoo zoo = new Zoo("/Users/koem/IdeaProjects/ZooV2/src/main/resources/zoo.data");
        zoo.printItemsInClass();
//        zoo.printAllValues();

        Container container = new Container(5, zoo.getAmountOfElements(), zoo.getSizeOfElement());
//        container.addObjectsFromClass(zoo.getElemetsFromClass(1));
        container.addObjectsFromAllClasses(zoo.getElemetsFromAllClasses());
//        container.printContainers();

//        System.out.println(Arrays.deepToString(container.returnContainerAsArray(0)));
    }



}
