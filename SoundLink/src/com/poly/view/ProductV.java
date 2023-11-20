/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.view;

import com.poly.globaldata.GobalSelecttedProduct;
import com.poly.main.Main;
import com.poly.model.Product;
import com.poly.ultis.XImage;
import com.poy.service.CRUDService;
import com.poy.service.Impl.BrandServiceImpl;
import com.poy.service.Impl.CategoryServiceImpl;
import com.poy.service.Impl.DesignServiceImpl;
import com.poy.service.Impl.FrequencyRangeImpl;
import com.poy.service.Impl.ProductServiceImpl;
import com.poy.service.Impl.TotalPowerImpl;
import com.poy.service.Impl.TypeProductServiceImpl;
import com.poy.service.ProductService;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnha1
 */
public class ProductV extends javax.swing.JPanel {

    private int ind = -1;
    DefaultTableModel model2 = new DefaultTableModel();
    private int totalPage;
    private int currentPage = 0;
    ProductService prDao = new ProductServiceImpl();
    JFileChooser js = new JFileChooser();
    List<Object[]> lit;
    private CRUDService crud;

    /**
     * Creates new form sp
     */
    public ProductV() {
        initComponents();
        currentPage = 0;
        setcbo1();
        setTotalPageProduct();
        setTextProduct();
        fillToTableProduct();
        
        

    }

   

    void updateProduct() {
        if (ind < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào ! ");
        } else if (checkProduct()) {
            int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật ");
            if (flag == 0) {

                Product pr = getDataProduct();
                pr.setId(prDao.findAll(getDataFind(), currentPage).get(ind).getId());
                System.out.println(pr);
                int i = prDao.updateProduct(pr);
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
                    currentPage = 0;
                    setTotalPageProduct();
                    setTextProduct();
                    clear();
                    fillToTableProduct();

                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!");
                }
            }
        }

    }

    private Product getDataProduct() {
        Product pr = new Product();
        pr.setAccountId(null);
        pr.setName(txtNameProduct.getText());
        pr.setTotalQuantity(10);
        pr.setActivated(chkActi.isSelected());
        pr.setBrandName(cboBrand.getSelectedItem().toString());
        pr.setCategorieName(cboCate.getSelectedItem().toString());
        pr.setDesignName(cboDesign.getSelectedItem().toString());
        pr.setFrequencyRangeName(cboFreq.getSelectedItem().toString());
        pr.setTotalPowerName(cboTotal.getSelectedItem().toString());
        pr.setTypeProductName(cboType.getSelectedItem().toString());
        pr.setDescription(txtDesProduct.getText());
        pr.setThumnail(lblImageProduct.getToolTipText());
        return pr;
    }

    void showDataProduct(int ind) {
        Product pr = prDao.findAll(getDataFind(), currentPage).get(ind);
        txtNameProduct.setText(pr.getName());
        txtDesProduct.setText(pr.getDescription());
        cboBrand.setSelectedItem(pr.getBrandName());
        cboCate.setSelectedItem(pr.getCategorieName());
        cboFreq.setSelectedItem(pr.getFrequencyRangeName());
        cboDesign.setSelectedItem(pr.getDesignName());
        cboTotal.setSelectedItem(pr.getTotalPowerName());
        cboType.setSelectedItem(pr.getTypeProductName());
        chkActi.setSelected(pr.getActivated());
        if (pr.getThumnail() != null) {
            lblImageProduct.setToolTipText(pr.getThumnail());

            ImageIcon icon = XImage.read(pr.getThumnail());
            Image img = icon.getImage();

            Image imgScale = img.getScaledInstance(lblImageProduct.getWidth(), lblImageProduct.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImage = new ImageIcon(imgScale);

            lblImageProduct.setIcon(scaledImage);

            lblImageProduct.setIcon(scaledImage);
            System.out.println(pr.getThumnail());
        } else {
            lblImageProduct.setToolTipText(null);
            lblImageProduct.setIcon(null);
            lblImageProduct.setText("trống");

        }
    }

    void clear() {
        tblListProduct.clearSelection();

        txtDesProduct.setText("");
//        txtFindProduct.setText("");

        txtNameProduct.setText("");
        cboBrand.setSelectedIndex(0);
        cboBrandSearch.setSelectedIndex(0);
        cboCate.setSelectedIndex(0);
        cboCateSearch.setSelectedIndex(0);
        cboDesign.setSelectedIndex(0);
        cboDesignSearch.setSelectedIndex(0);
        cboFreq.setSelectedIndex(0);
        cboFreqSearch.setSelectedIndex(0);
        cboTotal.setSelectedIndex(0);
        cboTotalSearch.setSelectedIndex(0);
        cboType.setSelectedIndex(0);
        cboTypeSearch.setSelectedIndex(0);
        chkActi.setSelected(false);

        lblImageProduct.setToolTipText(null);
        lblImageProduct.setIcon(null);

    }

    void setTextProduct() {
        if (prDao.getTotalPage(getDataFind()) == 0) {
            lblPageProduct.setText("Hiện không có sản phẩm nào ");
        } else {
            lblPageProduct.setText("Trang số " + (currentPage + 1) + " Trên " + totalPage);
        }
    }

    Product getDataFind() {
        Product pr = new Product();
        pr.setName(txtFindProduct3.getText() + "");
        pr.setActivated(rdoProductTrue.isSelected() ? Boolean.TRUE : Boolean.FALSE);
        pr.setBrandName(cboBrandSearch.getSelectedItem().toString());
        pr.setCategorieName(cboCateSearch.getSelectedItem().toString());
        pr.setDesignName(cboDesignSearch.getSelectedItem().toString());
        pr.setTotalPowerName(cboTotalSearch.getSelectedItem().toString());
        pr.setFrequencyRangeName(cboFreqSearch.getSelectedItem().toString());
        pr.setTypeProductName(cboTypeSearch.getSelectedItem().toString());
        return pr;
    }

    void setImageProduct() {
        JFileChooser filechooser = new JFileChooser();
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            Image img = icon.getImage();

            Image imgScale = img.getScaledInstance(lblImageProduct.getWidth(), lblImageProduct.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImage = new ImageIcon(imgScale);

            lblImageProduct.setIcon(scaledImage);
            lblImageProduct.setToolTipText(file.getName());
        }

    }

    void fillToTableProduct() {
        model2 = (DefaultTableModel) tblListProduct.getModel();
        model2.setRowCount(0);
        for (Product p : prDao.findAll(getDataFind(), currentPage)) {
            model2.addRow(p.toData());
        }
    }

    private boolean checkProduct() {
        if (txtNameProduct.getText().equals("") || txtDesProduct.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
            return false;
        }
        if (lblImageProduct.getToolTipText() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh cho sản phẩm !");
            return false;
        }
        return true;
    }

    void setTotalPageProduct() {

        if (prDao.getTotalPage(getDataFind()) > 5) {
            totalPage = prDao.getTotalPage(getDataFind()) / 5;
            if (prDao.getTotalPage(getDataFind()) % 5 != 0) {
                totalPage++;
            }
        } else {
            totalPage = 1;
        }

    }

    void setcbo1() {
        crud = new DesignServiceImpl();
        lit = crud.findAllActivate();
        cboDesign.removeAllItems();
        cboDesignSearch.removeAllItems();
        cboDesignSearch.addItem("");
        System.out.println(lit.get(0)[1]);
        for (Object[] oj : lit) {
            cboDesign.addItem((String) oj[1]);
            cboDesignSearch.addItem((String) oj[1]);
        }
        setCob2();
    }

    void setCob2() {
        crud = new TypeProductServiceImpl();
        lit = crud.findAllActivate();
        cboType.removeAllItems();
        cboTypeSearch.removeAllItems();
        cboTypeSearch.addItem("");
        for (Object[] oj : lit) {
            cboType.addItem((String) oj[1]);
            cboTypeSearch.addItem((String) oj[1]);
        }
        System.out.println(lit.get(0)[1]);
        setCob3();
    }

    void setCob3() {
        crud = new FrequencyRangeImpl();
        lit = crud.findAllActivate();
        cboFreq.removeAllItems();
        cboFreqSearch.removeAllItems();
        cboFreqSearch.addItem("");
        for (Object[] oj : lit) {
            cboFreq.addItem((String) oj[1]);
            cboFreqSearch.addItem((String) oj[1]);
        }

        setCob4();
    }

    void setCob4() {
        crud = new TotalPowerImpl();
        lit = crud.findAllActivate();
        cboTotal.removeAllItems();
        cboTotalSearch.removeAllItems();
        cboTotalSearch.addItem("");
        for (Object[] oj : lit) {
            cboTotal.addItem((String) oj[1]);
            cboTotalSearch.addItem((String) oj[1]);
        }

        setCob5();
    }

    void setCob5() {
        crud = new CategoryServiceImpl();
        lit = crud.findAllActivate();
        cboCate.removeAllItems();
        cboCateSearch.removeAllItems();
        cboCateSearch.addItem("");
        for (Object[] oj : lit) {
            cboCate.addItem((String) oj[1]);
            cboCateSearch.addItem((String) oj[1]);
        }

        setCob6();
    }

    void setCob6() {
        crud = new BrandServiceImpl();
        lit = crud.findAllActivate();
        cboBrand.removeAllItems();
        cboBrandSearch.removeAllItems();
        cboBrandSearch.addItem("");
        for (Object[] oj : lit) {
            cboBrand.addItem((String) oj[1]);
            cboBrandSearch.addItem((String) oj[1]);
        }

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
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNameProduct = new javax.swing.JTextField();
        cboFreq = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cboBrand = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblImageProduct = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboTotal = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cboCate = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cboDesign = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cboType = new javax.swing.JComboBox<>();
        chkActi = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        btnAddProduct = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtDesProduct = new javax.swing.JTextArea();
        btnHang = new javax.swing.JButton();
        btnDaiTan = new javax.swing.JButton();
        btnTongCongSuat = new javax.swing.JButton();
        btnDanhMuc = new javax.swing.JButton();
        btnKieuDang = new javax.swing.JButton();
        btnLoai = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtFindProduct3 = new javax.swing.JTextField();
        btnFindProduct3 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        cboCateSearch = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cboTypeSearch = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        cboFreqSearch = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        cboDesignSearch = new javax.swing.JComboBox<>();
        cboBrandSearch = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        cboTotalSearch = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        rdoProductTrue = new javax.swing.JRadioButton();
        rdoProductFalse = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListProduct = new javax.swing.JTable();
        btnFirstPageProduct = new javax.swing.JButton();
        btnPrevPageProduct = new javax.swing.JButton();
        btnNextPageProduct = new javax.swing.JButton();
        btnLastPageProduct = new javax.swing.JButton();
        lblPageProduct = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tên sản phẩm:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Kiểu dáng :");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Hãng :");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Trạng thái :");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblImageProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Dải tần : ");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Danh mục :");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Tổng công suất :");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("Loại :");

        chkActi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chkActi.setText("Bán ?");
        chkActi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkActiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Ảnh : ");

        btnAddProduct.setBackground(new java.awt.Color(255, 204, 0));
        btnAddProduct.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(51, 51, 51));
        btnAddProduct.setText("Thêm");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 0));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSua.setForeground(new java.awt.Color(51, 51, 51));
        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(255, 204, 0));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(51, 51, 51));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Miêu tả : ");

        txtDesProduct.setColumns(20);
        txtDesProduct.setRows(5);
        jScrollPane7.setViewportView(txtDesProduct);

        btnHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHang.setForeground(new java.awt.Color(255, 0, 0));
        btnHang.setText("+");
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        btnDaiTan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDaiTan.setForeground(new java.awt.Color(255, 0, 0));
        btnDaiTan.setText("+");
        btnDaiTan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaiTanActionPerformed(evt);
            }
        });

        btnTongCongSuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTongCongSuat.setForeground(new java.awt.Color(255, 0, 0));
        btnTongCongSuat.setText("+");
        btnTongCongSuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTongCongSuatActionPerformed(evt);
            }
        });

        btnDanhMuc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDanhMuc.setForeground(new java.awt.Color(255, 0, 0));
        btnDanhMuc.setText("+");
        btnDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhMucActionPerformed(evt);
            }
        });

        btnKieuDang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKieuDang.setForeground(new java.awt.Color(255, 0, 0));
        btnKieuDang.setText("+");
        btnKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKieuDangActionPerformed(evt);
            }
        });

        btnLoai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLoai.setForeground(new java.awt.Color(255, 0, 0));
        btnLoai.setText("+");
        btnLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiActionPerformed(evt);
            }
        });

        btnLamMoi1.setBackground(new java.awt.Color(255, 204, 0));
        btnLamMoi1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLamMoi1.setForeground(new java.awt.Color(51, 51, 51));
        btnLamMoi1.setText("Làm mới thuộc tính");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addComponent(jLabel21)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNameProduct)
                            .addComponent(cboBrand, 0, 177, Short.MAX_VALUE)
                            .addComponent(cboFreq, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTotal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTongCongSuat, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addComponent(btnDaiTan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(100, 100, 100)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(cboType, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(chkActi)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboDesign, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboCate, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnKieuDang, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                    .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(60, 60, 60))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnLamMoi)
                                .addGap(12, 12, 12)
                                .addComponent(btnAddProduct)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua))
                            .addComponent(btnLamMoi1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cboCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(cboBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDesign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboFreq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel23)
                            .addComponent(cboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDaiTan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel35)
                                .addComponent(chkActi)
                                .addComponent(cboTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addComponent(btnTongCongSuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(btnLamMoi)
                                                    .addComponent(btnAddProduct)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSua)))
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Tìm kiếm sản phẩm :");

        txtFindProduct3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindProduct3KeyReleased(evt);
            }
        });

        btnFindProduct3.setText("Tìm");
        btnFindProduct3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProduct3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txtFindProduct3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btnFindProduct3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindProduct3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindProduct3))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Danh mục :");

        cboCateSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCateSearchItemStateChanged(evt);
            }
        });
        cboCateSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCateSearchActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Loại :");

        cboTypeSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTypeSearchItemStateChanged(evt);
            }
        });
        cboTypeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTypeSearchActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("Dải tần :");

        cboFreqSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFreqSearchItemStateChanged(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Thiết kế : ");

        cboDesignSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDesignSearchItemStateChanged(evt);
            }
        });

        cboBrandSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboBrandSearchItemStateChanged(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Hãng :");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("Tổng công xuất : ");

        cboTotalSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTotalSearchItemStateChanged(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Trạng thái : ");

        buttonGroup1.add(rdoProductTrue);
        rdoProductTrue.setSelected(true);
        rdoProductTrue.setText("Đang bán");

        buttonGroup1.add(rdoProductFalse);
        rdoProductFalse.setText("Ngưng bán");

        tblListProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Danh mục", "Hãng", "Dải tần", "Công suất", "Kiểu dáng", "Loại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListProduct.setGridColor(new java.awt.Color(235, 183, 158));
        tblListProduct.setSelectionBackground(new java.awt.Color(255, 153, 204));
        tblListProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListProduct);

        btnFirstPageProduct.setText("<<");
        btnFirstPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstPageProductActionPerformed(evt);
            }
        });

        btnPrevPageProduct.setText("<");
        btnPrevPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageProductActionPerformed(evt);
            }
        });

        btnNextPageProduct.setText(">");
        btnNextPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageProductActionPerformed(evt);
            }
        });

        btnLastPageProduct.setText(">>");
        btnLastPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageProductActionPerformed(evt);
            }
        });

        lblPageProduct.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPageProduct.setForeground(new java.awt.Color(255, 0, 0));
        lblPageProduct.setText("jLabel12");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel34))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboCateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboDesignSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel42))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboBrandSearch, 0, 134, Short.MAX_VALUE)
                                    .addComponent(cboTotalSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboFreqSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel44)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoProductTrue)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoProductFalse))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(btnFirstPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrevPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(lblPageProduct)
                        .addGap(80, 80, 80)
                        .addComponent(btnNextPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLastPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(cboFreqSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDesignSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel44)
                            .addComponent(rdoProductTrue)
                            .addComponent(rdoProductFalse)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cboCateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboBrandSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(cboTotalSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevPageProduct)
                    .addComponent(btnFirstPageProduct)
                    .addComponent(lblPageProduct)
                    .addComponent(btnNextPageProduct)
                    .addComponent(btnLastPageProduct))
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageProductMouseClicked
        // TODO add your handling code here:
        setImageProduct();
    }//GEN-LAST:event_lblImageProductMouseClicked

    private void chkActiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkActiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkActiActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        if (checkProduct()) {
            int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm ?");
            if (flag == 0) {
                if(prDao.findByName(txtNameProduct.getText())==null){
                     int count = prDao.create(getDataProduct());
                 if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    setTotalPageProduct();
                    currentPage = 0;
                    setTextProduct();
                    ind = -1;
                    clear();
                }else{
                     JOptionPane.showMessageDialog(this,"Thêm sản phẩm mới thành công ");
                 }
                }else{
                    JOptionPane.showMessageDialog(this,"Sản phẩm đã tồn tại");
                }
               
               
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateProduct();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();
        fillToTableProduct();
        setTextProduct();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        // TODO add your handling code here:
        BrandView brandView = new BrandView();
        brandView.setVisible(true);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnDaiTanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaiTanActionPerformed
        // TODO add your handling code here:
        FrequencyView frequencyView = new FrequencyView();
        frequencyView.setVisible(true);
    }//GEN-LAST:event_btnDaiTanActionPerformed

    private void btnTongCongSuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTongCongSuatActionPerformed
        // TODO add your handling code here:
        TotalPowerView powerView = new TotalPowerView();
        powerView.setVisible(true);
    }//GEN-LAST:event_btnTongCongSuatActionPerformed

    private void btnDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhMucActionPerformed
        // TODO add your handling code here:
        CategoriView designView = new CategoriView();
        designView.setVisible(true);
    }//GEN-LAST:event_btnDanhMucActionPerformed

    private void btnKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKieuDangActionPerformed
        // TODO add your handling code here:
        DesignView typeProductsView = new DesignView();
        typeProductsView.setVisible(true);
    }//GEN-LAST:event_btnKieuDangActionPerformed

    private void btnLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiActionPerformed
        // TODO add your handling code here:
        TypeProductView tp = new TypeProductView();
        tp.setVisible(true);
    }//GEN-LAST:event_btnLoaiActionPerformed

    private void btnFirstPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPageProductActionPerformed
        // TODO add your handling code here:
        currentPage = 0;
        setTextProduct();
        fillToTableProduct();
    }//GEN-LAST:event_btnFirstPageProductActionPerformed

    private void btnPrevPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageProductActionPerformed
        // TODO add your handling code here:

        currentPage = currentPage - 1;
        if (currentPage < 0) {
            currentPage = totalPage - 1;
        }
        fillToTableProduct();
        setTextProduct();
    }//GEN-LAST:event_btnPrevPageProductActionPerformed

    private void btnNextPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageProductActionPerformed
        // TODO add your handling code here:
        currentPage = currentPage + 1;
        if (currentPage > totalPage - 1) {
            currentPage = 0;
        }
        fillToTableProduct();
        setTextProduct();
    }//GEN-LAST:event_btnNextPageProductActionPerformed

    private void btnLastPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageProductActionPerformed
        // TODO add your handling code here:
        currentPage = totalPage - 1;
        fillToTableProduct();
        setTextProduct();
    }//GEN-LAST:event_btnLastPageProductActionPerformed

    private void tblListProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListProductMouseClicked
        // TODO add your handling code here:

        ind = tblListProduct.getSelectedRow();
        showDataProduct(ind);
        GobalSelecttedProduct.selectProduct = prDao.findAll(getDataFind(), currentPage).get(ind).getId();
       
    }//GEN-LAST:event_tblListProductMouseClicked

    private void cboTotalSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTotalSearchItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTotalSearchItemStateChanged

    private void cboBrandSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboBrandSearchItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboBrandSearchItemStateChanged

    private void cboDesignSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDesignSearchItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDesignSearchItemStateChanged

    private void cboFreqSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFreqSearchItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_cboFreqSearchItemStateChanged

    private void cboTypeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTypeSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTypeSearchActionPerformed

    private void cboTypeSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTypeSearchItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_cboTypeSearchItemStateChanged

    private void cboCateSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboCateSearchItemStateChanged

    }//GEN-LAST:event_cboCateSearchItemStateChanged

    private void btnFindProduct3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProduct3ActionPerformed
        // TODO add your handling code here:

        ind = 0;
        setTotalPageProduct();
        setTextProduct();
        currentPage = 0;
        fillToTableProduct();
        tblListProduct.clearSelection();
    }//GEN-LAST:event_btnFindProduct3ActionPerformed

    private void txtFindProduct3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindProduct3KeyReleased

    }//GEN-LAST:event_txtFindProduct3KeyReleased

    private void cboCateSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCateSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCateSearchActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        // TODO add your handling code here:
        setcbo1();
    }//GEN-LAST:event_btnLamMoi1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnDaiTan;
    private javax.swing.JButton btnDanhMuc;
    private javax.swing.JButton btnFindProduct3;
    private javax.swing.JButton btnFirstPageProduct;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnKieuDang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLastPageProduct;
    private javax.swing.JButton btnLoai;
    private javax.swing.JButton btnNextPageProduct;
    private javax.swing.JButton btnPrevPageProduct;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTongCongSuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboBrand;
    private javax.swing.JComboBox<String> cboBrandSearch;
    private javax.swing.JComboBox<String> cboCate;
    private javax.swing.JComboBox<String> cboCateSearch;
    private javax.swing.JComboBox<String> cboDesign;
    private javax.swing.JComboBox<String> cboDesignSearch;
    private javax.swing.JComboBox<String> cboFreq;
    private javax.swing.JComboBox<String> cboFreqSearch;
    private javax.swing.JComboBox<String> cboTotal;
    private javax.swing.JComboBox<String> cboTotalSearch;
    private javax.swing.JComboBox<String> cboType;
    private javax.swing.JComboBox<String> cboTypeSearch;
    private javax.swing.JCheckBox chkActi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblImageProduct;
    private javax.swing.JLabel lblPageProduct;
    private javax.swing.JRadioButton rdoProductFalse;
    private javax.swing.JRadioButton rdoProductTrue;
    private javax.swing.JTable tblListProduct;
    private javax.swing.JTextArea txtDesProduct;
    private javax.swing.JTextField txtFindProduct3;
    private javax.swing.JTextField txtNameProduct;
    // End of variables declaration//GEN-END:variables
}
