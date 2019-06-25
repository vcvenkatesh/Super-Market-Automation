/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperMarketData;

import java.io.Serializable;

/**
 *
 * @author vumma
 */
public class SalesHistory implements Serializable{
    private String productId;
    private float quantitySold;
    private String doS;
    private float cost;
    private float sellingprice;
    
    public SalesHistory(String pid, float quan, String doS, float cost, float sellingprice){
        productId = pid;
        quantitySold = quan;
        this.doS = doS;
        this.cost = cost;
        this.sellingprice = sellingprice;
    }

    public SalesHistory() {
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantitySold(float quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setDoS(String doS) {
        this.doS = doS;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setSellingprice(float sellingprice) {
        this.sellingprice = sellingprice;
    }
    
    
    
    public String getProductId(){
        return productId;
    }
    public float getQuantitySold(){
        return quantitySold;
    }
    public String getDateOfSale(){
        return doS;
    }
    public float getCostPrice(){
        return cost;
    }
    public float getSellingPrice(){
        return sellingprice;
    }
}

