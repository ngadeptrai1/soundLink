package com.poly.Interface;

import java.util.List;
import com.poly.model.Type_Product;

public interface Interface_TypeProduct {

    public List<Type_Product> list_TypeProduct();

    public int add_TypeProduct(Type_Product o);

    public boolean remove_TypeProduct(int ID);

    public int update_TypeProduct(Type_Product o);

}
