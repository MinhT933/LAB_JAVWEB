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
import static java.util.Collections.list;
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
        String sql = "SELECT top (100) [productID]\n"
                + ",[productName]\n"
                + ",[color]\n"
                + ",[categoryName]\n"
                + ",[quanlity]\n"
                + ",[createDate]\n"
                + " FROM [ResourceSharing1].[dbo].[Resources] inner join [ResourceSharing1].[dbo].[Categories] \n"
                + " on [ResourceSharing1].[dbo].[Resources].categoryID = [ResourceSharing1].[dbo].[Categories].[categoryID]";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listSource.add(new SourceDTO(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return listSource;
    }

    public List<SourceDTO> searchByName(String txtSearch, int index) {
        List<SourceDTO> ListS = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = " select rowid,[productID]\n"
                + "           ,[productName]\n"
                + "           ,[color]\n"
                + "           ,[categoryName]\n"
                + "           ,[quanlity]\n"
                + "	      ,[createDate]\n"
                + "           from (SELECT ROW_NUMBER () over (order by [productID] asc) as rowid,[productID]\n"
                + "           ,[productName]\n"
                + "           ,[color]\n"
                + "           ,[categoryName]\n"
                + "           ,[quanlity]\n"
                + "	      ,[createDate]\n"
                + "            FROM [ResourceSharing1].[dbo].[Resources] inner join [ResourceSharing1].[dbo].[Categories]\n"
                + "            ON [ResourceSharing1].[dbo].[Resources].categoryID = [ResourceSharing1].[dbo].[Categories].categoryID\n"
                + "            WHERE [categoryName] like ? or [productName] like ? or [createDate] like ? ) as x where rowid between ?*3-2 and ?*3";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            ps.setString(3, "%" + txtSearch + "%");
            ps.setInt(4, index);
            ps.setInt(5, index);
            rs = ps.executeQuery();

            while (rs.next()) {
                ListS.add(new SourceDTO(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));

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
                + "                 FROM [ResourceSharing1].[dbo].[Resources] inner join [ResourceSharing1].[dbo].[Categories]\n"
                + "                 ON [ResourceSharing1].[dbo].[Resources].categoryID = [ResourceSharing1].[dbo].[Categories].categoryID\n"
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

    public static void main(String[] args) {
        String txt = "pro";
        SourceDAO dao = new SourceDAO();
        List<SourceDTO> search = dao.searchByName(txt, 2);
        int count = dao.count(txt);
        System.out.println(search);
        System.out.println(count);

    }

 
    
  
}
