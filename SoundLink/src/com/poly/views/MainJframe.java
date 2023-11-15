package com.poly.views;

import com.poly.model.Brand;
import com.poly.model.Category;
import com.poly.model.Color;
import com.poly.model.Design;
import com.poly.model.FrequencyRange;
import com.poly.model.TotalPower;
import com.poy.service.Impl.TypeProductServiceImpl;
import com.poly.model.TypeProduct;
import com.poy.service.CRUDService;
import com.poy.service.Impl.BrandServiceImpl;
import com.poy.service.Impl.CategoryServiceImpl;
import com.poy.service.Impl.ColorServiceImpl;
import com.poy.service.Impl.DesignServiceImpl;
import com.poy.service.Impl.FrequencyRangeImpl;
import com.poy.service.Impl.TotalPowerImpl;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class MainJframe extends javax.swing.JFrame {
    

    private int ind = -1;
    DefaultTableModel model = new DefaultTableModel();
    private CRUDService crud;
    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private String curenttAttr;
    private List<Object[]> currentList = null;
    private int totalPage;
    private int currentPage = 0;
    
   
     
    public MainJframe() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(this.NORMAL);
        this.setResizable(false);
        this.setTitle("Sound Link");
        checkRdo();
        setTotalPage();
        fillToTable();
        setText();
        this.setContentPane(new NewJPanel1());
    }

    
    void setText() {
        lblPage.setText("Trang số " + (currentPage + 1) + " Trên " + totalPage);
    }

    void setTotalPage() {
        if (crud.getTotalPage(txtSearch.getText()) > 5) {
            totalPage = crud.getTotalPage(txtSearch.getText()) / 5;
            if (crud.getTotalPage(txtSearch.getText()) % 5 != 0) {
                totalPage++;
            }
        } else {
            totalPage = 1;
        }

    }

    void clear() {
        txtSearch.setText("");
        txtName.setText("");
        txtDes.setText("");
        chkActivated.setSelected(false);
        currentPage = 0;
        checkRdo();
        setTotalPage();
        setText();
        ind = -1;
    }
    
    void convertListBrandToList(List<Brand> list) {
        currentList = list.stream()
                .map(item -> new Object[]{item.getId(), item.getName(), item.getDescription(), item.getDateCreated(), item.isActivated()})
                .collect(Collectors.toList());
    }

    void convertListCateToList(List<Category> list) {
        currentList = list.stream()
                .map(item -> new Object[]{item.getId(), item.getName(), item.getDescription(), item.getDateCreated(), item.isActivated()})
                .collect(Collectors.toList());
    }

    void convertListColorToList(List<Color> list) {
        currentList = list.stream()
                .map(item -> new Object[]{item.getId(), item.getName(), item.getDescription(), item.getDateCreated(), item.isActivated()})
                .collect(Collectors.toList());
    }

    void convertListDesignToList(List<Design> list) {
        currentList = list.stream()
                .map(item -> new Object[]{item.getId(), item.getName(), item.getDescription(), item.getDateCreated(), item.isActivated()})
                .collect(Collectors.toList());
    }

    void convertListTypeToList(List<TypeProduct> list) {
        currentList = list.stream()
                .map(item -> new Object[]{item.getId(), item.getName(), item.getDescription(), item.getDateCreated(), item.isActivated()})
                .collect(Collectors.toList());
    }

    void convertListFreToList(List<FrequencyRange> list) {
        currentList = list.stream()
                .map(item -> new Object[]{item.getId(), item.getName(), item.getDescription(), item.getDateCreated(), item.isActivated()})
                .collect(Collectors.toList());
    }

    void convertListTotalPowerToList(List<TotalPower> list) {
        currentList = list.stream()
                .map(item -> new Object[]{item.getId(), item.getName(), item.getDescription(), item.getDateCreated(), item.isActivated()})
                .collect(Collectors.toList());
    }

    void fillToTable() {
        model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        for (Object[] p : currentList) {
            model.addRow(new Object[]{
                p[1], p[2], p[3], (boolean) p[4] ? "Đang hoạt động" : "Ngừng"
            });
        }
    }

    void showData(int ind) {
        checkRdo();
        Object[] item = currentList.get(ind);
        txtName.setText(item[1].toString());
        txtDes.setText(item[2].toString());
        chkActivated.setSelected((boolean) item[4]);
    }

    void checkRdo() {
        if (rdoBrand.isSelected()) {
            crud = new BrandServiceImpl();
            convertListBrandToList(crud.findAll(currentPage, txtSearch.getText()));

        }
        if (rdoCate.isSelected()) {
            crud = new CategoryServiceImpl();

            convertListCateToList(crud.findAll(currentPage, txtSearch.getText()));
        }
        if (rdoColor.isSelected()) {
            crud = new ColorServiceImpl();

            convertListColorToList(crud.findAll(currentPage, txtSearch.getText()));
        }
        if (rdoDesign.isSelected()) {
            crud = new DesignServiceImpl();

            convertListDesignToList(crud.findAll(currentPage, txtSearch.getText()));
        }
        if (rdoType.isSelected()) {
            crud = new TypeProductServiceImpl();

            convertListTypeToList(crud.findAll(currentPage, txtSearch.getText()));
        }
        if (rdoPower.isSelected()) {
            crud = new TotalPowerImpl();

            convertListTotalPowerToList(crud.findAll(currentPage, txtSearch.getText()));
        }
        if (rdoFrequency.isSelected()) {
            crud = new FrequencyRangeImpl();

            convertListFreToList(crud.findAll(currentPage, txtSearch.getText()));
        }
    }

    private Object[] getData() {
        return new Object[]{
            txtName.getText(),
            txtDes.getText(),
            chkActivated.isSelected()
        };
    }

    void add() {
        if (txtName.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
        } else {
            int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm");
            checkRdo();
            if (flag == 0) {
                int quantity = crud.create(getData());
                if (quantity > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    ind = -1;
                    clear();
                    setText();
                    fillToTable();

                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công");

                }
                ind = -1;
            }
        }

    }

    void update() {
        if (ind < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bảng nào ");
        } else if (txtName.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
        } else {
            int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa ");
            checkRdo();
            if (flag == 0) {
                Object[] data = currentList.get(ind);
                data[1] = txtName.getText();
                data[2] = txtDes.getText();
                data[4] = chkActivated.isSelected();

                int quantity = crud.update(data);
                if (quantity > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    ind = -1;

                    clear();
                    setText();
                    fillToTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
                }
                ind = -1;
            }
        }
    }
    

    void remove() {
        int cf = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa ? ");
        checkRdo();
        if (ind > 0) {
            if (cf == 0) {

                int quantity = crud.remove(currentList.get(ind)[0].toString());
                if (quantity > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công ");
                    ind = -1;

                    clear();

                    fillToTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại  ");

                    clear();

                    fillToTable();
                    ind = -1;
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
        pBanHang6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        pBanHang5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
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
        nga = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        rdoCate = new javax.swing.JRadioButton();
        rdoBrand = new javax.swing.JRadioButton();
        rdoColor = new javax.swing.JRadioButton();
        rdoType = new javax.swing.JRadioButton();
        rdoDesign = new javax.swing.JRadioButton();
        rdoPower = new javax.swing.JRadioButton();
        rdoFrequency = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDes = new javax.swing.JTextArea();
        chkActivated = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnFirstPage = new javax.swing.JButton();
        btnPrevPage = new javax.swing.JButton();
        btnNextPage = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        MyJpanel = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
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

        pBanHang1.setBackground(new java.awt.Color(255, 255, 255));
        pBanHang1.setForeground(new java.awt.Color(102, 102, 102));
        pBanHang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBanHang1MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 0, 51));
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

        javax.swing.GroupLayout pBanHang4Layout = new javax.swing.GroupLayout(pBanHang4);
        pBanHang4.setLayout(pBanHang4Layout);
        pBanHang4Layout.setHorizontalGroup(
            pBanHang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBanHang4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
            .addGroup(pBanHang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pBanHang4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pBanHang6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pBanHang4Layout.setVerticalGroup(
            pBanHang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBanHang4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pBanHang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pBanHang4Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(pBanHang6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(221, Short.MAX_VALUE)
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
                .addGap(69, 69, 69)
                .addComponent(pBanHang9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 770));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));
        jPanel2.setForeground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1150, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("Ban hang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(491, 491, 491)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(599, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(jLabel1)
                .addContainerGap(435, Short.MAX_VALUE))
        );

        tabpanel.addTab("banhang", jPanel4);

        nga.setBackground(new java.awt.Color(204, 255, 204));
        nga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nga.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        nga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ngaMouseClicked(evt);
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
                .addContainerGap(991, Short.MAX_VALUE)
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
            .addGap(0, 104, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1015, Short.MAX_VALUE))
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

        nga.addTab("Sản phẩm", jPanel21);

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

        buttonGroup1.add(rdoPower);
        rdoPower.setText("Công Suất");
        rdoPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPowerActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoFrequency);
        rdoFrequency.setText("Tần Số");
        rdoFrequency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoFrequencyActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 0));
        jLabel5.setText("Tên thuộc tính : ");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 0));
        jLabel6.setText("Miêu tả : ");

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

        txtDes.setColumns(20);
        txtDes.setRows(5);
        jScrollPane5.setViewportView(txtDes);

        chkActivated.setText("Hoạt động");

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
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoBrand)
                                    .addComponent(rdoCate))
                                .addGap(120, 120, 120)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoColor)
                                    .addComponent(rdoType))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoDesign)
                                    .addComponent(rdoPower)))
                            .addComponent(rdoFrequency))
                        .addGap(38, 38, 38))
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
                            .addComponent(rdoType)
                            .addComponent(rdoPower))
                        .addGap(28, 28, 28)
                        .addComponent(rdoFrequency)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tblList.setAutoCreateRowSorter(true);
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên", "Miêu tả", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.setGridColor(new java.awt.Color(255, 204, 102));
        tblList.setSelectionBackground(new java.awt.Color(255, 204, 0));
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblList);

        jPanel29.setBackground(new java.awt.Color(204, 242, 236));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 225, 160), new java.awt.Color(255, 153, 0)), "Tìm thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        btnSearch.setBackground(new java.awt.Color(153, 255, 153));
        btnSearch.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(204, 153, 0));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        btnFirstPage.setText("<<");
        btnFirstPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstPageActionPerformed(evt);
            }
        });

        btnPrevPage.setText("<");
        btnPrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageActionPerformed(evt);
            }
        });

        btnNextPage.setText(">");
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });

        btnLastPage.setText(">>");
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });

        lblPage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPage.setForeground(new java.awt.Color(255, 0, 0));
        lblPage.setText("jLabel12");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(btnFirstPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrevPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(lblPage)
                .addGap(89, 89, 89)
                .addComponent(btnNextPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLastPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirstPage)
                    .addComponent(btnPrevPage)
                    .addComponent(btnNextPage)
                    .addComponent(btnLastPage)
                    .addComponent(lblPage))
                .addGap(60, 60, 60))
        );

        nga.addTab("Thuộc tính", jPanel27);

        MyJpanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MyJpanelLayout = new javax.swing.GroupLayout(MyJpanel);
        MyJpanel.setLayout(MyJpanelLayout);
        MyJpanelLayout.setHorizontalGroup(
            MyJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MyJpanelLayout.setVerticalGroup(
            MyJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyJpanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
        );

        nga.addTab("Sản phẩm chi tiết", MyJpanel);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nga)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nga)
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
                .addContainerGap(604, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel2)
                .addContainerGap(403, Short.MAX_VALUE))
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
                .addContainerGap(592, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(jLabel3)
                .addContainerGap(394, Short.MAX_VALUE))
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
                .addContainerGap(622, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jLabel7)
                .addContainerGap(410, Short.MAX_VALUE))
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
                .addContainerGap(588, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jLabel8)
                .addContainerGap(392, Short.MAX_VALUE))
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
                .addContainerGap(623, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(jLabel9)
                .addContainerGap(359, Short.MAX_VALUE))
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
                .addContainerGap(619, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel11)
                .addContainerGap(377, Short.MAX_VALUE))
        );

        tabpanel.addTab("baohanh", jPanel11);

        jPanel1.add(tabpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 1150, 760));

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
        clear();
        fillToTable();
    }//GEN-LAST:event_rdoCateActionPerformed

    private void rdoBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoBrandActionPerformed
        // TODO add your handling code here:
        clear();
        fillToTable();

    }//GEN-LAST:event_rdoBrandActionPerformed

    private void rdoColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoColorActionPerformed
        clear();
        fillToTable();
    }//GEN-LAST:event_rdoColorActionPerformed

    private void rdoTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTypeActionPerformed
        // TODO add your handling code here:
        clear();
        fillToTable();
    }//GEN-LAST:event_rdoTypeActionPerformed

    private void rdoDesignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDesignActionPerformed
        // TODO add your handling code here:
        clear();
        fillToTable();
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

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        ind = tblList.getSelectedRow();
        checkRdo();
        showData(ind);
    }//GEN-LAST:event_tblListMouseClicked

    private void ngaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ngaMouseClicked

    }//GEN-LAST:event_ngaMouseClicked

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
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rdoFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoFrequencyActionPerformed
        clear();
        fillToTable();
    }//GEN-LAST:event_rdoFrequencyActionPerformed

    private void rdoPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPowerActionPerformed
        clear();
        fillToTable();
    }//GEN-LAST:event_rdoPowerActionPerformed

    private void btnFirstPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPageActionPerformed
        // TODO add your handling code here:
        currentPage = 0;
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnFirstPageActionPerformed

    private void btnLastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageActionPerformed
        // TODO add your handling code here:
        currentPage = totalPage - 1;
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnLastPageActionPerformed

    private void btnPrevPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageActionPerformed
        // TODO add your handling code here:
        currentPage = currentPage - 1;
        if (currentPage < 0) {
            currentPage = totalPage - 1;
        }
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnPrevPageActionPerformed

    private void btnNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageActionPerformed
        // TODO add your handling code here:
        currentPage = currentPage + 1;
        if (currentPage > totalPage - 1) {
            currentPage = 0;
        }
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnNextPageActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
       checkRdo();
        fillToTable();
        setText();
    }//GEN-LAST:event_btnSearchActionPerformed

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
    public javax.swing.JPanel MyJpanel;
    private javax.swing.JButton btnFirstPage;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPrevPage;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkActivated;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblPage;
    private javax.swing.JTabbedPane nga;
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
    private javax.swing.JRadioButton rdoFrequency;
    private javax.swing.JRadioButton rdoPower;
    private javax.swing.JRadioButton rdoType;
    private javax.swing.JTabbedPane tabpanel;
    private javax.swing.JTable tblList;
    private javax.swing.JTextArea txtDes;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    
}
