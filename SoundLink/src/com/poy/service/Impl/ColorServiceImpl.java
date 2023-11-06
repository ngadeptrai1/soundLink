package com.poy.service.Impl;

import com.poy.service.DBConnect;
import com.poly.model.Color;
import com.poy.service.CRUDService;
import com.poy.service.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.poy.service.ColorService;

public class ColorServiceImpl implements CRUDService<Color> {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public  List<Color> findAll() {
        ArrayList<Color> listColor = new ArrayList<>();
        try {
            String sql = "SELECT Color_Id, Color, Activated FROM COLOR";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Color color = new Color(
                        rs.getInt("Color_Id"),
                        rs.getString("Color"),
                        rs.getBoolean("Activated")
                );
                listColor.add(color);
                System.out.println(rs.getString("Color_Id"));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lấy Dữ Liệu Thành Công");
        } catch (SQLException ex) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + ex.getMessage());
        }
        return listColor;
    }

    @Override
    public int create(Color e) {
        int ind = -1;
        try {
            String sql = "INSERT INTO COLOR (Color, Activated) VALUES (?, ?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, e.getColor());
            ps.setInt(2, e.isActivated() ? 1:0);
            ind = ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Thêm Dữ Liệu Thành Công");
            return ind;
        } catch (SQLException ex) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + ex.getMessage());
           
        }
        return ind;
    }

    @Override
    public int remove(String id) {
        int ind = 0;
        try {
            String sql = "DELETE FROM COLOR WHERE Color_Id = ? ";

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
    public int update(Color o) {
        int ind = 0;
        try {
            String sql = "UPDATE COLOR SET Color=?, Activated = ? WHERE Color_Id = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getColor());
            ps.setInt(2, o.isActivated() ? 1 : 0 );
            ps.setInt(3, o.getId());

          ind =  ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Sửa Dữ Liệu Thành Công");
            return ind;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());
           
        } 
        return ind;
    }


}
