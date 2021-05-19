/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhT.dao;

import MinhT.Connect.DBUtils;
import MinhT.dto.RequestDTO;
import MinhT.dto.SourceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MinhT
 */
public class RequestDAO {

    public List<RequestDTO> loadReqsData() {
        List<RequestDTO> listReqs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT[requestID]\n"
                + "      ,[dateBook]\n"
                + "      ,[statusReqID]\n"
                + "      ,[email]\n"
                + "      ,[productID]\n"
                + "  FROM [ResourceSharing].[dbo].[Requests]";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listReqs.add(new RequestDTO(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return listReqs;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        RequestDAO dao = new RequestDAO();
        List<RequestDTO> list = dao.loadReqsData();
        System.out.println(list);

    }
}
