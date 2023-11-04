package com.poly.DAO;

import com.poly.Interface.Interface_TypeProduct;
import com.poly.model.Type_Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_TypeProduct implements Interface_TypeProduct {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Type_Product> list_TypeProduct() {
        ArrayList<Type_Product> listTypeProduct = new ArrayList<>();
        try {
            String sql = "SELECT Type_Product_Id, Name, Description, Update_Day, Quantity FROM TYPE_PRODUCT";
            con = com.poly.DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listTypeProduct.add(new Type_Product(
                        rs.getInt("Type_Product_Id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Update_Day"),
                        rs.getInt("Quantity")));
                System.out.println(rs.getString("Type_Product_Id"));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lấy Dữ Liệu Thành Công");
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return listTypeProduct;
    }

    @Override
    public int add_TypeProduct(Type_Product o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "INSERT INTO TYPE_PRODUCT (Name, Description, Update_Day, Quantity) VALUES (?,?,?,?)";
            con = com.poly.DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setString(3, o.getUpdate_Day());
            ps.setInt(4, o.getQuantity());
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
    public boolean remove_TypeProduct(int ID) {
        try {
            String sql = "DELETE FROM TYPE_PRODUCT WHERE Type_Product_Id = " + ID;

            con = com.poly.DBConnect.DBConnect.getConnection();
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
    public int update_TypeProduct(Type_Product o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "UPDATE TYPE_PRODUCT SET Name=?, Description=?, Update_Day=?, Quantity=? WHERE Type_Product_Id = ?";
            con = com.poly.DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getName());
            ps.setString(2, o.getDescription());
            ps.setString(3, o.getUpdate_Day());
            ps.setInt(4, o.getQuantity());
            ps.setInt(5, o.getType_Product_Id());

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

    private int checkInput(Type_Product o) {
        if (o.getName().isEmpty()) {
            return 1;
        }
        if (o.getUpdate_Day().length() != 8) {
            return 2;
        }
        if (o.getDescription().isEmpty()) {
            return 3;
        }
        return 0;
    }
}
