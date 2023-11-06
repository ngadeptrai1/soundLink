package com.poly.views;

import com.poly.model.Brand;
import com.poly.model.Category;
import com.poly.model.Color;
import com.poly.model.Design;
import com.poy.service.Impl.TypeProductServiceImpl;
import com.poly.model.TypeProduct;
import com.poy.service.CRUDService;
import com.poy.service.Impl.BrandServiceImpl;
import com.poy.service.Impl.CategoryServiceImpl;
import com.poy.service.Impl.ColorServiceImpl;
import com.poy.service.Impl.DesignServiceImpl;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainJframe extends javax.swing.JFrame {

    private int ind = -1;
    DefaultTableModel model = new DefaultTableModel();
    private CRUDService crud;
    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyys");
    private String curenttAttr;
    private Brand brandData = null;
    private Color colorData = null;
    private Design degisData = null;
    private TypeProduct typeData = null;
    private Category cateData = null;

    public MainJframe() {
        initComponents();
        setLocationRelativeTo(null);
        checkRdo();
    }

    void showData() {
        checkRdo();
        if (curenttAttr.equals("BR")) {
            brandData = (Brand) crud.findAll().get(ind);
            txtDes.setText(brandData.getDescription());
            txtName.setText(brandData.getBrandName());
            chkActivated.setSelected(brandData.isActivated());
        }
        if (curenttAttr.equals("COLOR")) {
            colorData = (Color) crud.findAll().get(ind);
            txtDes.setText("");
            txtName.setText(colorData.getColor());
            chkActivated.setSelected(colorData.isActivated());
        }
        if (curenttAttr.equals("DE")) {
            degisData = (Design) crud.findAll().get(ind);
            txtDes.setText(degisData.getDescription());
            txtName.setText(degisData.getDescription());
            chkActivated.setSelected(degisData.isActivated());
        }
        if (curenttAttr.equals("CATE")) {
            cateData = (Category) crud.findAll().get(ind);
            txtDes.setText(cateData.getDescribe());
            txtName.setText(cateData.getName());
            chkActivated.setSelected(false);
        }
        if (curenttAttr.equals("TYPE")) {
            typeData = (TypeProduct) crud.findAll().get(ind);
            txtDes.setText(typeData.getDescription());
            txtName.setText(typeData.getName());
            chkActivated.setSelected(false);
        }
    }

    void fillToTableBrand(List<Brand> list) {
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        for (Brand p : list) {
            model.addRow(new Object[]{p.getBrandName(), p.getDescription(), fmt.format(p.getDateCreated())});
        }
    }

    void fillToTableDesign(List<Design> list) {
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        for (Design p : list) {
            model.addRow(new Object[]{p.getName(), p.getDescription(), " "});
        }
    }

    void fillToTableTypeProduct(List<TypeProduct> list) {
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        for (TypeProduct p : list) {
            model.addRow(new Object[]{p.getName(), p.getDescription(), fmt.format(p.getCreatedTime())});
        }
    }

    void fillToTableColor(List<Color> list) {
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        for (Color p : list) {
            model.addRow(new Object[]{p.getColor(), "", ""});
        }
    }

    void fillToTableCate(List<Category> list) {
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        for (Category p : list) {
            model.addRow(new Object[]{p.getName(), p.getDescribe(), fmt.format(p.getCreatedTime())});
        }
    }

    void checkRdo() {
        if (rdoBrand.isSelected()) {
            crud = new BrandServiceImpl();
            curenttAttr = "BR";
            fillToTableBrand(crud.findAll());
        }
        if (rdoCate.isSelected()) {
            crud = new CategoryServiceImpl();
            curenttAttr = "CATE";
            fillToTableCate(crud.findAll());
        }
        if (rdoColor.isSelected()) {
            crud = new ColorServiceImpl();
            curenttAttr = "COLOR";
            fillToTableColor(crud.findAll());
        }
        if (rdoDesign.isSelected()) {
            crud = new DesignServiceImpl();
            curenttAttr = "DE";
            fillToTableDesign(crud.findAll());
        }
        if (rdoType.isSelected()) {
            crud = new TypeProductServiceImpl();
            curenttAttr = "TYPE";
            fillToTableTypeProduct(crud.findAll());
        }
    }

    void checkAdd() {
        if (curenttAttr.equals("BR")) {
            brandData = new Brand(null, txtName.getText(), txtDes.getText(), null, chkActivated.isSelected());
        }
        if (curenttAttr.equals("COLOR")) {
            colorData = new Color(null, txtName.getText(), chkActivated.isSelected());
        }
        if (curenttAttr.equals("DE")) {
            degisData = new Design(null, txtName.getText(), txtDes.getText(), chkActivated.isSelected());
        }
        if (curenttAttr.equals("CATE")) {
            cateData = new Category(null, txtName.getText(), txtDes.getText(), null);
        }
        if (curenttAttr.equals("TYPE")) {
            typeData = new TypeProduct(null, txtName.getText(), txtDes.getText(), null);
        }
    }

    void add() {
        if (txtName.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
        } else {
            int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm");
            checkRdo();
            checkAdd();
            if (flag == 0) {
                if (curenttAttr.equals("BR")) {
                    crud.create(brandData);
                    fillToTableBrand(crud.findAll());
                }
                if (curenttAttr.equals("COLOR")) {
                    crud.create(colorData);
                    fillToTableColor(crud.findAll());
                }
                if (curenttAttr.equals("DE")) {
                    crud.create(degisData);
                    fillToTableDesign(crud.findAll());
                }
                if (curenttAttr.equals("CATE")) {
                    crud.create(cateData);
                    fillToTableCate(crud.findAll());
                }
                if (curenttAttr.equals("TYPE")) {
                    crud.create(typeData);
                    fillToTableTypeProduct(crud.findAll());
                }
            }
        }

    }

    void update() {
        if(ind <0){
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn bảng nào ");
        }else
        if (txtName.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
        } else {
            int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa ");
            checkRdo();
            checkAdd();
            if (flag == 0) {
                if (curenttAttr.equals("BR")) {
                    brandData = (Brand) crud.findAll().get(ind);
                    brandData.setBrandName(txtName.getText());
                    brandData.setActivated(chkActivated.isSelected());
                    brandData.setDescription(txtDes.getText());
                    int record = crud.update(brandData);
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công ");
                        fillToTableBrand(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại  ");
                    }
                    fillToTableBrand(crud.findAll());
                }
                if (curenttAttr.equals("COLOR")) {
                     colorData = (Color) crud.findAll().get(ind);
                     colorData.setColor(txtName.getText());
                     colorData.setActivated(chkActivated.isSelected());
                    int record = crud.update(colorData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công ");
                        fillToTableColor(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại  ");
                    }
                    
                }
                if (curenttAttr.equals("DE")) {
                    degisData = (Design) crud.findAll().get(ind);
                    degisData.setActivated(chkActivated.isSelected());
                    degisData.setName(txtName.getText());
                    degisData.setDescription(txtDes.getText());
                    int record = crud.update(degisData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công ");
                        fillToTableDesign(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại  ");
                    }
                }
                if (curenttAttr.equals("CATE")) {
                    cateData = (Category) crud.findAll().get(ind);
                    cateData.setDescribe(txtDes.getText());
                    cateData.setName(txtName.getText());
                    
                    int record = crud.update(cateData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công ");
                        fillToTableCate(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại  ");
                    }
                }
                if (curenttAttr.equals("TYPE")) {
                   typeData = (TypeProduct) crud.findAll().get(ind);
                   typeData.setDescription(txtDes.getName());
                   typeData.setName(txtName.getText());
                  
                    int record = crud.update(typeData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công ");
                        fillToTableTypeProduct(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại  ");
                    }
                }
            }
        }
    }

    void remove() {
        int cf = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa ? ");
        checkRdo();
        if (ind > 0) {
            if (cf == 0) {
                if (curenttAttr.equals("BR")) {
                    brandData = (Brand) crud.findAll().get(ind);
                    int record = crud.remove(brandData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công ");
                        fillToTableBrand(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại  ");
                    }
                }
                if (curenttAttr.equals("COLOR")) {
                    colorData = (Color) crud.findAll().get(ind);
                    int record = crud.remove(colorData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công ");
                        fillToTableColor(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại  ");
                    }
                }
                if (curenttAttr.equals("DE")) {
                    degisData = (Design) crud.findAll().get(ind);
                    int record = crud.remove(degisData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công ");
                        fillToTableDesign(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại  ");
                    }
                }
                if (curenttAttr.equals("CATE")) {
                    cateData = (Category) crud.findAll().get(ind);
                    int record = crud.remove(cateData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công ");
                        fillToTableCate(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại  ");
                    }
                }
                if (curenttAttr.equals("TYPE")) {
                    typeData = (TypeProduct) crud.findAll().get(ind);
                    int record = crud.remove(typeData.getId() + "");
                    if (record > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công ");
                        fillToTableTypeProduct(crud.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại  ");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào ");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pBanHang = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        pBanHang1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        pBanHang4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        pBanHang5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        pBanHang6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        pBanHang7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        pBanHang8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pBanHang9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tabpanel = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        rdoCate = new javax.swing.JRadioButton();
        rdoBrand = new javax.swing.JRadioButton();
        rdoColor = new javax.swing.JRadioButton();
        rdoType = new javax.swing.JRadioButton();
        rdoDesign = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDes = new javax.swing.JTextArea();
        chkActivated = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        btn_addProduct = new javax.swing.JButton();
        btn_updateProduct = new javax.swing.JButton();
        btn_removeProduct = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 204, 153));

        pBanHang.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang.setForeground(new java.awt.Color(204, 255, 255));
        pBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBanHangMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 255, 255));
        jLabel10.setText("Bán hàng");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pBanHangLayout = new javax.swing.GroupLayout(pBanHang);
        pBanHang.setLayout(pBanHangLayout);
        pBanHangLayout.setHorizontalGroup(
            pBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(16, 16, 16))
        );
        pBanHangLayout.setVerticalGroup(
            pBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        pBanHang1.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang1.setForeground(new java.awt.Color(204, 255, 255));
        pBanHang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBanHang1MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 255, 255));
        jLabel18.setText("Sản phẩm");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pBanHang1Layout = new javax.swing.GroupLayout(pBanHang1);
        pBanHang1.setLayout(pBanHang1Layout);
        pBanHang1Layout.setHorizontalGroup(
            pBanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang1Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addContainerGap())
        );
        pBanHang1Layout.setVerticalGroup(
            pBanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBanHang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        pBanHang4.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang4.setForeground(new java.awt.Color(204, 255, 255));
        pBanHang4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBanHang4MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 255, 255));
        jLabel21.setText("Hóa đơn");

        javax.swing.GroupLayout pBanHang4Layout = new javax.swing.GroupLayout(pBanHang4);
        pBanHang4.setLayout(pBanHang4Layout);
        pBanHang4Layout.setHorizontalGroup(
            pBanHang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );
        pBanHang4Layout.setVerticalGroup(
            pBanHang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBanHang4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        pBanHang5.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang5.setForeground(new java.awt.Color(204, 255, 255));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 255, 255));
        jLabel22.setText("Giảm giá");

        javax.swing.GroupLayout pBanHang5Layout = new javax.swing.GroupLayout(pBanHang5);
        pBanHang5.setLayout(pBanHang5Layout);
        pBanHang5Layout.setHorizontalGroup(
            pBanHang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap())
        );
        pBanHang5Layout.setVerticalGroup(
            pBanHang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBanHang5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        pBanHang6.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang6.setForeground(new java.awt.Color(204, 255, 255));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 255, 255));
        jLabel23.setText("Nhân viên");

        javax.swing.GroupLayout pBanHang6Layout = new javax.swing.GroupLayout(pBanHang6);
        pBanHang6.setLayout(pBanHang6Layout);
        pBanHang6Layout.setHorizontalGroup(
            pBanHang6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );
        pBanHang6Layout.setVerticalGroup(
            pBanHang6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        pBanHang7.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang7.setForeground(new java.awt.Color(204, 255, 255));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 255, 255));
        jLabel24.setText("Khách hàng");

        javax.swing.GroupLayout pBanHang7Layout = new javax.swing.GroupLayout(pBanHang7);
        pBanHang7.setLayout(pBanHang7Layout);
        pBanHang7Layout.setHorizontalGroup(
            pBanHang7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );
        pBanHang7Layout.setVerticalGroup(
            pBanHang7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBanHang7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        pBanHang8.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang8.setForeground(new java.awt.Color(204, 255, 255));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 255, 255));
        jLabel25.setText("Thống kê");

        javax.swing.GroupLayout pBanHang8Layout = new javax.swing.GroupLayout(pBanHang8);
        pBanHang8.setLayout(pBanHang8Layout);
        pBanHang8Layout.setHorizontalGroup(
            pBanHang8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pBanHang8Layout.setVerticalGroup(
            pBanHang8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBanHang8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 255, 255));
        jButton1.setText("Đăng Xuất");

        pBanHang9.setBackground(new java.awt.Color(204, 51, 0));
        pBanHang9.setForeground(new java.awt.Color(204, 255, 255));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 255, 255));
        jLabel26.setText("Bảo hành");

        javax.swing.GroupLayout pBanHang9Layout = new javax.swing.GroupLayout(pBanHang9);
        pBanHang9.setLayout(pBanHang9Layout);
        pBanHang9Layout.setHorizontalGroup(
            pBanHang9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addContainerGap())
        );
        pBanHang9Layout.setVerticalGroup(
            pBanHang9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pBanHang9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBanHang7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBanHang5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBanHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBanHang4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBanHang8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBanHang6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addComponent(pBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pBanHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pBanHang4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pBanHang5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pBanHang7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pBanHang8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pBanHang6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pBanHang9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 780));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));
        jPanel2.setForeground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1090, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("Ban hang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(491, 491, 491)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(539, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(jLabel1)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        tabpanel.addTab("banhang", jPanel4);

        jTabbedPane3.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(255, 204, 51));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 102, 0));
        jLabel4.setText("Sản phẩm");

        jPanel22.setBackground(new java.awt.Color(204, 255, 204));
        jPanel22.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel23.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(931, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        jPanel24.setBackground(new java.awt.Color(204, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(204, 153, 0))); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(955, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Sản phẩm", jPanel21);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(485, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Sản phẩm chi tiết", jPanel25);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jPanel28.setBackground(new java.awt.Color(242, 245, 236));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 204), new java.awt.Color(255, 188, 183), new java.awt.Color(204, 204, 0), new java.awt.Color(153, 51, 0)), "Thuộc tính sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(255, 153, 0))); // NOI18N

        buttonGroup1.add(rdoCate);
        rdoCate.setText("Danh mục");
        rdoCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCateActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoBrand);
        rdoBrand.setSelected(true);
        rdoBrand.setText("Hãng");
        rdoBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoBrandActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoColor);
        rdoColor.setText("Màu sắc");
        rdoColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoColorActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoType);
        rdoType.setText("Thể loại");
        rdoType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTypeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDesign);
        rdoDesign.setText("Kiểu dáng");
        rdoDesign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDesignActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 0));
        jLabel5.setText("Tên thuộc tính : ");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 0));
        jLabel6.setText("Miêu tả : ");

        txtDes.setColumns(20);
        txtDes.setRows(5);
        jScrollPane5.setViewportView(txtDes);

        chkActivated.setText("Hoạt động");

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Cập nhật");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Xóa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoBrand)
                            .addComponent(rdoCate))
                        .addGap(120, 120, 120)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoType)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(rdoColor)
                                .addGap(52, 52, 52)
                                .addComponent(rdoDesign)))
                        .addGap(60, 60, 60))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkActivated, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkActivated))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoBrand)
                            .addComponent(rdoColor)
                            .addComponent(rdoDesign))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoCate)
                            .addComponent(rdoType))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên", "Miêu tả", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblList);

        jPanel29.setBackground(new java.awt.Color(204, 242, 236));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 225, 160), new java.awt.Color(255, 153, 0)), "Tìm thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        jButton3.setBackground(new java.awt.Color(153, 255, 153));
        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 153, 0));
        jButton3.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        btn_addProduct.setText("Thêm");
        btn_addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addProductActionPerformed(evt);
            }
        });

        btn_updateProduct.setText("Sửa");
        btn_updateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateProductActionPerformed(evt);
            }
        });

        btn_removeProduct.setText("Xóa");
        btn_removeProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jScrollPane6)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addComponent(btn_addProduct)
                                .addGap(40, 40, 40)
                                .addComponent(btn_updateProduct)
                                .addGap(35, 35, 35)
                                .addComponent(btn_removeProduct)
                                .addContainerGap())))))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addProduct)
                    .addComponent(btn_updateProduct)
                    .addComponent(btn_removeProduct))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Thuộc tính", jPanel27);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        tabpanel.addTab("sanpham", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setText("Hoa don");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(jLabel2)
                .addContainerGap(544, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel2)
                .addContainerGap(413, Short.MAX_VALUE))
        );

        tabpanel.addTab("hoadon", jPanel6);

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setText("Giam gia");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(511, 511, 511)
                .addComponent(jLabel3)
                .addContainerGap(532, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(jLabel3)
                .addContainerGap(404, Short.MAX_VALUE))
        );

        tabpanel.addTab("giamgia", jPanel7);

        jLabel7.setText("Khach hang");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(465, 465, 465)
                .addComponent(jLabel7)
                .addContainerGap(562, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jLabel7)
                .addContainerGap(420, Short.MAX_VALUE))
        );

        tabpanel.addTab("khachhang", jPanel8);

        jLabel8.setText("thong kê");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(503, 503, 503)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(528, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jLabel8)
                .addContainerGap(402, Short.MAX_VALUE))
        );

        tabpanel.addTab("thongke", jPanel9);

        jLabel9.setText("nhan vien");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(475, 475, 475)
                .addComponent(jLabel9)
                .addContainerGap(563, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(jLabel9)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        tabpanel.addTab("nhanvien", jPanel10);

        jLabel11.setText("bao hanh");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(473, 473, 473)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel11)
                .addContainerGap(387, Short.MAX_VALUE))
        );

        tabpanel.addTab("baohanh", jPanel11);

        jPanel1.add(tabpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 1090, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCateActionPerformed
        // TODO add your handling code here:
        crud = new CategoryServiceImpl();
        curenttAttr = "CATE";
        fillToTableCate(crud.findAll());
    }//GEN-LAST:event_rdoCateActionPerformed

    private void rdoBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoBrandActionPerformed
        // TODO add your handling code here:
        crud = new BrandServiceImpl();
        curenttAttr = "BR";
        fillToTableBrand(crud.findAll());
    }//GEN-LAST:event_rdoBrandActionPerformed

    private void rdoColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoColorActionPerformed
        // TODO add your handling code here:
        crud = new ColorServiceImpl();
        curenttAttr = "COLOR";
        fillToTableColor(crud.findAll());
    }//GEN-LAST:event_rdoColorActionPerformed

    private void rdoTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTypeActionPerformed
        // TODO add your handling code here:
        crud = new TypeProductServiceImpl();
        curenttAttr = "TYPE";
        fillToTableTypeProduct(crud.findAll());
    }//GEN-LAST:event_rdoTypeActionPerformed

    private void rdoDesignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDesignActionPerformed
        // TODO add your handling code here:
        crud = new DesignServiceImpl();
        curenttAttr = "DE";
        fillToTableDesign(crud.findAll());
    }//GEN-LAST:event_rdoDesignActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        tabpanel.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void pBanHang4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBanHang4MouseClicked
        // TODO add your handling code here:
        tabpanel.setSelectedIndex(0);
    }//GEN-LAST:event_pBanHang4MouseClicked

    private void pBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBanHangMouseClicked
        // TODO add your handling code here:
        tabpanel.setSelectedIndex(0);
    }//GEN-LAST:event_pBanHangMouseClicked

    private void btn_updateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateProductActionPerformed
        //int check_updateProduct = 

    }//GEN-LAST:event_btn_updateProductActionPerformed

    private void btn_removeProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeProductActionPerformed

    }//GEN-LAST:event_btn_removeProductActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        ind = tblList.getSelectedRow();
        showData();
    }//GEN-LAST:event_tblListMouseClicked

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked

    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void btn_addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addProductActionPerformed
        //int check_addProduct = 

    }//GEN-LAST:event_btn_addProductActionPerformed

    private void pBanHang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBanHang1MouseClicked
        // TODO add your handling code here:
        tabpanel.setSelectedIndex(1);
    }//GEN-LAST:event_pBanHang1MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        tabpanel.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        remove();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addProduct;
    private javax.swing.JButton btn_removeProduct;
    private javax.swing.JButton btn_updateProduct;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkActivated;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel pBanHang;
    private javax.swing.JPanel pBanHang1;
    private javax.swing.JPanel pBanHang4;
    private javax.swing.JPanel pBanHang5;
    private javax.swing.JPanel pBanHang6;
    private javax.swing.JPanel pBanHang7;
    private javax.swing.JPanel pBanHang8;
    private javax.swing.JPanel pBanHang9;
    private javax.swing.JRadioButton rdoBrand;
    private javax.swing.JRadioButton rdoCate;
    private javax.swing.JRadioButton rdoColor;
    private javax.swing.JRadioButton rdoDesign;
    private javax.swing.JRadioButton rdoType;
    private javax.swing.JTabbedPane tabpanel;
    private javax.swing.JTable tblList;
    private javax.swing.JTextArea txtDes;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
