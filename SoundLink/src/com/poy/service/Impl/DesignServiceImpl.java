package com.poly.DAO;

import com.poly.Interface.Interface_Design;
import com.poly.model.Design;
import com.poly.DBConnect.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Design implements Interface_Design {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Design> list_Design() {
        ArrayList<Design> listDesign = new ArrayList<>();
        try {
            String sql = "SELECT Design_Id, Name, Description, Activated FROM DESIGN";
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
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return listDesign;
    }

    @Override
    public int add_Design(Design o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "INSERT INTO DESIGN (Name, Description, Activated) VALUES (?, ?, ?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setString(3, o.getActivated());
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
    public boolean remove_Design(int design_Id) {
        try {
            String sql = "DELETE FROM DESIGN WHERE Design_Id = " + design_Id;

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
    public int update_Design(Design o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "UPDATE DESIGN SET Name=?, Description=?, Activated=? WHERE Design_Id = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setString(3, o.getActivated());
            ps.setInt(4, o.getDesign_Id());

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

    private int checkInput(Design o) {
        if (o.getName().isEmpty()) {
            return 1;
        }
        if (o.getDescription().isEmpty()) {
            return 2;
        }
        return 0;
    }
}
