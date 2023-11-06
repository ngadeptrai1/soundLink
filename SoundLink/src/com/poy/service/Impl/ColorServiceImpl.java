package com.poly.DAO;

import com.poly.Interface.Interface_Color;
import com.poly.model.Color;
import com.poly.DBConnect.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Color implements Interface_Color {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Color> list_Color() {
        ArrayList<Color> listColor = new ArrayList<>();
        try {
            String sql = "SELECT Color_Id, Color, Status FROM COLOR";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Color color = new Color(
                        rs.getInt("Color_Id"),
                        rs.getString("Color"),
                        rs.getString("Status")
                );
                listColor.add(color);
                System.out.println(rs.getString("Color_Id"));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lấy Dữ Liệu Thành Công");
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return listColor;
    }

    @Override
    public int add_Color(Color o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "INSERT INTO COLOR (Color, Status) VALUES (?, ?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getColor());
            ps.setString(2, o.getStatus());
            ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Thêm Dữ Liệu Thành Công");
            return 0;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());
            return 4;
        }
    }

    @Override
    public boolean remove_Color(int Color_Id) {
        try {
            String sql = "DELETE FROM COLOR WHERE Color_Id = " + Color_Id;

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.executeUpdate();

            ps.close();
            con.close();
            System.out.println("Xóa Thành Công");
            return true;
        } catch (SQLException e) {
            System.out.println("Xóa Thất Bại: \n" + e.getMessage());
            return false;
        }
    }

    @Override
    public int update_Color(Color o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "UPDATE COLOR SET Color=?, Status=? WHERE Color_Id = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getColor());
            ps.setString(2, o.getStatus());
            ps.setInt(3, o.getColor_Id());

            ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Sửa Dữ Liệu Thành Công");
            return 0;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());
            return 4;
        }
    }

    private int checkInput(Color o) {
        if (o.getColor().isEmpty()) {
            return 1;
        }
        return 0;
    }
}
