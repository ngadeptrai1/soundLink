/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.view;

import com.poly.globaldata.GobalSelecttedProduct;
import com.poly.model.Product;
import com.poly.model.ProductDetails;
import com.poly.ultis.XImage;
import com.poy.service.CRUDService;
import com.poy.service.Impl.ColorServiceImpl;
import com.poy.service.Impl.ProductDetailImpl;
import com.poy.service.Impl.ProductServiceImpl;
import com.poy.service.ProductDeatailsService;
import com.poy.service.ProductService;
import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnha1
 */
public class ProductDetailsV extends javax.swing.JPanel {

    ProductDeatailsService productDeatailDao = new ProductDetailImpl();
    private int totalPage;
    private int currentPage = 0;
    private int ind = -1;

    ProductService prDao = new ProductServiceImpl();
    private CRUDService crud;
    List<Object[]> lit;

    public ProductDetailsV() {
        initComponents();
        
        setCob7();
        setLabelProductDetail();
        currentPage = 0;
        setTotalPageProductDetail();
        setTextProductDetail();
        fillTotableProductDetail();
        

    }

    void clear() {
        tblListProductDetail.clearSelection();
        cboColor.setSelectedIndex(0);
        cboColorSearch.setSelectedIndex(0);
        txtPriceProductDetail.setText("");
        txtQuantityProductDetail.setText("");
        chkActivatedProductDeatil.setSelected(false);
        lblImageProductDetail.setToolTipText(null);
        lblImageProductDetail.setIcon(null);
        rdoActivatedProductDetail.setSelected(true);
    }

    void setLabelProductDetail() {

        if (GobalSelecttedProduct.selectProduct > 0) {
            Product pr = prDao.findById(GobalSelecttedProduct.selectProduct);
            jLabel4.setText("Bạn đang chọn : " + pr.getName());
        } else {
            jLabel4.setText("Bạn chưa chọn sản phẩm nào");
        }

    }

    void setCob7() {
        crud = new ColorServiceImpl();
        lit = crud.findAllActivate();
        cboColorSearch.addItem("");
        for (Object[] oj : lit) {
            cboColor.addItem((String) oj[1]);
            cboColorSearch.addItem((String) oj[1]);
        }

    }

    void setImageProductDetail() {
        JFileChooser filechooser = new JFileChooser();
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            XImage.save(file);
           
            
             ImageIcon icon = XImage.read(file.getName());
            Image img = icon.getImage();
            
            Image imgScale = img.getScaledInstance(lblImageProductDetail.getWidth(), lblImageProductDetail.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImage = new ImageIcon(imgScale);
            
            lblImageProductDetail.setIcon(scaledImage);
            
            lblImageProductDetail.setIcon(icon);
            lblImageProductDetail.setToolTipText(file.getName());
        }

    }

    void fillTotableProductDetail() {
        DefaultTableModel modell = (DefaultTableModel) tblListProductDetail.getModel();
        modell.setRowCount(0);
        for (ProductDetails prdt : productDeatailDao.getALlByProduct(GobalSelecttedProduct.selectProduct, getDataFindProductDetail(), currentPage)) {
            modell.addRow(prdt.toObject());
        }

    }

    private ProductDetails getDataFindProductDetail() {
        ProductDetails prdt = new ProductDetails();
        prdt.setActivated(rdoActivatedProductDetail.isSelected() ? Boolean.TRUE : Boolean.FALSE);
        prdt.setColorName(cboColorSearch.getSelectedItem().toString());
        return prdt;
    }

    private ProductDetails getDataForm() {
        ProductDetails prdt = new ProductDetails();
        prdt.setColorName(cboColor.getSelectedItem().toString());
        prdt.setActivated(chkActivatedProductDeatil.isSelected());
        prdt.setPrice(Long.parseLong(txtPriceProductDetail.getText()));
        prdt.setProductId(GobalSelecttedProduct.selectProduct);
        prdt.setThumnail(lblImageProductDetail.getToolTipText());
        prdt.setQuantity(Integer.parseInt(txtQuantityProductDetail.getText()));
        return prdt;
    }

    void setTextProductDetail() {

        if (GobalSelecttedProduct.selectProduct < 0) {
            lblProductDeatil.setText("Hiện không có sản phẩm nào");
        } else {
            if (productDeatailDao.getALlByProduct(GobalSelecttedProduct.selectProduct, getDataFindProductDetail(), currentPage).size() == 0) {
                lblProductDeatil.setText("Hiện không có sản phẩm nào");
            } else {
                lblProductDeatil.setText("Trang số " + (currentPage + 1) + "trên " + totalPage + "trang");
            }
        }

    }

    private boolean validateProductDetail() {
        Pattern pattern = Pattern.compile("");
        if (txtPriceProductDetail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Giá không được để trống !");
            return false;
        } else if (txtQuantityProductDetail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Số lượng không được trống");
            return false;
        } else if (!txtPriceProductDetail.getText().matches("[0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Số tiền nhập vào không hợp lệ");
            return false;
        } else if (Double.parseDouble(txtPriceProductDetail.getText()) < 100000) {
            JOptionPane.showMessageDialog(this, "Số tiền nhập vào phải lớn hơn 100.00 !");
            return false;
        } else if (!txtQuantityProductDetail.getText().matches("[0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Số lượng nhập vào không hợp lệ");
            return false;
        } else if (Double.parseDouble(txtQuantityProductDetail.getText()) < 5) {
            JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải lớn hơn 5!");
            return false;
        }
        if (lblImageProductDetail.getToolTipText() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh cho sản phẩm  !");
            return false;
        } else {
            return true;
        }

    }

    void setTotalPageProductDetail() {

        if (productDeatailDao.getTotalPage(GobalSelecttedProduct.selectProduct, getDataFindProductDetail()) > 5) {
            totalPage = productDeatailDao.getTotalPage(GobalSelecttedProduct.selectProduct, getDataFindProductDetail()) / 5;
            if (productDeatailDao.getTotalPage(GobalSelecttedProduct.selectProduct, getDataFindProductDetail()) % 5 != 0) {
                totalPage++;
            }
        } else {
            totalPage = 1;
        }
    }

    void addProductDetail() {
        if (GobalSelecttedProduct.selectProduct <= 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào !");
        } else {
            if (validateProductDetail()) {
                int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm sản phẩm không ? ");
                if (flag == 0) {
                    {
                        int result = productDeatailDao.createProductDetail(getDataForm());
                        if (result > 0) {
                            JOptionPane.showMessageDialog(this, "Thêm thành công");
                            clear();
                            ind = -1;
                            fillTotableProductDetail();
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm không thành công");
                        }
                    }

                }
            }

        }

    }

    void updateProductDetail() {
        if (GobalSelecttedProduct.selectProduct <= 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào !");
        } else {
            if (ind < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào ");
            } else {
                if (validateProductDetail()) {
                    int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn câp nhật sản phẩm không ? ");
                    if (flag == 0) {
                        ProductDetails pr = getDataForm();
                        pr.setId(productDeatailDao.getALlByProduct(GobalSelecttedProduct.selectProduct, getDataFindProductDetail(), currentPage).get(ind).getId());
                        int result = productDeatailDao.updateProductDetail(pr);
                        if (result > 0) {
                            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                            clear();
                            ind = -1;
                            fillTotableProductDetail();
                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
                        }

                    }
                }
            }

        }

    }

    void showProductDetail(int index) {
        ProductDetails pr = productDeatailDao.getALlByProduct(GobalSelecttedProduct.selectProduct, getDataFindProductDetail(), currentPage).get(index);
        txtPriceProductDetail.setText(pr.getPrice().toString());
        txtQuantityProductDetail.setText(pr.getQuantity().toString());
        cboColor.setSelectedItem(pr.getColorName());
        chkActivatedProductDeatil.setSelected(pr.getActivated());
        if (pr.getThumnail() != null) {
            lblImageProductDetail.setToolTipText(pr.getThumnail());
            
             ImageIcon icon = XImage.read(pr.getThumnail());
            Image img = icon.getImage();
            
            Image imgScale = img.getScaledInstance(lblImageProductDetail.getWidth(), lblImageProductDetail.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImage = new ImageIcon(imgScale);
            
            lblImageProductDetail.setIcon(scaledImage);
            
         
            System.out.println(pr.getThumnail());
        } else {
            lblImageProductDetail.setToolTipText(null);
            lblImageProductDetail.setIcon(null);
            lblImageProductDetail.setText("trống");

        }
    }

    void removeProductDetail() {
        JOptionPane.showMessageDialog(this, "Không được xóa sản phẩm này !");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtPriceProductDetail = new javax.swing.JTextField();
        txtQuantityProductDetail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblImageProductDetail = new javax.swing.JLabel();
        cboColor = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        chkActivatedProductDeatil = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        btnAddProductDetail = new javax.swing.JButton();
        btnUpdateProductDetail = new javax.swing.JButton();
        btnLamMoiSPCT = new javax.swing.JButton();
        btnRemoveProductDeatil = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cboKhoangGia = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        cboColorSearch = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        rdoActivatedProductDetail = new javax.swing.JRadioButton();
        rdoProductDetail = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListProductDetail = new javax.swing.JTable();
        btnLastProductDetal = new javax.swing.JButton();
        btnNextProductDetail = new javax.swing.JButton();
        lblProductDeatil = new javax.swing.JLabel();
        btnPrevProductDetail = new javax.swing.JButton();
        btnFirstProductDetail = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Số lượng:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Giá bán:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Màu:");

        lblImageProductDetail.setBackground(new java.awt.Color(255, 255, 255));
        lblImageProductDetail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblImageProductDetail.setForeground(new java.awt.Color(51, 51, 51));
        lblImageProductDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageProductDetail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblImageProductDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageProductDetailMouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Trạng thái:");

        jLabel39.setText("VND");

        chkActivatedProductDeatil.setText(" Bán ?");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 51));
        jLabel4.setText("  đă");

        btnAddProductDetail.setBackground(new java.awt.Color(255, 204, 0));
        btnAddProductDetail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAddProductDetail.setForeground(new java.awt.Color(51, 51, 51));
        btnAddProductDetail.setText("Thêm");
        btnAddProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductDetailActionPerformed(evt);
            }
        });

        btnUpdateProductDetail.setBackground(new java.awt.Color(255, 204, 0));
        btnUpdateProductDetail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnUpdateProductDetail.setForeground(new java.awt.Color(51, 51, 51));
        btnUpdateProductDetail.setText("Cập nhật");
        btnUpdateProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductDetailActionPerformed(evt);
            }
        });

        btnLamMoiSPCT.setBackground(new java.awt.Color(255, 204, 0));
        btnLamMoiSPCT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLamMoiSPCT.setForeground(new java.awt.Color(51, 51, 51));
        btnLamMoiSPCT.setText("Làm mới");
        btnLamMoiSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPCTActionPerformed(evt);
            }
        });

        btnRemoveProductDeatil.setBackground(new java.awt.Color(255, 204, 0));
        btnRemoveProductDeatil.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRemoveProductDeatil.setForeground(new java.awt.Color(51, 51, 51));
        btnRemoveProductDeatil.setText("Loại bỏ");
        btnRemoveProductDeatil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveProductDeatilActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Ảnh sản phẩm :");

        jButton3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 51));
        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtQuantityProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPriceProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboColor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkActivatedProductDeatil, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAddProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(btnUpdateProductDetail)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addComponent(btnRemoveProductDeatil, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(btnLamMoiSPCT)
                        .addGap(195, 195, 195))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImageProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPriceProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel39)
                    .addComponent(jLabel16)
                    .addComponent(txtQuantityProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(chkActivatedProductDeatil)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cboColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProductDetail)
                    .addComponent(btnUpdateProductDetail)
                    .addComponent(btnLamMoiSPCT)
                    .addComponent(btnRemoveProductDeatil))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImageProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Giá tiền:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Màu :");

        buttonGroup1.add(rdoActivatedProductDetail);
        rdoActivatedProductDetail.setSelected(true);
        rdoActivatedProductDetail.setText("Đang bán ");

        buttonGroup1.add(rdoProductDetail);
        rdoProductDetail.setText("Tạm ngưng");

        jButton1.setText("Tìm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(cboKhoangGia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cboColorSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdoActivatedProductDetail)
                        .addGap(75, 75, 75)
                        .addComponent(rdoProductDetail)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cboKhoangGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColorSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jButton1))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoActivatedProductDetail)
                    .addComponent(rdoProductDetail))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblListProductDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Màu sắc", "Giá bán", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListProductDetail.setGridColor(new java.awt.Color(235, 190, 153));
        tblListProductDetail.setSelectionBackground(new java.awt.Color(204, 255, 204));
        tblListProductDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListProductDetailMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListProductDetail);

        btnLastProductDetal.setText(">>");
        btnLastProductDetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastProductDetalActionPerformed(evt);
            }
        });

        btnNextProductDetail.setText(">");
        btnNextProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextProductDetailActionPerformed(evt);
            }
        });

        lblProductDeatil.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblProductDeatil.setForeground(new java.awt.Color(255, 51, 51));
        lblProductDeatil.setText("jLabel8");

        btnPrevProductDetail.setText("<");
        btnPrevProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevProductDetailActionPerformed(evt);
            }
        });

        btnFirstProductDetail.setText("<<");
        btnFirstProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstProductDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnFirstProductDetail)
                        .addGap(30, 30, 30)
                        .addComponent(btnPrevProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214)
                        .addComponent(lblProductDeatil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNextProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnLastProductDetal)
                        .addGap(64, 64, 64))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirstProductDetail)
                    .addComponent(btnPrevProductDetail)
                    .addComponent(btnNextProductDetail)
                    .addComponent(btnLastProductDetal)
                    .addComponent(lblProductDeatil))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageProductDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageProductDetailMouseClicked
        // TODO add your handling code here:
        setImageProductDetail();
    }//GEN-LAST:event_lblImageProductDetailMouseClicked

    private void btnAddProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductDetailActionPerformed
        addProductDetail();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddProductDetailActionPerformed

    private void btnUpdateProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductDetailActionPerformed
        updateProductDetail();
//         TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateProductDetailActionPerformed

    private void btnLamMoiSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPCTActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiSPCTActionPerformed

    private void btnRemoveProductDeatilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProductDeatilActionPerformed
        removeProductDetail();
    }//GEN-LAST:event_btnRemoveProductDeatilActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ColorV colorView = new ColorV();
        colorView.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        fillTotableProductDetail();
        setTextProductDetail();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblListProductDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListProductDetailMouseClicked
        // TODO add your handling code here:
        ind = tblListProductDetail.getSelectedRow();
        showProductDetail(ind);
    }//GEN-LAST:event_tblListProductDetailMouseClicked

    private void btnLastProductDetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastProductDetalActionPerformed
        // TODO add your handling code here:
        currentPage = totalPage - 1;
        fillTotableProductDetail();
        setTextProductDetail();
    }//GEN-LAST:event_btnLastProductDetalActionPerformed

    private void btnNextProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextProductDetailActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        currentPage = currentPage + 1;
        if (currentPage > totalPage - 1) {
            currentPage = 0;
        }
        fillTotableProductDetail();
        setTextProductDetail();
    }//GEN-LAST:event_btnNextProductDetailActionPerformed

    private void btnPrevProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevProductDetailActionPerformed
        // TODO add your handling code here:

        currentPage = currentPage - 1;
        if (currentPage < 0) {
            currentPage = totalPage - 1;
        }
        fillTotableProductDetail();
        setTextProductDetail();
    }//GEN-LAST:event_btnPrevProductDetailActionPerformed

    private void btnFirstProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstProductDetailActionPerformed
        // TODO add your handling code here:
        currentPage = 0;
        setTextProductDetail();
        fillTotableProductDetail();
    }//GEN-LAST:event_btnFirstProductDetailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProductDetail;
    private javax.swing.JButton btnFirstProductDetail;
    private javax.swing.JButton btnLamMoiSPCT;
    private javax.swing.JButton btnLastProductDetal;
    private javax.swing.JButton btnNextProductDetail;
    private javax.swing.JButton btnPrevProductDetail;
    private javax.swing.JButton btnRemoveProductDeatil;
    private javax.swing.JButton btnUpdateProductDetail;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboColor;
    private javax.swing.JComboBox<String> cboColorSearch;
    private javax.swing.JComboBox<String> cboKhoangGia;
    private javax.swing.JCheckBox chkActivatedProductDeatil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImageProductDetail;
    private javax.swing.JLabel lblProductDeatil;
    private javax.swing.JRadioButton rdoActivatedProductDetail;
    private javax.swing.JRadioButton rdoProductDetail;
    private javax.swing.JTable tblListProductDetail;
    private javax.swing.JTextField txtPriceProductDetail;
    private javax.swing.JTextField txtQuantityProductDetail;
    // End of variables declaration//GEN-END:variables
}
