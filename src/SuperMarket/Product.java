/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarket;

import java.io.Serializable;

/**
 *
 * @author vumma
 */
public class Product implements Serializable{
    
    
    private String productID;
    private String name;
    private String manufacturer;
    private String location;
    private float cost;
    private String description;
    private float discount;
    private float quantity;
    private float MRP;
    private static int ADD = 1;
    private static int DELETE = -1;
    
    
    public Product(){
        
    }

    public Product(String productID, String name, String manufacturer, String location, float cost, String description, float discount, float quantity, float MRP) {
        this.productID = productID;
        this.name = name;
        this.manufacturer = manufacturer;
        this.location = location;
        this.cost = cost;
        this.description = description;
        this.discount = discount;
        this.quantity = quantity;
        this.MRP = MRP;
    }
    
    

    public Product(String nameString,String manufacturerString,String productIDString)
    {
        quantity = 0;
        name = nameString;
        manufacturer = manufacturerString;
        productID = productIDString;
    }
    public float calcProfit()
    {
        return ((100-discount)/100)*MRP-cost;
    }
    
    public void updateQuantity(float newQuantityUpdate,int updateType)
    {
        if(updateType==ADD)
        {
            quantity += newQuantityUpdate;
        }	
        else if(updateType==DELETE)
        {
            quantity -= newQuantityUpdate;
        }
    }
    public String getProductID()
    {
        return productID;
    }
    
    public void setProductID(String productId){
        productID = productId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String namE){
        name = namE;
    }
    public String getManufacturer()
    {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturerr){
        manufacturer = manufacturerr;
    }
    
    public float getMRP()
    {
        return MRP;
    }
    public void setMRP(float MRPVal)
    {
        MRP = MRPVal;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String desc)
    {
        description = desc;
    }
    public float getCost()
    {
        return cost;
    }
    public void setCost(float costVal)
    {
        cost = costVal;
    }
    public float getDiscount()
    {
        return discount;
    }
    public void setDiscount(float discountVal)
    {
        discount = discountVal;
    }
    public float getQuantity()
    {
        return quantity;
    }
    public void setQuantity(float quantityVal)
    {
        quantity = quantityVal;
    }
    public void setLocation(String loc)
    {
        location = loc;
    }
    public String getLocation()
    {
        return location;
    }
    
}
