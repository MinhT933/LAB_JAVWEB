/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dao;

import MinhT.Connect.DBUtils;
import MinhT.MyConstants;
import MinhT.dto.RequestDTO;
import MinhT.dto.SourceDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author MinhT
 */
public class RequestDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<RequestDTO> loadReqsData() throws SQLException {
        List<RequestDTO> listReqs = new ArrayList<>();

        String sql = "  SELECT[requestID]\n"
                + "      ,[dateBook]\n"
                + "      ,[statusReqID]\n"
                + "      ,[email]\n"
                + "      ,[productID]\n"
                + "  FROM [ResourceSharing1].[dbo].[Requests]";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listReqs.add(new RequestDTO(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return listReqs;
    }

    public SourceDTO getSourceID(String id) throws SQLException {

        String sql = " SELECT [productID]\n"
                + "                ,[productName]\n"
                + "                ,[color]\n"
                + "                ,[categoryName]\n"
                + "                ,[quanlity]\n"
                + "                ,[createDate]\n"
                + "                FROM [ResourceSharing1].[dbo].[Resources] inner join [ResourceSharing1].[dbo].[Categories]\n"
                + "                on [ResourceSharing1].[dbo].[Resources].categoryID = [ResourceSharing1].[dbo].[Categories].[categoryID]\n"
                + "	           where  [productID] = ?";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new SourceDTO(rs.getString("productID"),
                        rs.getString("productName"),
                        rs.getString("color"),
                        rs.getString("quanlity"),
                        rs.getString("categoryName"),
                        rs.getString("createDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;

    }

    public boolean updateStatusRequest(int requestID, int statusID) throws NamingException, SQLException {
        boolean flag = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "update Requests set statusReqID = ? "
                        + "where requestID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, statusID);
                ps.setInt(2, requestID);
                flag = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
        return flag;
    }

    public boolean bookingResource(RequestDTO requestBooking) throws NamingException, SQLException, ClassNotFoundException {
        boolean isBook = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "  insert  into [ResourceSharing1].[dbo].[Requests](dateBook, statusReqID, email, productID) values(?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setDate(1, requestBooking.getDateBook());
                ps.setInt(2, requestBooking.getStatusReqID());
                ps.setString(3, requestBooking.getEmail());
                ps.setString(4, requestBooking.getProductID());
                isBook = ps.executeUpdate() > 0;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return isBook;
    }

    public void deleteRequestByid(String id) throws SQLException {
        String sql = "delete from [ResourceSharing1].[dbo].[Requests] where [requestID] = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}
