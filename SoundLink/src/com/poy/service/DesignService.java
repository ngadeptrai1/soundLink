package com.poly.Interface;

import java.util.List;
import com.poly.model.Design;

public interface Interface_Design {

    public List<Design> list_Design();

    public int add_Design(Design o);

    public boolean remove_Design(int design_Id);

    public int update_Design(Design o);
}
