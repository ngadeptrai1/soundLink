package com.poly.Interface;

import java.util.List;
import com.poly.model.Brands;

public interface Interface_Brands {

    public List<Brands> list_Brand();

    public int add_Brand(Brands o);

    public boolean remove_Brand(int brand_Id);

    public int update_Brand(Brands o);
}
