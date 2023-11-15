package com.poy.service;

import java.util.List;

public interface CRUDService<E> {

    int create(Object[] o);

    int update(Object[] o);

    int remove(String id);

    List<E> findAll(int pageNums,String text);
    
    List<Object[]> findAllActivate();
    
    int getTotalPage(String text);
}
