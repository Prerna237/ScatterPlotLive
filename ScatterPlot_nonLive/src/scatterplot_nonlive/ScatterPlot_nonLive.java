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

    static int numOfClusters;
    static FileReader fr;
    static BufferedReader br;
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
        lineChart.setLegendVisible(true);
        lineChart.setHorizontalGridLinesVisible(true);
        if (fr == null) {
            System.err.println("The Log file of the clustering process not found");
            System.exit(1);
        }
        numOfClusters = Integer.parseInt(br.readLine());
        ScatterChart.Series<Number, Number>[] array = new ScatterChart.Series[numOfClusters];
        System.out.println("NumofCluster=" + numOfClusters);
        for (int i = 0; i < numOfClusters; i++) {
            array[i] = new ScatterChart.Series<Number, Number>();
        }

        input = " ";
        while ((input = br.readLine()) != null) {
            {
                System.out.println(input.charAt(input.length()-1));
                array[Integer.parseInt(""+input.charAt(input.length()-1))-1].getData().add(new ScatterChart.Data<Number, Number>(Integer.parseInt("" + input.charAt(0)), Integer.parseInt("" + input.charAt(2))));
            }
        }
//        series.setName("Cluster A");
//        seriesb.setName("Cluster B");
//        seriesc.setName("Cluster C");

        if (lineChart.getData() == null) {
            lineChart.setData(FXCollections.<XYChart.Series<Number, Number>>observableArrayList());
        }
        for(int i=0;i<numOfClusters;i++){
            array[i].setName("Cluster "+(i+1));
            //System.out.println("Adding "+(i+1));
            if(array[i].getData().size()>0){
            lineChart.getData().add(array[i]);
            }
        }
        Scene scene = new Scene(lineChart, 500, 400);
        scene.getStylesheets().add(getClass().getResource("Chart.css").toExternalForm());
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
