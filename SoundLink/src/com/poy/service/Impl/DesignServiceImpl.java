package com.poy.service.Impl;

import com.poy.service.DBConnect;
import com.poly.model.Design;
import com.poy.service.CRUDService;
import com.poy.service.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.poy.service.DesignService;

public class DesignServiceImpl implements CRUDService<Design> {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Design> findAll() {
        ArrayList<Design> listDesign = new ArrayList<>();
        try {
            String sql = "SELECT Design_Id, Name, Description, Activated FROM DESIGNS";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Design design = new Design(
                        rs.getInt("Design_Id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getBoolean("Activated")
                );
                listDesign.add(design);
                System.out.println(rs.getString("Design_Id"));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lấy Dữ Liệu Thành Công");
        } catch (SQLException ex) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + ex.getMessage());
        }
        return listDesign;
    }

    @Override
    public int create(Design o){
        int ind = 0;
                try {
            String sql = "INSERT INTO DESIGN (Name, Description, Activated) VALUES (?, ?, ?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setInt(3, o.isActivated() ? 1:0);
          ind =  ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Thêm Dữ Liệu Thành Công");
            return ind;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());
        
        }  
                return ind;
    }

    @Override
    public int remove(String id) {
        int ind = 0;
        try {
            String sql = "DELETE FROM DESIGN WHERE Design_Id = ? ";

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
           ind = ps.executeUpdate();

            ps.close();
            con.close();
            System.out.println("Xóa Thành Công");
            return ind;
        } catch (SQLException e) {
            System.out.println("Xóa Thất Bại: \n" + e.getMessage());
           
        }
        return ind;
    }

    @Override
    public int  update(Design o) {
        int ind =0;
        try {
            String sql = "UPDATE DESIGN SET Name=?, Description=?, Activated=? WHERE Design_Id = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setInt(3, o.isActivated() ? 1: 0);
            ps.setInt(4, o.getId());

           ind= ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Sửa Dữ Liệu Thành Công");
            return ind;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());
            return ind;
        }
    }

   

}
