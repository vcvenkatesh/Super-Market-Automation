/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import SuperMarket.Product;
import SuperMarketData.SalesHistory;
import java.sql.Connection;
import java.sql.DataTruncation;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author vumma
 */
public class DBConnect {

    
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBConnect() throws Exception{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
        st  = con.createStatement();
        
    }
    
    public void closeConnections() throws SQLException{
        st.close();
        con.close();
    }
    
    public boolean checkDB(String dbname){
        try{
            String query = "show databases";
            rs = st.executeQuery(query);
            
            while(rs.next()){
                String name = rs.getString("Database");
                
                if(name.equals(dbname)){
                    return true;
                }
            }
        }catch(SQLException e){
            System.err.println("Error : "+ e);
        }
        
        return false;
    }
    
   
    
    public String getWorkerName(String query) throws SQLException {
        
        String workerName = "";
        try(ResultSet r = getrs(query)) {
            while(r.next()){
                workerName = r.getString("name");
            }   
        }
        
        return workerName;
    }
    
    
    public String getWorkerType(String query) throws SQLException {
        
        String workerType = "";
        try(ResultSet r = getrs(query)) {
            while(r.next()){
                workerType = r.getString("workertype");
            }   
        }
        
        return workerType;
    }
    
    
    public Product getProduct(String query) throws SQLException{
        
        Product product = null;
        
        try(ResultSet r = getrs(query)) {
            while(r.next()){
                product = new Product();
                
                product.setCost((float) r.getDouble("price"));
                product.setDescription(r.getString("description"));
                product.setDiscount(r.getFloat("discount"));
                product.setLocation(r.getString("location"));
                product.setMRP(r.getFloat("costprice"));
                product.setManufacturer(r.getString("manufacturer"));
                product.setName(r.getString("name"));
                product.setProductID(r.getString("productid"));
                product.setQuantity(r.getFloat("quantity"));
                
            }
        }
        
        return product;
    }
    
    public List<Product> getProductlist(String query) throws SQLException{
        
        Product product = null;
        
        List<Product> p = new ArrayList<Product>();
        
        ResultSet r =  null;
                //st.executeQuery(query);
        try{
            r = st.executeQuery(query);
            while(r.next()){
                product = new Product();
               // System.out.println(r.getString("name"));
                product.setCost((float) r.getDouble("price"));
                product.setDescription(r.getString("description"));
                product.setDiscount(r.getFloat("discount"));
                product.setLocation(r.getString("location"));
                product.setMRP(r.getFloat("costprice"));
                product.setManufacturer(r.getString("manufacturer"));
                product.setName(r.getString("name"));
                product.setProductID(r.getString("productid"));
                product.setQuantity(r.getFloat("quantity"));
                
                p.add(product);
                
            }
        }catch(SQLException e){
            
        }
        
        return p;
    }
    
    
    public String changePass(String query){
        
        String result = updateQuery(query);
        
        return result;
        
    }
    
    public String insertProductintoSales(String query){
        
        String result = insertQuery(query);
        
        return result;
    }
    
    public void query(String query){
        try{
            rs = st.executeQuery(query);
        }catch(SQLException e){
            System.err.println("Error : "+e);
        }
    }
    
    public String updateQuery(String query){
        
        String result = "";
        int res ;
        try{
            res = st.executeUpdate(query);
            if(res >= 1){
                result = "SUCCESS";
                return result;
            }
            
        }catch(SQLException e){
            System.err.println("Error : "+e);
        }
        return result;
    }
    
    public int updateProduct(String query){
        
        
        int res =0 ;
        try{
            res = st.executeUpdate(query);
            if(res >= 1){
               
                return res;
            }
            
        }catch(SQLException e){
            System.err.println("Error : "+e);
        }
        return res;
    }
    
    public String insertQuery(String query){
        
        String result = "NOPE";
        boolean res ;
        try{
            res = st.execute(query);
            if(res  == true){
                result = "SUCCESS";
                return result;
            }
            
        }catch(SQLException e){
            System.err.println("Error : "+e);
        }
        return result;
    }
    
    
    public ResultSet getrs(String query){
        try{
            rs = st.executeQuery(query);
            
            return rs;
        }catch(SQLException e){
            System.err.println("Error : "+ e);
        }
        
        return null;
    }

    public float getProductQuantity(String total_quantity_query) throws SQLException {
        
        
        float productQuantity = 0;
        try(ResultSet r = getrs(total_quantity_query)) {
            while(r.next()){
                productQuantity = r.getFloat("quantity"); //.getString("quantity");
            }   
        }
        
        return productQuantity;
        
        
    }
    
    public String getProductResult(String query) throws SQLException{
        
        int count = 0;
        String result;
        try(ResultSet r = getrs(query)) {
            while(r.next()){             
                count=count+1; 
            }
        }
        
        //changes to be made
        if(count == 1){
            result = "SUCCESS";
        }else{
            result = "NOPE";
        }
        
        return result;
    }

    public List<SalesHistory> getSaleslist(String query) {
        SalesHistory salesHistory = null;
        
        List<SalesHistory> sale = new ArrayList<SalesHistory>();
        
        ResultSet r =  null;
                //st.executeQuery(query);
        try{
            r = st.executeQuery(query);
            while(r.next()){
                salesHistory = new SalesHistory();
                //System.out.println(r.getString("time"));
                salesHistory.setProductId(r.getString("productid"));
                salesHistory.setQuantitySold((float) r.getDouble("quantity"));
                String date = r.getString("time");
                String d[] = date.split("-");
                StringBuilder sb1 = new StringBuilder(d[1]);
                StringBuilder sb2 = new StringBuilder(d[2]);
                if(d[1].charAt(0) == '0'){
                    sb1.deleteCharAt(0);
                }
                if(d[2].charAt(0) == '0'){
                    sb2.deleteCharAt(0);
                }
                date = d[0]+"-"+sb1+"-"+sb2;
                System.out.println("date"+date);
                salesHistory.setDoS(date);
                salesHistory.setCost((float) r.getDouble("price"));
                salesHistory.setSellingprice(r.getFloat("costprice"));
                
                sale.add(salesHistory);
                
            }
        }catch(SQLException e){
            
        }
        
        return sale;
    }

    public HashMap<String, Float> getTotalSaleslist(String query) throws SQLException {
        HashMap<String, Float> sales = new HashMap<String, Float>();
        
        ResultSet r  =null;
        try{
            r = st.executeQuery(query);
            while(r.next()){
                sales.put(r.getString("time"), r.getFloat("quantity"));
                System.out.println(r.getString("time"));
            }
        }catch(SQLException e){
            
        }
        
        return sales;
    }
    
    
}
