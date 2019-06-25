/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarket;

import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author vumma
 */
public class PieChart {
    public static ChartPanel createProfitChart(HashMap<String,Float> hm){
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(String s: hm.keySet()){
        dataset.setValue(s, hm.get(s));
        }
        JFreeChart chart = ChartFactory.createPieChart("Overall profit statistics", dataset, true, true, true);

        ChartPanel cp = new ChartPanel(chart);
        return cp;
    }
}
