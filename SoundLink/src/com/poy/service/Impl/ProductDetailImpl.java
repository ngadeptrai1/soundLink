/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poy.service.Impl;

import com.poly.model.ProductDetails;
import com.poy.service.DBConnect;
import com.poy.service.ProductDeatailsService;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dnha1
 */
public class ProductDetailImpl implements ProductDeatailsService {

    Connection conn;
    PreparedStatement stmt;
    Statement st;
    ResultSet rs;

    @Override
    public int createProductDetail(ProductDetails productDetails) {
        int rowsAffected = 0;
        String query = "INSERT INTO PRODUCT_DETAILS (PRODUCT_ID, COLOR_ID, Thumnail, QUANTITY, ACTIVATED, PRODUCT_PRICE) VALUES (?, (SELECT ID FROM COLORS WHERE NAME = ?), ?, ?, ?, ?)";
        try {
            conn = DBConnect.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, productDetails.getProductId());
            stmt.setString(2, productDetails.getColorName());
            stmt.setString(3, productDetails.getThumnail());
            stmt.setInt(4, productDetails.getQuantity());
            stmt.setInt(5, productDetails.getActivated() ? 1 : 0);
            stmt.setDouble(6, productDetails.getPrice());
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected;
    }

    @Override
    public int updateProductDetail(ProductDetails productDetails) {
        int rowsAffected = 0;
        String query = "UPDATE PRODUCT_DETAILS SET COLOR_ID = (SELECT ID FROM COLORS WHERE NAME = ?), Thumnail = ?, QUANTITY = ?, ACTIVATED = ?, PRODUCT_PRICE = ? WHERE ID = ?";
        try {
            conn = DBConnect.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, productDetails.getColorName());
            stmt.setString(2, productDetails.getThumnail());
            stmt.setInt(3, productDetails.getQuantity());
            stmt.setInt(4, productDetails.getActivated() ? 1 : 0);
            stmt.setDouble(5, productDetails.getPrice());
            stmt.setInt(6, productDetails.getId());
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected;
    }

    @Override
    public int deleteProductDetails(String ID) {
        int rowsAffected = 0;
        String query = "DELETE PRODUCT_DETAILS WHERE ID = ?";
        try {
            conn = DBConnect.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, ID);
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected;

    }

    @Override
    public List<ProductDetails> getALlByProduct(int productId, ProductDetails productDetails, int pageNumber) {
        List<ProductDetails> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT PRDT.ID,CL.NAME AS ColorName,PRDT.Thumnail AS Thumnail ,PRDT.PRODUCT_ID,PRDT.QUANTITY AS Quantity,PRDT.ACTIVATED AS Activated,PRDT.PRODUCT_PRICE AS Price FROM PRODUCT_DETAILS PRDT");
        query.append(" JOIN COLORS CL ON PRDT.COLOR_ID = CL.ID WHERE PRDT.Product_id = ? ");
        query.append("AND CL.name LIKE CONCAT('%', ?,'%')");
        query.append("AND PRDT.Activated = ?");
        query.append(" ORDER BY PRDT.ID OFFSET ? ROWS FETCH FIRST 5 ROWS ONLY");
        try {
            conn = DBConnect.getConnection();
            stmt = conn.prepareStatement(query.toString());
            stmt.setInt(1, productId);
            stmt.setString(2, productDetails.getColorName());
            stmt.setInt(3, productDetails.getActivated() ? 1 : 0);
            stmt.setInt(4, pageNumber * 5);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductDetails prdt = new ProductDetails();
                prdt.setId(rs.getInt("ID"));
                prdt.setColorName(rs.getString("ColorName"));
                prdt.setProductId(productId);
                prdt.setThumnail(rs.getString("Thumnail"));
                prdt.setActivated(rs.getBoolean("Activated"));
                prdt.setPrice(rs.getLong("Price"));
                prdt.setQuantity(rs.getInt("Quantity"));
                list.add(prdt);
            }
            conn.close();
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotalPage(int productId, ProductDetails productDetails) {
        int numberRecord = -1;
        StringBuilder query = new StringBuilder();
        query.append(" SELECT COUNT(*) FROM PRODUCT_DETAILS PRDT");
        query.append(" JOIN COLORS CL ON PRDT.COLOR_ID = CL.ID WHERE PRDT.Product_id = ? ");
        query.append("AND CL.name = ? ");
        query.append("AND PRDT.Activated = ?");

        try {
            conn = DBConnect.getConnection();
            stmt = conn.prepareStatement(query.toString());
            stmt.setInt(1, productId);
            stmt.setString(2, productDetails.getColorName());
            stmt.setInt(3, productDetails.getActivated() ? 1 : 0);
            rs = stmt.executeQuery();
            while (rs.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberRecord;
    }

    public static void main(String[] args) {
        ProductDetails pr = new ProductDetails();
        pr.setColorName("");
        pr.setActivated(Boolean.TRUE);
        ProductDetailImpl prd = new ProductDetailImpl();
        System.out.println(prd.getALlByProduct(2, pr, 0));
    }

}
