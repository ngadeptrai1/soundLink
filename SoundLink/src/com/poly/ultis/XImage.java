/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.ultis;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author dnha1
 */
public class XImage {
//   
//    public  static  Image getAppIcon(){
//        URL url =    XImage.class.getResource("/com/edusys/icon/Hinh/fpt.png");
//        return  new ImageIcon(url).getImage();
//    }
    public static  void save(File file){
    File dst = new File("logos", file.getName());
    if(!dst.getParentFile().exists()){
    dst.getParentFile().mkdirs();
    }
        try {
            Path from = Paths.get(file.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ImageIcon read (String fileName){
    File path = new File("logos" , fileName);
        System.out.println(path.getAbsolutePath());
    return new ImageIcon(path.getAbsolutePath());
    
}

}
