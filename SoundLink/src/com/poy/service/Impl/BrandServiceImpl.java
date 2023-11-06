package com.poy.service.Impl;

import java.util.List;
import com.poly.model.Brand;
import com.poy.service.CRUDService;
import java.sql.*;
import java.util.ArrayList;
import com.poy.service.BrandService;

public class BrandServiceImpl implements CRUDService<Brand> {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Brand> findAll() {
        ArrayList<Brand> listBrand = new ArrayList<>();
        try {
            String sql = "SELECT Brand_Id,Name,Date_Created,Description,Activated FROM BRANDS ORDER BY Brand_Id ASC";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listBrand.add(new Brand(rs.getInt("Brand_Id"),rs.getString("Name"),
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
        return listBrand;
    }

    @Override
    public int create(Brand o) {
        int ind = -1;
        try {
            String sql = "INSERT INTO Brands\n"
                    + "    (Name, Date_Created, Description, Activated)\n"
                    + "VALUES(?,?,?,?)";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
           
            ps.setString(1, o.getBrandName());
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(3, o.getDescription());
            ps.setInt(4, o.isActivated()?1:0);
                ind = ps.executeUpdate();

            con.close();
            ps.close();

            System.out.println("Thêm Dữ Liệu Thành Công");
            
             return  ind;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());
            return ind;
        }
    }

    @Override
    public int remove(String id) {
        int ind = -1;
        try {
            String sql = "DELETE Brands WHERE brand_Id= ? ";
            
            con = com.poy.service.DBConnect.getConnection();
            
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
         ind =   ps.executeUpdate();

            ps.close();
            con.close();
            System.out.println("Xóa Thành Công");
            return ind;
        } catch (SQLException e) {
            System.out.println("Xóa Thất Bại: \n" + e.getMessage());
        
        }
        return ind;
    }

    //return 0: Thêm Thành Công
    //1:trống tên.
    //2:thiếu ngày
    //3:thiếu Description
    //4:Lỗi SQL
    @Override
    public int update(Brand o) {
       int ind = -1;
        try {
            String sql = "UPDATE Brands \n"
                    + " SET Name=?,Date_Created=?,Description=?,Activated=? "
                    + "WHERE brand_Id=?";
            con = com.poy.service.DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, o.getBrandName());
             ps.setDate(2, new java.sql.Date(o.getDateCreated().getTime()));
            ps.setString(3, o.getDescription());
            ps.setBoolean(4, o.isActivated());
            ps.setInt(5, o.getId());

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
