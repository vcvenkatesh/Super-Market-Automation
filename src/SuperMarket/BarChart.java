/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarket;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.*;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;
/**
 *
 * @author vumma
 */
public class BarChart {
 public BarChart()
        {
                
        }

        public static CategoryDataset createSaleDataset(java.util.HashMap<String,Float> lsh)
        {
                DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
                for(String sh:lsh.keySet()){
                    defaultcategorydataset.addValue(lsh.get(sh), "Sales", sh);
                }
                
                return defaultcategorydataset;
        }
        public static CategoryDataset createProfitDataset(HashMap<String,Float> lsh)
        {
                DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
                for(String sh:lsh.keySet()){
                    defaultcategorydataset.addValue(lsh.get(sh), "Profit", sh);
                }
                
                return defaultcategorydataset;
        }
        public static CategoryDataset createPriceDataset(HashMap<String,Float> lsh)
        {
                DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
                for(String sh:lsh.keySet()){
                    defaultcategorydataset.addValue(lsh.get(sh), "Prices", sh);
                }
                
                return defaultcategorydataset;
        }

        public static JFreeChart createChart(CategoryDataset categorydataset, String name, String type, String t)
        {
                JFreeChart jfreechart = ChartFactory.createLineChart(name, null, type, categorydataset, PlotOrientation.VERTICAL, false, true, false);
                jfreechart.addSubtitle(new TextTitle(t));
                TextTitle texttitle = new TextTitle("");
                texttitle.setFont(new Font("SansSerif", 0, 10));
                texttitle.setPosition(RectangleEdge.BOTTOM);
                texttitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
                jfreechart.addSubtitle(texttitle);
                CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
                categoryplot.setRangePannable(true);
                categoryplot.setRangeGridlinesVisible(false);
                java.net.URL url = (BarChart.class).getClassLoader().getResource("line_Chart_example.png");
                if (url != null)
                {
                        ImageIcon imageicon = new ImageIcon(url);
                        jfreechart.setBackgroundImage(imageicon.getImage());
                        categoryplot.setBackgroundPaint(null);
                }
                NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
                numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                ChartUtilities.applyCurrentTheme(jfreechart);
                LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer)categoryplot.getRenderer();
                lineandshaperenderer.setBaseShapesVisible(true);
                lineandshaperenderer.setDrawOutlines(true);
                lineandshaperenderer.setUseFillPaint(true);
                lineandshaperenderer.setBaseFillPaint(Color.white);
                lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3F));
                lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
                lineandshaperenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5D, -5D, 10D, 10D));
                return jfreechart;
        }

        public static ChartPanel createSaleChart(java.util.HashMap<String,Float> lsh,int i)
        {
                String s  = i==0?"Daily Analysis":(i==1)?"Monthly Analysis":"Yearly Analysis";
                JFreeChart jfreechart = createChart(createSaleDataset(lsh),"Sales Chart","quantity",s);
                ChartPanel chartpanel = new ChartPanel(jfreechart);
                chartpanel.setMouseWheelEnabled(true);
                
                return chartpanel;
        }
        public static ChartPanel createProfitChart(java.util.HashMap<String,Float> profit, int i){
            String s  = i==0?"Daily Analysis":(i==1)?"Monthly Analysis":"Yearly Analysis";
            JFreeChart jfreechart = createChart(createProfitDataset(profit),"Profit Chart","amount(in Rs.)",s);
                ChartPanel chartpanel = new ChartPanel(jfreechart);
                chartpanel.setMouseWheelEnabled(true);
                
                return chartpanel;
        }
        public static ChartPanel createPriceChart(java.util.HashMap<String,Float> price, int i){
            String s  = i==0?"Daily Analysis":(i==1)?"Monthly Analysis":"Yearly Analysis";
            JFreeChart jfreechart = createChart(createPriceDataset(price),"Price Chart","amount(in Rs.)",s);
                ChartPanel chartpanel = new ChartPanel(jfreechart);
                chartpanel.setMouseWheelEnabled(true);
                
                return chartpanel;
        }
        
}