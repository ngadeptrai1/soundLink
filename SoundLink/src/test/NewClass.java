/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

/**
 *
 * @author dnha1
 */
public class NewClass {
     public static void main(String[] args) {
        Jframe2 frame1 = new Jframe2();
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Kiểm tra xem JFrame nào đang đóng
                JFrame frame = (JFrame) e.getSource();
                if (frame == frame1) {
                    // Thông báo cho JFrame còn lại
                    System.out.println("dong");
                }
            }
        });
        frame1.setVisible(true);

        JframeOne frame2 = new JframeOne( );
        frame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Kiểm tra xem JFrame nào đang đóng
                JFrame frame = (JFrame) e.getSource();
                if (frame == frame2) {
                    // Thông báo cho JFrame còn lại
                    System.out.println("dong");
                }
            }
        });
        frame2.setVisible(true);
    }
}
