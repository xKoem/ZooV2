package pl.xkoem;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_parameter;
import libsvm.svm_problem;

public class Main {
    public static void main(String[] args) {

        Zoo zoo = new Zoo("/Users/koem/IdeaProjects/ZooV2/src/main/resources/zoo.data");
        zoo.printItemsInClass();
//        zoo.printAllValues();

        Container container = new Container(4, zoo.getAmountOfElements(), zoo.getSizeOfElement());
//        container.addObjectsFromClass(zoo.getElemetsFromClass(1));
        container.addObjectsFromAllClasses(zoo.getElemetsFromAllClasses());
//        container.printContainers();

//        svm_node[][] nodes = container.getNodes(0);

//        for (svm_node[] nodeList: nodes) {
//            for (svm_node node : nodeList) {
//                System.out.print(" " + node.index + " " + node.value);
//            }
//            System.out.print("\n");
//        }

        svm_problem problem = new svm_problem();
        problem.y = container.getClasses(0);
        problem.l = container.getClasses(0).length;
        problem.x = container.getNodes(0);

        svm_parameter parameter = new svm_parameter();
        parameter.kernel_type = 2;
        parameter.gamma = 0.05;
        parameter.svm_type = 1;
        parameter.nu = 0.5;

        svm_model model =  svm.svm_train(problem, parameter);

        System.out.println("predict: " + svm.svm_predict(model,container.getNodes(0)[0]));
//        svm.

//        System.out.println(model.l);



        //System.out.println(Arrays.deepToString(container.returnContainerAsArray(0)));
    }



}
