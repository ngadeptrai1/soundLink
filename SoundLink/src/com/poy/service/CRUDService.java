/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poy.service;

import java.util.List;

/**
 *
 * @author dnha1
 */
public interface CRUDService <E> {
    int create(E e);
    int update(E e);
    int remove(String id);
    List<E> findAll();
}
