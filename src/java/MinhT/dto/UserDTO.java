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
public class UserDTO implements Serializable{
    private String name;
    private String password;
    private int statusID;
    private int roleID;
    private Date createDate;
    private String phone;
    private String address, email;

    public UserDTO(String name, String password, int statusID, int roleID, Date createDate, String phone, String address, String email) {
        this.name = name;
        this.password = password;
        this.statusID = statusID;
        this.roleID = roleID;
        this.createDate = createDate;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public UserDTO(String name, String password, int roleID) {
        this.name = name;
        this.password = password;
         this.roleID = roleID;
    }

   


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "name=" + name + ", password=" + password + ", statusID=" + statusID + ", roleID=" + roleID + ", createDate=" + createDate + ", phone=" + phone + ", address=" + address + ", email=" + email + '}';
    }

 
   
}
