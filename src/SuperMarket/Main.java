/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarket;

import DatabaseConnection.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author vumma
 */
public class Main {
    
    public static DBConnect db;
    
    private static boolean isLoggedSucess = false;
    
    public static void main(String[] args) throws SQLException {
        
        
        try {
        // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
           } 
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    // handle exception
            }
        
        
       //Database connection
       
        try{
            db = new DBConnect();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Make sure that database server either Xampp or Wamp servers are Running. ","Server", JOptionPane.INFORMATION_MESSAGE);
	    
            return;
        }
        
        if(db.checkDB("supermarketautomation")){
            
            db.query("use supermarketautomation");
            
        }else{
            
            JOptionPane.showMessageDialog(null, "SuperMarketAutomation Database not created please create ","Database",JOptionPane.INFORMATION_MESSAGE);
              
            System.exit(0);
        }
        
        //Login
        
        
        
       
       
        
        
       // do{
            
         while(isLoggedSucess == false){  
            Login login = new Login(new JFrame(), true);
           /* if(!isLoggedSucess)
                {
                    login.ShowError();
                }*/
                login.setLocationRelativeTo(null);
                login.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent we){
                        System.exit(0);
                    }
                });
            login.setVisible(true);
            
        
         String query = "SELECT workertype FROM employee WHERE empid='"+login.userName()+"' and password='"+login.passWord()+"';";
        //getting type of worker
        System.out.println(login.userName());
        String worker = "";
        try {
        worker = db.getWorkerType(query);
        } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
            System.out.println(worker+"in main");
            if(worker.equalsIgnoreCase("clerk")){
                isLoggedSucess = true;
                login.setEnabled(false);
                //login.dispose();
                
                System.out.println("in clerk");
                SalesClerk salesClerk = new SalesClerk(login.userName());
                salesClerk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                salesClerk.setLocationRelativeTo(null);
                        salesClerk.addWindowListener(new WindowAdapter(){
                            @Override
                            public void windowClosing(WindowEvent evt){
                                try {
                                    db.closeConnections();
                                } catch (SQLException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.exit(0);
                                System.out.println("closeed");
                            }
                        });
                salesClerk.setVisible(true);
                
                
  
            }else if(worker.equalsIgnoreCase("staff")){
                isLoggedSucess = true;
                login.setEnabled(false);
                //login.dispose();
                
                System.out.println("in staff");
                SuperMarketStaff superMarketStaff = new SuperMarketStaff(login.userName());
                superMarketStaff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                superMarketStaff.setLocationRelativeTo(null);
                        superMarketStaff.addWindowListener(new WindowAdapter(){
                            @Override
                            public void windowClosing(WindowEvent evt){
                                try {
                                    db.closeConnections();
                                } catch (SQLException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.exit(0);
                                System.out.println("closeed");
                            }
                        });
                superMarketStaff.setVisible(true);
                
                
            }else if(worker.equalsIgnoreCase("manager")){
                isLoggedSucess = true;
                
                login.setEnabled(false);
                //login.dispose();
                
                System.out.println("in manager");
                Manager manager = new Manager(login.userName());
                manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                manager.setLocationRelativeTo(null);
                        manager.addWindowListener(new WindowAdapter(){
                            @Override
                            public void windowClosing(WindowEvent evt){
                                try {
                                    db.closeConnections();
                                } catch (SQLException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.exit(0);
                                System.out.println("closeed");
                            }
                        });
                manager.setVisible(true);
                
            }
            else{
                isLoggedSucess = false;
            }
              
        }
         //while(isLoggedSucess == true);
        
        
        
        
    }
    
   
    
    
    
    
}
