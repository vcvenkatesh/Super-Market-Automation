/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarket;

import static SuperMarket.Main.db;

/**
 *
 * @author vumma
 */
public class EmployeeDatabase {
    
    public static String changePassword(String username,String oldPassword,String newPassword){
        
        
        String query = "UPDATE employee set password='"+newPassword+"' where empid='"+username+"' and password='"+oldPassword+"';";
        
        String result = db.updateQuery(query);
        
        return result;
    }
    
}
