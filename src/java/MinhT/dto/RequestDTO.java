/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author MinhT
 */
public class RequestDTO implements Serializable{
    private int requestID;
    private Date dateBook;
    private int statusReqID;
    private String email;
    private String productID;

    public RequestDTO(int requestID, Date dateBook, int statusReqID, String email, String productID) {
        this.requestID = requestID;
        this.dateBook = dateBook;
        this.statusReqID = statusReqID;
        this.email = email;
        this.productID = productID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public Date getDateBook() {
        return dateBook;
    }

    public void setDateBook(Date dateBook) {
        this.dateBook = dateBook;
    }

    public int getStatusReqID() {
        return statusReqID;
    }

    public void setStatusReqID(int statusReqID) {
        this.statusReqID = statusReqID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }


   
}
