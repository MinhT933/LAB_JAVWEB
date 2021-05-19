/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dao;

import MinhT.dto.UserDTO;
import MinhT.Connect.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhT
 */
public class UserDAO {

    public UserDTO Login(String name, String password) throws ClassNotFoundException, SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
                con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "SELECT name,password,roleID FROM [ResourceSharing].[dbo].[Users] WHERE name = '" + name + "' AND password = '" + password + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(name,password,roleID);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;

    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String name= "ad";
        String pass= "123ÁDASD";
        UserDAO dao= new UserDAO();
        UserDTO user = dao.Login(name, pass);
        if(user!= null){
            System.out.println("có ở trong đây");
        }else{
            System.out.println("ko có nhenn");
        }
          
        
    }
   
    


}
