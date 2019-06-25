/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarket;

import static SuperMarket.Main.db;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vumma
 */
public class SalesDatabase {
    
    public SalesDatabase(){
        
    }
    
    
    public void addSales(SalesTransaction st) throws SQLException{
        
        
        List<Product> productList =  st.getItemsList();
       
        int i;
        for(i=0;i<productList.size();i++)
        {
            String prodid = (productList.get(i)).getProductID();
            float count = (productList.get(i)).getQuantity();
            
            String total_quantity_query = " SELECT quantity from product WHERE productid='"+prodid+"';";
            float res = db.getProductQuantity(total_quantity_query);
            res = res-count;
            String query = "UPDATE product set quantity='"+res+"' WHERE productid='"+prodid+"';";
            String result = db.updateQuery(query);
            System.out.println(result);
            
            
        }
        
    }
    
}
