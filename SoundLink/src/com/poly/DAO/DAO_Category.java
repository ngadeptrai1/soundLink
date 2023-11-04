package com.poly.DAO;

import com.poly.Interface.Interface_Category;
import com.poly.model.Category;
import com.poly.DBConnect.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Category implements Interface_Category {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Category> list_Category() {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            String sql = "SELECT Categories_Id, Name, Describe, Updated_Time, Updated_Person_Id FROM CATEGORY";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCategory.add(new Category(
                        rs.getInt("Categories_Id"),
                        rs.getString("Name"),
                        rs.getString("Describe"),
                        rs.getString("Updated_Time"),
                        rs.getInt("Updated_Person_Id")));
                System.out.println(rs.getString("Categories_Id"));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lấy Dữ Liệu Thành Công");
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return listCategory;
    }

    @Override
    public int add_Category(Category o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "INSERT INTO CATEGORY (Name, Describe, Updated_Time, Updated_Person_Id) VALUES (?,?,?,?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescribe());
            ps.setString(3, o.getUpdated_Time());
            ps.setInt(4, o.getUpdated_Person_Id());
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
    public boolean remove_Category(int Categories_Id) {
        try {
            String sql = "DELETE FROM CATEGORY WHERE Categories_Id = " + Categories_Id;

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
    public int update_Category(Category o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "UPDATE CATEGORY SET Name=?, Describe=?, Updated_Time=?, Updated_Person_Id=? WHERE Categories_Id = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescribe());
            ps.setString(3, o.getUpdated_Time());
            ps.setInt(4, o.getUpdated_Person_Id());
            ps.setInt(5, o.getCategories_Id());

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

    private int checkInput(Category o) {
        if (o.getName().isEmpty()) {
            return 1;
        }
        if (o.getDescribe().isEmpty()) {
            return 2;
        }
        if (o.getUpdated_Time().length() != 8) {
            return 3;
        }
        return 0;
    }
}
