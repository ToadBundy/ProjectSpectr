import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.editor.ChartEditor;
import org.jfree.chart.editor.ChartEditorManager;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.jdbc.JDBCXYDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jtransforms.dht.DoubleDHT_1D;
import org.jtransforms.fft.DoubleFFT_1D;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        WavReader wavReader = new WavReader("src/main/resources/Rick-Astley-Never-Gonna-Give-You-Up-_radio-edit_.wav");
        int k = wavReader.DATA.length/32000;

        int counter = 0;
        while(counter < k){
            double[] mainFreaquencyForAnySecond = new double[k];
        for(int i = 0; i < k; i++){

            mainFreaquencyForAnySecond[i] = wavReader.DATA[counter*k+i];
        }
        DoubleFFT_1D fft = new DoubleFFT_1D(k);
        fft.realForward(mainFreaquencyForAnySecond);
            System.out.println(Arrays.stream(mainFreaquencyForAnySecond).max());
            counter++;
        }


//        System.out.println("-------------------DATA--------------------");
//        for(int i = 0; i<2*k;i++){
//            System.out.println(mainFreaquencyForAnySecond[i]);
//        }
//        DoubleFFT_1D fft = new DoubleFFT_1D(k);
//        fft.realForward(mainFreaquencyForAnySecond);
//        System.out.println("-------------------AFTER------------------------");
//        for(int i = 0; i<2*k;i++){
//            System.out.println(mainFreaquencyForAnySecond[i]);
        }





//    MakeSound makeSound = new MakeSound();
//    makeSound.playSound("src/main/resources/Rick-Astley-Never-Gonna-Give-You-Up-_radio-edit_.wav");

//    double timer = 10;//sec
//    double freaquency = 1;//Hz
//    double amplitude = 1;
//    double phase = 0;
//    double sampleRate = 10000;
//    double time = 0.1;


//    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//    for(Integer i = 0; i < wavReader.DATA.length/1000; i++){
//        Double t = Double.valueOf(i);
//        Double x = Double.valueOf(wavReader.DATA[i]);
//        dataset.addValue(x, "Spec", t);
//    }
//    JFreeChart chart = ChartFactory.createLineChart("Title","AxisX","AxisY", dataset,PlotOrientation.VERTICAL,true, false,false);
//    ChartFrame frame = new ChartFrame("Graph", chart, false);
//        frame.setSize(1000, 600);
//        frame.setVisible(true);

    }




