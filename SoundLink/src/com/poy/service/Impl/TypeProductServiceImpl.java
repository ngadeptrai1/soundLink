package com.poy.service.Impl;

import com.poly.model.TypeProduct;
import com.poy.service.CRUDService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.poy.service.TypeProductService;

public class TypeProductServiceImpl implements CRUDService<TypeProduct> {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<TypeProduct> findAll() {
        ArrayList<TypeProduct> listTypeProduct = new ArrayList<>();
        try {
            String sql = "SELECT Type_Product_Id, Name, Description, Created_Time FROM TYPE_PRODUCT";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listTypeProduct.add(new TypeProduct(
                        rs.getInt("Type_Product_Id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDate("Created_time")));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lấy Dữ Liệu Thành Công");
        } catch (SQLException ex) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + ex.getMessage());
        }
        return listTypeProduct;
    }

    @Override
    public int create(TypeProduct o) {
        int ind = 0;
        try {
            String sql = "INSERT INTO TYPE_PRODUCT (Name, Description, Created_Time) VALUES (?,?,?)";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
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
        int ind = 0;
        try {
            String sql = "DELETE FROM TYPE_PRODUCT WHERE Type_Product_Id = ? ";

            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(0, sql);
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
    public int update(TypeProduct o) {
        int ind = 0;
        try {
            String sql = "UPDATE TYPE_PRODUCT SET Name=?, Description=? WHERE Type_Product_Id = ?";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());

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
