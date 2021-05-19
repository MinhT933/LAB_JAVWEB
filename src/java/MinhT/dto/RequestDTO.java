/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dto;

/**
 *
 * @author MinhT
 */
public class RequestDTO {
    private String requestID;
    private String dateBook;
    private String statusReqID;
    private String email;
    private String productID;

    public RequestDTO(String requestID, String dateBook, String statusReqID, String email, String productID) {
        this.requestID = requestID;
        this.dateBook = dateBook;
        this.statusReqID = statusReqID;
        this.email = email;
        this.productID = productID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getDateBook() {
        return dateBook;
    }

    public void setDateBook(String dateBook) {
        this.dateBook = dateBook;
    }

    public String getStatusReqID() {
        return statusReqID;
    }

    public void setStatusReqID(String statusReqID) {
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

    @Override
    public String toString() {
        return "RequestDTO{" + "requestID=" + requestID + ", dateBook=" + dateBook + ", statusReqID=" + statusReqID + ", email=" + email + ", productID=" + productID + '}';
    }
}
