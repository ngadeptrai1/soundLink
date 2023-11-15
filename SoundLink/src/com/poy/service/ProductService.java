/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poy.service;

import com.poly.model.Product;
import java.util.List;

/**
 *
 * @author dnha1
 */
public interface ProductService {
        
    List<Product> findAll(Product p,int pageNum);
    Product findById(int id);
    int create(Product p);
    int getTotalPage( Product p);
    int updateProduct(Product p);
}
