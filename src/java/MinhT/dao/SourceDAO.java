/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dao;

import MinhT.Connect.DBUtils;
import MinhT.dto.SourceDTO;
import MinhT.dto.UserDTO;
import com.sun.org.apache.xerces.internal.xs.PSVIProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MinhT
 */
public class SourceDAO {

    public List<SourceDTO> loadData() {
        List<SourceDTO> listSource = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT[productID]\n"
                + "      ,[productName]\n"
                + "      ,[color]\n"
                + "      ,[categoryName]\n"
                + "      ,[quanlity]\n"
                + "  FROM [ResourceSharing].[dbo].[Resources] inner join [ResourceSharing].[dbo].[Categories] \n"
                + "  on [ResourceSharing].[dbo].[Resources].categoryID = [ResourceSharing].[dbo].[Categories].CateID";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listSource.add(new SourceDTO(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return listSource;
    }

    public List<SourceDTO> searchByName(String txtSearch) {
        List<SourceDTO> ListS = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = "SELECT[productID]\n"
                + "      ,[productName]\n"
                + "      ,[color]\n"
                + "      ,[categoryName]\n"
                + "      ,[quanlity]\n"
                + "FROM [ResourceSharing].[dbo].[Resources] inner join [ResourceSharing].[dbo].[Categories]\n"
                + "ON [ResourceSharing].[dbo].[Resources].categoryID = [ResourceSharing].[dbo].[Categories].CateID  \n"
                + "WHERE [categoryName] like ? or [productName] like ?";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                ListS.add(new SourceDTO(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));

            }
        } catch (Exception e) {
        }
        return ListS;
    }

    public int count(String txtSearch) {
        List<SourceDTO> ListS = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = " SELECT count ( [productID])\n"
                + "                 FROM [ResourceSharing].[dbo].[Resources] inner join [ResourceSharing].[dbo].[Categories]\n"
                + "                 ON [ResourceSharing].[dbo].[Resources].categoryID = [ResourceSharing].[dbo].[Categories].CateID\n"
                + "                 WHERE [categoryName] like ? or [productName] like ?";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
    }
//    public static void main(String[] args) {
//        String txt = "pr";
//        SourceDAO dao = new SourceDAO();
//        int count = dao.count(txt);
//        System.out.println(count);
//
//    }
}
