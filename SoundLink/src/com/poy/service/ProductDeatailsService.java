/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poy.service;

import com.poly.model.ProductDetails;
import java.util.List;

/**
 *
 * @author dnha1
 */
public interface ProductDeatailsService {
   List<ProductDetails> getALlByProduct(int productId , ProductDetails productDetails,int pageNumber) ;
   int createProductDetail(ProductDetails productDetails);
   int updateProductDetail(ProductDetails productDetails);
   int deleteProductDetails(String ID);
   
   int getTotalPage(int productId , ProductDetails productDetails );
   
   ProductDetails findByColor (int productId , String colorName);
 
}
