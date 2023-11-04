package com.poly.Interface;

import java.util.List;
import com.poly.model.Category;

public interface Interface_Category {

    public List<Category> list_Category();

    public int add_Category(Category o);

    public boolean remove_Category(int Categories_Id);

    public int update_Category(Category o);
}
