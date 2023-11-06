package com.poly.DAO;

import com.poly.Interface.Interface_Brands;
import java.util.List;
import com.poly.model.Brands;
import java.sql.*;
import java.util.ArrayList;

public class DAO_Brand implements Interface_Brands {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Brands> list_Brand() {
        ArrayList<Brands> listBrand = new ArrayList<>();
        try {
            String sql = "SELECT Brand_Id,Name,Date_Created,Description,Activated FROM BRANDS ORDER BY Brand_Id ASC";
            con = com.poly.DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listBrand.add(new Brands(rs.getInt("Brand_Id"),
                        rs.getString("Name"),
                        rs.getDate("Date_Created").toString(),
                        rs.getString("Description"),
                        rs.getBoolean("Activated")));
                System.out.println(rs.getString("Brand_Id"));
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lẫy Dữ Liệu Thành Công");
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu: \n" + e.getMessage());
        }
        return listBrand;
    }

    //return 0: Thêm Thành Công
    //1: trống tên.
    //2:thiếu ngày
    //3:thiếu Description
    //4:Lỗi SQL
    @Override
    public int add_Brand(Brands o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "INSERT INTO Brands\n"
                    + "    (Name, Date_Created, Description, Activated)\n"
                    + "VALUES(?,?,?,?)";
            con = com.poly.DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getBrand_Name());
            ps.setString(2, o.getDate_Created());
            ps.setString(3, o.getDescription());
            ps.setBoolean(4, o.isActivated());
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
    public boolean remove_Brand(int brand_Id) {
        try {
            String sql = "DELETE Brands WHERE brand_Id=" + brand_Id;

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

    //return 0: Thêm Thành Công
    //1:trống tên.
    //2:thiếu ngày
    //3:thiếu Description
    //4:Lỗi SQL
    @Override
    public int update_Brand(Brands o) {
        int checkInput = checkInput(o);
        if (checkInput != 0) {
            return checkInput;
        }
        try {
            String sql = "UPDATE Brands \n"
                    + " SET Name=?,Date_Created=?,Description=?,Activated=? "
                    + "WHERE brand_Id=?";
            con = com.poly.DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getBrand_Name());
            ps.setString(2, o.getDate_Created());
            ps.setString(3, o.getDescription());
            ps.setBoolean(4, o.isActivated());
            ps.setInt(5, o.getBrand_Id());

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

    private int checkInput(Brands o) {
        if (o.getBrand_Name().isEmpty()) {
            return 1;
        }
        if (o.getDate_Created().length() != 8) {
            return 2;
        }
        if (o.getDescription().isEmpty()) {
            return 3;
        }
        return 0;
    }

    public static void main(String[] args) {
        new DAO_Brand().list_Brand();
    }

}
