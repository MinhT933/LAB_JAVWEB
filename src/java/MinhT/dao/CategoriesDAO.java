/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dao;

import MinhT.Connect.DBUtils;
import MinhT.dto.CategoriesDTO;
import MinhT.dto.SourceDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MinhT
 */
public class CategoriesDAO implements Serializable{

    public List<CategoriesDTO> loadCate() {
        List<CategoriesDTO> listCate = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT[categoryID]\n"
                + "      ,[categoryName]\n"
                + "  FROM [ResourceSharing].[dbo].[Categories]";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCate.add(new CategoriesDTO(rs.getString(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return listCate;
    }
}
