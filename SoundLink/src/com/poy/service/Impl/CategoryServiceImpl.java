package com.poy.service.Impl;

import com.poy.service.DBConnect;
import com.poly.model.Category;
import com.poy.service.CRUDService;
import com.poy.service.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.poy.service.CategoryService;

public class CategoryServiceImpl implements CRUDService<Category> {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Category> findAll() {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            String sql = "SELECT Categories_Id, Name, Describe, Created_Time FROM CATEGORY";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCategory.add(new Category(
                        rs.getInt("Categories_Id"),
                        rs.getString("Name"),
                        rs.getString("Describe"),
                        rs.getDate("Created_Time")));
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
    public int create(Category o) {
        int ind = -1;
        try {
            String sql = "INSERT INTO CATEGORY (Name, Describe,Created_Time) VALUES (?,?,?,?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescribe());
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ind = ps.executeUpdate();

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
        int ind = -1;
        try {
            String sql = "DELETE FROM CATEGORY WHERE Categories_Id = ?";

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ind = ps.executeUpdate();

            ps.close();
            con.close();
            System.out.println("Xóa Thành Công");
            return ind;
        } catch (SQLException ex) {
            System.out.println("Xóa Thất Bại: \n" + ex.getMessage());

        }
        return ind;
    }

    @Override
    public int update(Category o) {
        int ind = 0;
        try {
            String sql = "UPDATE CATEGORY SET Name=?, Describe=? WHERE Categories_Id = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, o.getName());
            ps.setString(2, o.getDescribe());
            ps.setInt(3, o.getId());
            ind = ps.executeUpdate();

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
