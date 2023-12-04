package view;

import org.junit.jupiter.api.Test;
import org.knowm.xchart.*;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StockViewTest {

    @Test
    void chart() {

//        List<Integer> range = new ArrayList<>();
//        range.add(1);
//        range.add(2);
//        range.add(3);
//        range.add(4);
//        range.add(5);
//        range.add(6);
//        range.add(7);
//        range.add(8);
//        range.add(9);
//        range.add(10);
//        range.add(11);
//        range.add(12);
//        range.add(13);
//        range.add(14);
//        range.add(15);
//        range.add(16);
//        range.add(17);
//        range.add(18);
//        range.add(19);
//        range.add(21);
//        range.add(22);
//        range.add(23);
//        range.add(24);
//        range.add(25);
//        range.add(26);
//        range.add(27);
//        range.add(28);
//        range.add(29);
//        range.add(30);
//        range.add(31);
//
//
//        List<Double> info = new ArrayList<>();
//        info.add(100.32);
//        info.add(120.32);
//        info.add(150.32);
//        info.add(140.32);
//        info.add(120.32);
//        info.add(110.32);
//        info.add(70.32);
//        info.add(132.32);
//        info.add(102.32);
//        info.add(109.32);
//        info.add(105.32);
//        info.add(190.32);
//        info.add(200.32);
//        info.add(205.32);
//        info.add(210.32);
//        info.add(240.32);
//        info.add(100.32);
//        info.add(50.32);
//        info.add(3.32);
//        info.add(1.32);
//        info.add(100.32);
//        info.add(150.32);
//        info.add(170.32);
//        info.add(154.32);
//        info.add(186.32);
//        info.add(145.32);
//        info.add(154.32);
//        info.add(126.32);
//        info.add(196.32);
//        info.add(124.32);
//
//        XYChart chart = new XYChartBuilder().width(800).height(600).title("TSLA").xAxisTitle("Date").yAxisTitle("Price").build();
//        XYSeries series = chart.addSeries("TESLA", range, info);
//        series.setLineColor(Color.BLUE);
//        series.setLineStyle(SeriesLines.SOLID);
//        series.setMarker(SeriesMarkers.CIRCLE);
//        series.setMarkerColor(Color.RED);

        double[] testdata = new double[2];
        double[] testdata2 = new double[2];

        XYChart chart = QuickChart.getChart("Test", "x", "y", "test", testdata, testdata2);
        XChartPanel chartPanel = new XChartPanel<>(chart);

        JFrame jframe = new JFrame();

        jframe.add(chartPanel);

        JFrame application = new JFrame("The Wolf of Bay Street");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setBackground(new Color(255, 87, 51));

        CardLayout cardLayout = new CardLayout();
        JFrame aFrame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
        application.setPreferredSize(screenSize);

        application.add(chartPanel);
        application.pack();
        application.setVisible(true);

//        JFrame chart2 = new SwingWrapper(chart).displayChart();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            ;
        }





    }
}