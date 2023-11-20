package com.poy.service.Impl;

import com.poly.model.Product;
import com.poy.service.DBConnect;
import com.poy.service.ProductService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public int create(Product product) {
        int i = -1;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO [dbo].[Products] ([Name], [Description], [Activated], [Thumnail],\n"
                + "[Total_Quantity], [Frequency_Range_id], [Total_Power_Id], [Brand_Id], [Type_Product_Id], [Categorie_Id], \n"
                + "[Design_Id], [Updated_Time], [Account_Id])\n"
                + "VALUES \n"
                + "    (?, ?, 1, ?, 100, (SELECT [Id] FROM [dbo].[Frequency_Ranges] WHERE [Name] = ?), \n"
                + "    (SELECT [Id] FROM [dbo].[Total_Powers] WHERE [Name] = ?), \n"
                + "    (SELECT [Id] FROM [dbo].[Brands] WHERE [Name] = ?),\n"
                + "    (SELECT [Id] FROM [dbo].[Type_Products] WHERE [Name] = ?),\n"
                + "    (SELECT [Id] FROM [dbo].[Categories] WHERE [Name] = ?),\n"
                + "    (SELECT [Id] FROM [dbo].[Designs] WHERE [Name] = ?),\n"
                + "    GETDATE(), 3)");
        try {
            con = DBConnect.getConnection();
            ps = con.prepareCall(query.toString());
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getThumnail());
            ps.setString(4, product.getFrequencyRangeName());
            ps.setString(5, product.getTotalPowerName());
            ps.setString(6, product.getBrandName());
            ps.setString(7, product.getTypeProductName());
            ps.setString(8, product.getCategorieName());
            ps.setString(9, product.getDesignName());

            i = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi Nhập Dữ Liệu: \n" + e.getMessage());

        }

        return i;
    }

    @Override
    public List<Product> findAll(Product p, int pageNum) {
        ArrayList<Product> listProducts = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT Product_Id ,sp.Name,sp.Description,sp.Activated,sp.Thumnail,sp.Total_Quantity,frequency.Name as frequencyName,brand.Name as brandName,tp.Name as tpName,typeProducts.Name as typeProductsName,categories.Name as categoriesName,designs.Name as designsName,sp.Updated_Time ");
            query.append("FROM Products as sp JOIN Frequency_Ranges as frequency on sp.Frequency_Range_id = frequency.Id ");
            query.append("JOIN Brands as brand on sp.Brand_Id = brand.Id ");
            query.append("JOIN Total_Powers as tp on sp.Total_Power_Id = tp.Id ");
            query.append("JOIN Categories as categories on sp.Categorie_Id = categories.Id ");
            query.append("JOIN Designs as designs on sp.Design_Id = designs.Id ");
            query.append("JOIN Type_Products as typeProducts on sp.Type_Product_Id = typeProducts.Id ");
            query.append("WHERE sp.Name like CONCAT('%', ? ,'%') ");
            query.append("AND brand.name like CONCAT('%', ? ,'%') ");
            query.append("AND tp.name like CONCAT('%', ? ,'%') ");
            query.append("AND frequency.name like CONCAT('%', ? ,'%') ");
            query.append("AND designs.name Like CONCAT('%', ? ,'%') ");
            query.append("AND typeProducts.name Like CONCAT('%', ? ,'%') ");
            query.append("AND categories.name Like CONCAT('%', ? ,'%') ");
            query.append("AND sp.Activated = ? ");
            query.append("ORDER BY Product_Id OFFSET ? ROWS FETCH FIRST 5 ROWS ONLY");

            con = DBConnect.getConnection();
            ps = con.prepareStatement(query.toString());
            ps.setString(1, p.getName());
            ps.setString(2, p.getBrandName());
            ps.setString(3, p.getTotalPowerName());
            ps.setString(4, p.getFrequencyRangeName());
            ps.setString(5, p.getDesignName());
            ps.setString(6, p.getTypeProductName());
            ps.setString(7, p.getCategorieName());
            ps.setInt(8, p.getActivated() ? 1 : 0);
            ps.setInt(9, pageNum * 5);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product pr = new Product();
                pr.setId(rs.getInt("Product_Id"));
                pr.setBrandName(rs.getString("brandName"));
                pr.setTotalPowerName(rs.getString("tpName"));
                pr.setTypeProductName(rs.getString("typeProductsName"));
                pr.setFrequencyRangeName(rs.getString("frequencyName"));
                pr.setCategorieName(rs.getString("categoriesName"));
                pr.setDesignName(rs.getString("designsName"));
                pr.setName(rs.getString("Name"));

                pr.setActivated(rs.getBoolean("Activated"));
                pr.setDescription(rs.getString("Description"));
                pr.setThumnail(rs.getString("Thumnail"));
                pr.setUpdateTime(rs.getDate("Updated_Time"));
                listProducts.add(pr);
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lẫy Dữ Liệu Thành Công");
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu sản phẩm: \n" + e.getMessage());
        }
        return listProducts;
    }

    @Override
    public int getTotalPage(Product p) {
        int total = -1;
        StringBuilder query = new StringBuilder();
        query.append("SELECT Count(*) ");
        query.append("FROM Products as sp JOIN Frequency_Ranges as frequency on sp.Frequency_Range_id = frequency.Id ");
        query.append("JOIN Brands as brand on sp.Brand_Id = brand.Id ");
        query.append("JOIN Total_Powers as tp on sp.Total_Power_Id = tp.Id ");
        query.append("JOIN Categories as categories on sp.Categorie_Id = categories.Id ");
        query.append("JOIN Designs as designs on sp.Design_Id = designs.Id ");
        query.append("JOIN Type_Products as typeProducts on sp.Type_Product_Id = typeProducts.Id ");
        query.append("WHERE sp.Name like CONCAT('%', ? ,'%') ");
        query.append("AND brand.name like CONCAT('%', ? ,'%') ");
        query.append("AND tp.name like CONCAT('%', ? ,'%') ");
        query.append("AND frequency.name like CONCAT('%', ? ,'%') ");
        query.append("AND designs.name Like CONCAT('%', ? ,'%') ");
        query.append("AND typeProducts.name Like CONCAT('%', ? ,'%') ");
        query.append("AND categories.name Like CONCAT('%', ? ,'%') ");
        query.append("AND sp.Activated = ? ");
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(query.toString());
            ps.setString(1, p.getName());
            ps.setString(2, p.getBrandName());
            ps.setString(3, p.getTotalPowerName());
            ps.setString(4, p.getFrequencyRangeName());
            ps.setString(5, p.getDesignName());
            ps.setString(6, p.getTypeProductName());
            ps.setString(7, p.getCategorieName());
            ps.setInt(8, p.getActivated() ? 1 : 0);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
            con.close();
            ps.close();
            System.out.println("lấy số luowngjthanhf cong");
            return total;
        } catch (SQLException e) {
            System.out.println("Lỗi Nhập Dữ Liệu sản phẩm: \n" + e.getMessage());

        }
        return 0;
    }

    public static void main(String[] args) {
        ProductServiceImpl p = new ProductServiceImpl();
        
        System.out.println(p.findByName("ưaawdw"));

    }

    @Override
    public int updateProduct(Product product) {
        int i = -1;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE [dbo].[Products]\n"
                + "SET [Name] = ?, [Description] = ?, [Activated] = ?, [Thumnail] = ?,\n"
                + " [Frequency_Range_id] = (SELECT [Id] FROM [dbo].[Frequency_Ranges] WHERE [Name] = ?),\n"
                + "[Total_Power_Id] = (SELECT [Id] FROM [dbo].[Total_Powers] WHERE [Name] = ?),\n"
                + "[Brand_Id] = (SELECT [Id] FROM [dbo].[Brands] WHERE [Name] = ?),\n"
                + "[Type_Product_Id] = (SELECT [Id] FROM [dbo].[Type_Products] WHERE [Name] = ?),\n"
                + "[Categorie_Id] = (SELECT [Id] FROM [dbo].[Categories] WHERE [Name] = ?),\n"
                + "[Design_Id] = (SELECT [Id] FROM [dbo].[Designs] WHERE [Name] = ?),\n"
                + "[Updated_Time] = GETDATE()\n"
                + "WHERE [Product_Id] = ?");

        try {
            con = DBConnect.getConnection();
            ps = con.prepareCall(query.toString());
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getActivated() ? 1 : 0);
            ps.setString(4, product.getThumnail());
            ps.setString(5, product.getFrequencyRangeName());
            ps.setString(6, product.getTotalPowerName());
            ps.setString(7, product.getBrandName());
            ps.setString(8, product.getTypeProductName());
            ps.setString(9, product.getCategorieName());
            ps.setString(10, product.getDesignName());
            ps.setInt(11, product.getId());

            i = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi Cập Nhật Dữ Liệu: \n" + e.getMessage());
        }

        return i;
    }

    @Override
    public Product findById(int id) {
       
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT Product_Id ,sp.Name,sp.Description,sp.Activated,sp.Thumnail,sp.Total_Quantity,frequency.Name as frequencyName,brand.Name as brandName,tp.Name as tpName,typeProducts.Name as typeProductsName,categories.Name as categoriesName,designs.Name as designsName,sp.Updated_Time ");
            query.append("FROM Products as sp JOIN Frequency_Ranges as frequency on sp.Frequency_Range_id = frequency.Id ");
            query.append("JOIN Brands as brand on sp.Brand_Id = brand.Id ");
            query.append("JOIN Total_Powers as tp on sp.Total_Power_Id = tp.Id ");
            query.append("JOIN Categories as categories on sp.Categorie_Id = categories.Id ");
            query.append("JOIN Designs as designs on sp.Design_Id = designs.Id ");
            query.append("JOIN Type_Products as typeProducts on sp.Type_Product_Id = typeProducts.Id ");
            query.append("WHERE sp.Product_id = ? ");
            con = DBConnect.getConnection();
            ps = con.prepareStatement(query.toString());
           ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product pr = new Product();
                pr.setId(rs.getInt("Product_Id"));
                pr.setBrandName(rs.getString("brandName"));
                pr.setTotalPowerName(rs.getString("tpName"));
                pr.setTypeProductName(rs.getString("typeProductsName"));
                pr.setFrequencyRangeName(rs.getString("frequencyName"));
                pr.setCategorieName(rs.getString("categoriesName"));
                pr.setDesignName(rs.getString("designsName"));
                pr.setName(rs.getString("Name"));

                pr.setActivated(rs.getBoolean("Activated"));
                pr.setDescription(rs.getString("Description"));
                pr.setThumnail(rs.getString("Thumnail"));
                pr.setUpdateTime(rs.getDate("Updated_Time"));
               return pr;
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lẫy Dữ Liệu Thành Công");
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu sản phẩm: \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public Product findByName(String name) {
  try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT Product_Id ,sp.Name,sp.Description,sp.Activated,sp.Thumnail,sp.Total_Quantity,frequency.Name as frequencyName,brand.Name as brandName,tp.Name as tpName,typeProducts.Name as typeProductsName,categories.Name as categoriesName,designs.Name as designsName,sp.Updated_Time ");
            query.append("FROM Products as sp JOIN Frequency_Ranges as frequency on sp.Frequency_Range_id = frequency.Id ");
            query.append("JOIN Brands as brand on sp.Brand_Id = brand.Id ");
            query.append("JOIN Total_Powers as tp on sp.Total_Power_Id = tp.Id ");
            query.append("JOIN Categories as categories on sp.Categorie_Id = categories.Id ");
            query.append("JOIN Designs as designs on sp.Design_Id = designs.Id ");
            query.append("JOIN Type_Products as typeProducts on sp.Type_Product_Id = typeProducts.Id ");
            query.append("WHERE sp.name = ? ");
            con = DBConnect.getConnection();
            ps = con.prepareStatement(query.toString());
           ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product pr = new Product();
                pr.setId(rs.getInt("Product_Id"));
                pr.setBrandName(rs.getString("brandName"));
                pr.setTotalPowerName(rs.getString("tpName"));
                pr.setTypeProductName(rs.getString("typeProductsName"));
                pr.setFrequencyRangeName(rs.getString("frequencyName"));
                pr.setCategorieName(rs.getString("categoriesName"));
                pr.setDesignName(rs.getString("designsName"));
                pr.setName(rs.getString("Name"));

                pr.setActivated(rs.getBoolean("Activated"));
                pr.setDescription(rs.getString("Description"));
                pr.setThumnail(rs.getString("Thumnail"));
                pr.setUpdateTime(rs.getDate("Updated_Time"));
               return pr;
            }
            rs.close();
            ps.close();
            con.close();
            System.out.println("Lẫy Dữ Liệu Thành Công");
            return null;
        } catch (SQLException e) {
            System.out.println("Lỗi Lấy Dữ Liệu sản phẩm: \n" + e.getMessage());
        }
        return null;    
    }
    
    
}
