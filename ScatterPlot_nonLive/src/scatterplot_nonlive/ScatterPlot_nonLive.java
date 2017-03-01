/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatterplot_nonlive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author dell pc
 */
public class ScatterPlot_nonLive extends Application {

    static FileReader fr;
    static BufferedReader br;
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    NumberAxis xAxis, yAxis;
    ScatterChart<Number, Number> lineChart;
    String input;

    @Override
    public void start(Stage stage) throws Exception {

        fr = new FileReader("input.txt");
        br = new BufferedReader(fr);

        stage.setTitle("Clustering Result");
        xAxis = new NumberAxis(0, 10, 1);
        yAxis = new NumberAxis(0, 10, 1);
        lineChart = new ScatterChart<Number, Number>(xAxis, yAxis);
        xAxis.setLabel("");
        yAxis.setLabel("");
        lineChart.setAnimated(false);
        lineChart.setTitle("");
        lineChart.setHorizontalGridLinesVisible(true);
        if (fr == null) {
            System.err.println("The Log file of the clustering process not found");
            System.exit(1);
        }

        final ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
        final ScatterChart.Series<Number, Number> seriesb = new ScatterChart.Series<Number, Number>();
        final ScatterChart.Series<Number, Number> seriesc = new ScatterChart.Series<Number, Number>();
        input = " ";
        while ((input = br.readLine()) != null) {
             {
                System.out.println(input);
                if (input.endsWith("" + 1)) {
                    series.getData().add(new ScatterChart.Data<Number, Number>(Integer.parseInt("" + input.charAt(0)), Integer.parseInt("" + input.charAt(2))));
                } else if (input.endsWith("" + 2)) {
                    seriesb.getData().add(new ScatterChart.Data<Number, Number>(Integer.parseInt("" + input.charAt(0)), Integer.parseInt("" + input.charAt(2))));
                } else if (input.endsWith("" + 3)) {
                    seriesc.getData().add(new ScatterChart.Data<Number, Number>(Integer.parseInt("" + input.charAt(0)), Integer.parseInt("" + input.charAt(2))));

                } else {

                }
            }
        }
        series.setName("Cluster A");
        seriesb.setName("Cluster B");
        seriesc.setName("Cluster C");

        if (lineChart.getData() == null) {
            lineChart.setData(FXCollections.<XYChart.Series<Number, Number>>observableArrayList());
        }
        lineChart.getData().add(seriesb);
        lineChart.getData().add(series);
        lineChart.getData().add(seriesc);
//        lineChart.getData().removeAll(series, seriesb, seriesc);
        Scene scene = new Scene(lineChart, 500, 400);
        //       scene.getStylesheets().add(getClass().getResource("Chart.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
