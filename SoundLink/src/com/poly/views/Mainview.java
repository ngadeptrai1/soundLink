
package com.poly.views;

public class Mainview extends javax.swing.JFrame {

    SanPhamview sp = new SanPhamview();
    BanHangview bh = new BanHangview();
    

    public Mainview() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        jDesktop.add(bh, 1).setVisible(false);
        jDesktop.add(sp, 2).setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelBanHang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanelSanPham = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanelHoaDon = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanelGiamGia = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanelKhachHang = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanelThongKe = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanelNhanVien = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanelBaoHanh = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanelThoat = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jDesktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(245, 245, 220));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 170));

        jPanelBanHang.setBackground(new java.awt.Color(165, 42, 42));
        jPanelBanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBanHangMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Bán Hàng");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelBanHangLayout = new javax.swing.GroupLayout(jPanelBanHang);
        jPanelBanHang.setLayout(jPanelBanHangLayout);
        jPanelBanHangLayout.setHorizontalGroup(
            jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBanHangLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanelBanHangLayout.setVerticalGroup(
            jPanelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBanHangLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanelBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 230, 40));

        jPanelSanPham.setBackground(new java.awt.Color(165, 42, 42));
        jPanelSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSanPhamMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Sản Phẩm");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelSanPhamLayout = new javax.swing.GroupLayout(jPanelSanPham);
        jPanelSanPham.setLayout(jPanelSanPhamLayout);
        jPanelSanPhamLayout.setHorizontalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanelSanPhamLayout.setVerticalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanelSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 230, -1));

        jPanelHoaDon.setBackground(new java.awt.Color(165, 42, 42));
        jPanelHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelHoaDonMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Hóa Đơn");

        javax.swing.GroupLayout jPanelHoaDonLayout = new javax.swing.GroupLayout(jPanelHoaDon);
        jPanelHoaDon.setLayout(jPanelHoaDonLayout);
        jPanelHoaDonLayout.setHorizontalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanelHoaDonLayout.setVerticalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHoaDonLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanelHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 230, -1));

        jPanelGiamGia.setBackground(new java.awt.Color(165, 42, 42));
        jPanelGiamGia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setBackground(new java.awt.Color(165, 42, 42));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Giảm Giá");

        javax.swing.GroupLayout jPanelGiamGiaLayout = new javax.swing.GroupLayout(jPanelGiamGia);
        jPanelGiamGia.setLayout(jPanelGiamGiaLayout);
        jPanelGiamGiaLayout.setHorizontalGroup(
            jPanelGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGiamGiaLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanelGiamGiaLayout.setVerticalGroup(
            jPanelGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 230, -1));

        jPanelKhachHang.setBackground(new java.awt.Color(165, 42, 42));
        jPanelKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Khách Hàng");

        javax.swing.GroupLayout jPanelKhachHangLayout = new javax.swing.GroupLayout(jPanelKhachHang);
        jPanelKhachHang.setLayout(jPanelKhachHangLayout);
        jPanelKhachHangLayout.setHorizontalGroup(
            jPanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhachHangLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel7)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanelKhachHangLayout.setVerticalGroup(
            jPanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 230, -1));

        jPanelThongKe.setBackground(new java.awt.Color(165, 42, 42));
        jPanelThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Thống Kê");

        javax.swing.GroupLayout jPanelThongKeLayout = new javax.swing.GroupLayout(jPanelThongKe);
        jPanelThongKe.setLayout(jPanelThongKeLayout);
        jPanelThongKeLayout.setHorizontalGroup(
            jPanelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongKeLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanelThongKeLayout.setVerticalGroup(
            jPanelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 230, -1));

        jPanelNhanVien.setBackground(new java.awt.Color(165, 42, 42));
        jPanelNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelNhanVien.setAutoscrolls(true);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Nhân Viên");

        javax.swing.GroupLayout jPanelNhanVienLayout = new javax.swing.GroupLayout(jPanelNhanVien);
        jPanelNhanVien.setLayout(jPanelNhanVienLayout);
        jPanelNhanVienLayout.setHorizontalGroup(
            jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel9)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanelNhanVienLayout.setVerticalGroup(
            jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 230, -1));

        jPanelBaoHanh.setBackground(new java.awt.Color(165, 42, 42));
        jPanelBaoHanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Bảo Hành");

        javax.swing.GroupLayout jPanelBaoHanhLayout = new javax.swing.GroupLayout(jPanelBaoHanh);
        jPanelBaoHanh.setLayout(jPanelBaoHanhLayout);
        jPanelBaoHanhLayout.setHorizontalGroup(
            jPanelBaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaoHanhLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanelBaoHanhLayout.setVerticalGroup(
            jPanelBaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaoHanhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelBaoHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 230, -1));

        jPanelThoat.setBackground(new java.awt.Color(165, 42, 42));
        jPanelThoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Thoát");

        javax.swing.GroupLayout jPanelThoatLayout = new javax.swing.GroupLayout(jPanelThoat);
        jPanelThoat.setLayout(jPanelThoatLayout);
        jPanelThoatLayout.setHorizontalGroup(
            jPanelThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThoatLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanelThoatLayout.setVerticalGroup(
            jPanelThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThoatLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 230, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 820));

        jDesktop.setAutoscrolls(true);
        jDesktop.setPreferredSize(new java.awt.Dimension(1129, 762));

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1057, Short.MAX_VALUE)
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktop, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktop, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        sp = new SanPhamview();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jPanelSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSanPhamMouseClicked
        // TODO add your handling code here:
        jDesktop.getComponent(1).setVisible(false);
        jDesktop.getComponent(2).setVisible(true);
    }//GEN-LAST:event_jPanelSanPhamMouseClicked

    private void jPanelBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBanHangMouseClicked
        // TODO add your handling code here:

        jDesktop.removeAll();
        jDesktop.add(bh).setVisible(true);
        jDesktop.add(sp).setVisible(false);

    }//GEN-LAST:event_jPanelBanHangMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        BanHangview bh = new BanHangview();
        jDesktop.removeAll();
        jDesktop.add(bh).setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanelHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelHoaDonMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jPanelHoaDonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mainview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBanHang;
    private javax.swing.JPanel jPanelBaoHanh;
    private javax.swing.JPanel jPanelGiamGia;
    private javax.swing.JPanel jPanelHoaDon;
    private javax.swing.JPanel jPanelKhachHang;
    private javax.swing.JPanel jPanelNhanVien;
    private javax.swing.JPanel jPanelSanPham;
    private javax.swing.JPanel jPanelThoat;
    private javax.swing.JPanel jPanelThongKe;
    // End of variables declaration//GEN-END:variables

}
