package com.poy.service.Impl;

import com.poly.model.Category;
import com.poy.service.CRUDService;
import com.poy.service.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CRUDService<Category> {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Category> findAll(int pageNums, String text) {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            String sql = "SELECT Id,Name,Date_Created,Description,Activated FROM Categories WHERE NAME LIKE ? ORDER BY ID OFFSET ? ROWS FETCH FIRST 5 ROWS ONLY";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ps.setInt(2, pageNums * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCategory.add(new Category(rs.getInt("Id"), rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDate("Date_Created"),
                        rs.getBoolean("Activated")));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lẫy Dữ Liệu Thành Công");
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return listCategory;
    }

    @Override
    public int create(Object[] o) {
        int ind = -1;
        try {
            String sql = "INSERT INTO Categorys\n"
                    + "    (Name, Date_Created, Description, Activated)\n"
                    + "VALUES(?,?,?,?)";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o[0].toString());
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(3, o[1].toString());
            ps.setInt(4, (boolean) o[2] ? 1 : 0);
            ind = ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Thêm Dữ Liệu Thành Công");

            return ind;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());
            return ind;
        }
    }

    @Override
    public int remove(String id) {
        int ind = -1;
        try {
            String sql = "DELETE Categorys WHERE Id= ? ";

            con = com.poy.service.DBConnect.getConnection();

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
    public int update(Object[] o) {
        int ind = -1;
        try {
            String sql = "UPDATE Categorys SET Name=?,Description=?,Activated=? WHERE Id=?";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, o[1].toString());
            ps.setString(2, o[2].toString());
            ps.setString(3, o[4].toString());
            ps.setInt(4, (int) o[0]);
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

    @Override
    public int getTotalPage(String text) {
        int total = -1;
        try {
            String sql = "SELECT COUNT(*) FROM Categories WHERE NAME LIKE ?";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
            con.close();
            ps.close();
            System.out.println("lấy số luowngjthanhf cong");
            return total;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());

        }
        return 0;
    }

   @Override
    public List<Object[]> findAllActivate() {

        ArrayList<Object[]> listCate = new ArrayList<>();
        try {
            String sql = "SELECT Id,Name,Date_Created,Description,Activated FROM Categories WHERE Activated = 1 ORDER BY Date_Created DESC";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category p = new Category(rs.getInt("Id"), rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDate("Date_Created"),
                        rs.getBoolean("Activated"));
                listCate.add(p.toObject());
            }
            rs.close();
            ps.close();
            con.close();
          
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return listCate;   
    
    }

    @Override
    public Category findByName(String name) {
 try {
            String sql = "SELECT Id,Name,Date_Created,Description,Activated FROM Categories WHERE NAME = ? ";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
               return new Category(rs.getInt("Id"), rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDate("Date_Created"),
                        rs.getBoolean("Activated"));
            } 
            rs.close();
            ps.close();
            con.close();
           return null;
           
          
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return null;    }
}
