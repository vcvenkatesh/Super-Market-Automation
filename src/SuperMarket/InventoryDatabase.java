/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarket;

import static SuperMarket.Main.db;
import SuperMarketData.SalesHistory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vumma
 */
public class InventoryDatabase {
    
    public static int ADD = 1;
    public static int DELETE = 0;
    
    public InventoryDatabase(){
        
        
        
    }
    
    public Product getProduct(String productId) throws SQLException{
        
        String query = "SELECT * FROM product WHERE productid='"+productId+"';";
            
            //getting type of worker
            
        Product product = db.getProduct(query);
        
        
        return product;
    }
    
    
    public void addBill(SalesTransaction st,String timee){
       List<Product> productList =  st.getItemsList();
       
       int i;
        for(i=0;i<productList.size();i++)
        {
            String prodid = (productList.get(i)).getProductID();
            String name = (productList.get(i)).getName();
            float price = (productList.get(i)).getMRP();
            String manufacturer = (productList.get(i)).getManufacturer();
            double costprice = (productList.get(i)).getCost();
            String location = (productList.get(i)).getLocation();
            float discount = (productList.get(i)).getDiscount();
            float quantity = (productList.get(i)).getQuantity();
            String description = (productList.get(i)).getDescription();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            String sellerid = st.getSellerID();
            String transid = st.getTransID();
            
            float totalcost = quantity*price*((100-discount)/100);
            
            String query = "INSERT INTO sales (productid, name, price, manufacturer, costprice, location, discount, quantity, description, time, sellerid, transid,totalcost ) "+
                    "VALUES ( '"+ prodid+"','"+name+"',"+price+",'"+manufacturer+"',"+costprice+",'"+location+"',"+discount+","+quantity+",'"+description+"','"+time+"','"+sellerid+"','"+transid+"',"+totalcost+");";
            
           // String query = "INSERT into sales " + "VALUES ( '"
            //        + prodid+"','"+name+"',"+price+",'"+manufacturer+"',"+costprice+",'"+location+"',"+discount+","+quantity+",'"+description+"',"+time+",'"+sellerid+"','"+transid+"');";
            
            
            String result = db.insertProductintoSales(query);
            System.out.println(result);
            
            
        }
              
    }
    
        //get product list
        
        public List<Product> getProductList() throws SQLException{
            
            String query = "SELECT * FROM product";
            List<Product> result = db.getProductlist(query);
            /*for(int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getName());
            }*/
            return result;
        }
        
        
        //add new product
        
        public String AddProduct(Product p) throws SQLException{
            
           /* String query = "INSERT INTO product (productid, name, price, manufacturer, costprice, location, discount, quantity, description ) "+
                    "VALUES ( '"+ p.getProductID()+"','"+p.getName()+"',"+p.getCost()+",'"+p.getManufacturer()+"',"+p.getMRP()+",'"+p.getLocation()+"',"+p.getDiscount()+","+p.getQuantity()+",'"+p.getDescription()+"');";
            */
           
           String query = "SELECT * from product where productid='"+p.getProductID()+"';"; //+"' AND name='"+p.getName()+
           String result = db.getProductResult(query);
           
           if(result.equalsIgnoreCase("NOPE")){
              String query1 = "INSERT INTO product (productid, name, price, manufacturer, costprice, location, discount, quantity, description ) "+
                    "VALUES ( '"+ p.getProductID()+"','"+p.getName()+"',"+p.getCost()+",'"+p.getManufacturer()+"',"+p.getMRP()+",'"+p.getLocation()+"',"+p.getDiscount()+","+p.getQuantity()+",'"+p.getDescription()+"');";
              String res = db.insertQuery(query1);
           }
            
            return result;
        }

    void updateInventory(String productid, float quantity, int add_del) throws SQLException {
        
        String query = "SELECT quantity from product WHERE productid='"+productid+"';";
        
        float old_quantity =  db.getProductQuantity(query);
        float new_quantity = old_quantity;
        if(add_del == ADD){
            new_quantity = old_quantity + quantity;
    
        }else if(add_del == DELETE){
            new_quantity = old_quantity - quantity;
        }
        
        String query1 = "UPDATE product set quantity="+new_quantity+" WHERE productid='"+productid+"';";
        int res = db.updateProduct(query1);
        System.out.println("Updated product quantity "+res);
    }

    String updatePrice(String productid, float price) {
        
        String query = "UPDATE product set price="+price+" WHERE productid='"+productid+"';";
        
        String result = db.updateQuery(query);
        
        
        if(result.equalsIgnoreCase("SUCCESS")){
            return "UPDATED";
        }else{
            return "NOPE";
        }
        
    }

    void updateDiscount(String productid, float discount) {
        String query = "UPDATE product set discount="+discount+" WHERE productid='"+productid+"';";
        
        String result = db.updateQuery(query);
    }

    public List<SalesHistory> getSalesHistory(String ProductID, String startDate, String endDate) throws ParseException {
        //List<SalesHistory> s = new ArrayList<SalesHistory>();
        
        String query = "SELECT * FROM sales WHERE productid='"+ProductID+"' and time >= '"+dateReturn(startDate)+"' and time <='"+dateReturn(endDate)+"';";
       // String q = "SELECT * FROM `sales` WHERE productid='123' and time >= '2019-01-01' and time <= '2019-07-01'";
            List<SalesHistory> result = db.getSaleslist(query);
            System.out.println(dateReturn(startDate) +" "+dateReturn(endDate));
          /*  for(SalesHistory model : result) {
            System.out.println(model.getDateOfSale());
            }*/
            return result;
    }

    HashMap<String, Float> getOverallProfit(String startDate, String endDate) throws ParseException, SQLException {
        //time profit
        HashMap<String , Float> hashMap = new HashMap<String, Float>();
        String query = "SELECT * FROM sales WHERE time >= '"+dateReturn(startDate)+"' and time <='"+dateReturn(endDate)+"';";
        
        HashMap<String,Float> result = db.getTotalSaleslist(query);
        
        return hashMap;
        
    }

    HashMap<String, Float> getOverallStats(String startDate, String endDate) throws ParseException, SQLException {
        HashMap<String , Float> hashMap = new HashMap<String, Float>();
        
        //date quantity
        
        String query = "SELECT * FROM sales WHERE time >= '"+dateReturn(startDate)+"' and time <='"+dateReturn(endDate)+"';";
        
        HashMap<String,Float> result = db.getTotalSaleslist(query);
        
        return hashMap;
    }
    
    
    public LocalDate dateReturn(String date) throws ParseException{
       // Date sDate;
        
       // DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       // sDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
               
        //sDate = formatter.parse(date);
        
       // String d = formatter.format(sDate);
       
        String str = date;

        String d[] = str.split("-");
        
        if(d[1].length() == 1){
            d[1] = "0"+d[1];
        }
        if(d[2].length() == 1){
            d[2] = "0"+d[2];
        }
        
        date = d[0]+"-"+d[1]+"-"+d[2];
       
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        LocalDate smdate = LocalDate.parse(date, format);
        //System.out.println(smdate);
        return smdate;
    }
    
    
    
}
