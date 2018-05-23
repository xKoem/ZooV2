package pl.xkoem;

import com.github.chen0040.data.evaluators.ClassifierEvaluator;
import com.github.chen0040.data.frame.DataFrame;
import com.github.chen0040.data.frame.DataQuery;
import com.github.chen0040.data.frame.DataRow;
import com.github.chen0040.data.utils.TupleTwo;
import com.github.chen0040.svmext.classifiers.OneVsOneSVC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream irisStream = null;
        try {
            irisStream = new FileInputStream("/Users/koem/IdeaProjects/ZooV2/src/main/resources/zoo.data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataFrame irisData = DataQuery.csv(",", false)
                .from(irisStream)
                .selectColumn(0).asNumeric().asInput("0")
                .selectColumn(1).asNumeric().asInput("1")
                .selectColumn(2).asNumeric().asInput("2")
                .selectColumn(3).asNumeric().asInput("3")
                .selectColumn(4).asCategory().asOutput("4")
                .selectColumn(5).asCategory().asOutput("5")
                .selectColumn(6).asCategory().asOutput("6")
                .selectColumn(7).asCategory().asOutput("7")
                .selectColumn(8).asCategory().asOutput("8")
                .selectColumn(9).asCategory().asOutput("9")
                .selectColumn(10).asCategory().asOutput("10")
                .selectColumn(11).asCategory().asOutput("11")
                .selectColumn(12).asCategory().asOutput("12")
                .selectColumn(13).asCategory().asOutput("13")
                .selectColumn(14).asCategory().asOutput("14")
                .selectColumn(15).asCategory().asOutput("15")
                .selectColumn(16).asCategory().asOutput("16")
                .build();

        TupleTwo<DataFrame, DataFrame> parts = irisData.shuffle().split(0.7);

        DataFrame trainingData = parts._1();
        DataFrame crossValidationData = parts._2();

        OneVsOneSVC multiClassClassifier = new OneVsOneSVC();
        multiClassClassifier.fit(trainingData);


        ClassifierEvaluator evaluator = new ClassifierEvaluator();

        for(DataRow dataRow: crossValidationData.rows()) {
            String predicted = multiClassClassifier.classify(dataRow);
            String actual = dataRow.categoricalTarget();
            System.out.println("predicted: " + predicted + "\tactual: " + actual);
            evaluator.evaluate(actual, predicted);
        }

        evaluator.report();

    }



}
