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
public class UserDTO {
    private String name= null;
    private String password= null;
    private String roleid= null;

    public UserDTO() {
    }
    public UserDTO(String name,String password,String roleid){
        this.name=name;
        this.password= password;
        this.roleid=roleid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "name=" + name + ", password=" + password + ", roleid=" + roleid + '}';
    }
    
}
