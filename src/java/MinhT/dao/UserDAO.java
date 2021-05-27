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
import javax.naming.NamingException;

/**
 *
 * @author MinhT
 */
public class UserDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public UserDTO Login(String name, String password,String email) throws ClassNotFoundException, SQLException {
        UserDTO user = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT email, name,password,roleID FROM [ResourceSharing].[dbo].[Users] WHERE name = '" + name + "' AND password = '" + password + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int roleID = rs.getInt("roleID");
                    email= rs.getString("email");
                    user = new UserDTO(name, password, roleID);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String name = "minht";
//        String pass = "123";
//        UserDAO dao = new UserDAO();
//        UserDTO user = dao.Login(name, pass);
//        if (user != null) {
//            System.out.println("có ở trong đây");
//        } else {
//            System.out.println("ko có nhenn");
//        }
//
//    }

    public boolean insertUser(UserDTO newUser) throws SQLException, NamingException, ClassNotFoundException {
        boolean flag = false;
        try {

            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT into [ResourceSharing1].[dbo].[Users](name, phone, password, address, statusID, roleID, createDate, email)"
                        + "VALUES(?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, newUser.getName());
                stm.setString(2, newUser.getPhone());
                stm.setString(3, newUser.getPassword());
                stm.setString(4, newUser.getAddress());
                stm.setInt(5, newUser.getStatusID());
                stm.setInt(6, newUser.getRoleID());
                stm.setDate(7, newUser.getCreateDate());
                stm.setString(8, newUser.getEmail());
                flag = stm.executeUpdate() > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return flag;
    }
   public boolean checkDuplicateEmail(String email) throws SQLException, NamingException, ClassNotFoundException {
    // toàn có trùng đâu  mail ko trùng 
        boolean flag = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select name, phone, password, address, statusID, roleID, createDate, email From [ResourceSharing1].[dbo].[Users] "
                        + "where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    flag = true;
                }
            }
        } finally {
             if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return flag;
    }
   public boolean updateUser(String email) throws SQLException, NamingException, ClassNotFoundException {
        boolean flag = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update USERS set statusID = 2"
                        + "where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                flag = stm.executeUpdate() > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return flag;
    }

}
