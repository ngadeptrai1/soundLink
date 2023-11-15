/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poy.service;

import com.poly.model.Design;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author dnha1
 */
public class NewClass  extends Thread {
    JInternalFrame view;
    JDesktopPane desktopPane;

    public NewClass(JInternalFrame view, JDesktopPane desktopPane) {
        this.view = view;
        this.desktopPane = desktopPane;
    }
    
    
    
    public void run() {
         desktopPane.removeAll();
        desktopPane.add(view).setVisible(true);
    }
}
