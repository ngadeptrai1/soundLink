/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package test;

import java.awt.event.ActionListener;

/**
 *
 * @author dnha1
 */
public interface TextWrangler {
  
    public void addActionListener(ActionListener listener);
    public void removeActionListener(ActionListener listener);
    public String getText();
}

