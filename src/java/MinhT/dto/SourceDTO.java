/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MinhT
 */
public class SourceDTO implements Serializable {
  private String productID;
  private  String productName;
   private String color ;
   private String quanlity ;
   private String CategoryName;
   private String createdate;

    public SourceDTO(String productID, String productName, String color, String quanlity, String CategoryName, String createdate) {
        this.productID = productID;
        this.productName = productName;
        this.color = color;
        this.quanlity = quanlity;
        this.CategoryName = CategoryName;
        this.createdate = createdate;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(String quanlity) {
        this.quanlity = quanlity;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "SourceDTO{" + "productID=" + productID + ", productName=" + productName + ", color=" + color + ", quanlity=" + quanlity + ", CategoryName=" + CategoryName + ", createdate=" + createdate + '}';
    }
   

   

}
