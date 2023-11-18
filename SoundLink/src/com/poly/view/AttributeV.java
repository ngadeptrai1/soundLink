/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.view;

import com.poly.model.Brand;
import com.poly.model.Category;
import com.poly.model.Color;
import com.poly.model.Design;
import com.poly.model.FrequencyRange;
import com.poly.model.TotalPower;
import com.poly.model.TypeProduct;
import com.poy.service.CRUDService;
import com.poy.service.Impl.BrandServiceImpl;
import com.poy.service.Impl.CategoryServiceImpl;
import com.poy.service.Impl.ColorServiceImpl;
import com.poy.service.Impl.DesignServiceImpl;
import com.poy.service.Impl.FrequencyRangeImpl;
import com.poy.service.Impl.TotalPowerImpl;
import com.poy.service.Impl.TypeProductServiceImpl;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnha1
 */
public class AttributeV extends javax.swing.JPanel {

    private CRUDService crud;
    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private String curenttAttr;
    private List<Object[]> currentList = null;
    private int totalPage;
    private int currentPage = 0;
    private DefaultTableModel model;
    private int ind =-1;

    /**
     * Creates new form Attribute
     */
    public AttributeV() {
        initComponents();
         currentPage = 0;
        rdoBrand.setSelected(true);
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
        
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

    void setText() {
        if (crud.getTotalPage(txtSearch.getText()) == 0) {
            lblPage.setText("Hiện không có sản phẩm nào ");
        } else {
            lblPage.setText("Trang số " + (currentPage + 1) + " Trên " + totalPage);
        }
    }

    void clear() {

        tblList.clearSelection();
        txtDes.setText("");

        txtName.setText("");
        txtSearch.setText("");

        chkActivated.setSelected(false);

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
    
    private Object[] getData() {
        return new Object[]{
            txtName.getText(),
            txtDes.getText(),
            chkActivated.isSelected()
        };
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
        buttonGroup2 = new javax.swing.ButtonGroup();
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
        jPanel29 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        btnLastPage = new javax.swing.JButton();
        btnNextPage = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        btnPrevPage = new javax.swing.JButton();
        btnFirstPage = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
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
                .addContainerGap(433, Short.MAX_VALUE))
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
        tblList.setSelectionBackground(new java.awt.Color(255, 204, 153));
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblList);

        btnLastPage.setText(">>");
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });

        btnNextPage.setText(">");
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });

        lblPage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPage.setForeground(new java.awt.Color(255, 0, 0));
        lblPage.setText("jLabel12");

        btnPrevPage.setText("<");
        btnPrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageActionPerformed(evt);
            }
        });

        btnFirstPage.setText("<<");
        btnFirstPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstPageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(btnFirstPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnPrevPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(lblPage)
                        .addGap(62, 62, 62)
                        .addComponent(btnNextPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnLastPage, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLastPage)
                    .addComponent(btnNextPage)
                    .addComponent(lblPage)
                    .addComponent(btnPrevPage)
                    .addComponent(btnFirstPage))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCateActionPerformed
        // TODO add your handling code here:
        clear();
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_rdoCateActionPerformed

    private void rdoBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoBrandActionPerformed
        // TODO add your handling code here:
        clear();
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_rdoBrandActionPerformed

    private void rdoColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoColorActionPerformed
        clear();
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_rdoColorActionPerformed

    private void rdoTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTypeActionPerformed
        // TODO add your handling code here:
        clear();
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_rdoTypeActionPerformed

    private void rdoDesignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDesignActionPerformed
        // TODO add your handling code here:
        clear();
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_rdoDesignActionPerformed

    private void rdoPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPowerActionPerformed
        clear();
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_rdoPowerActionPerformed

    private void rdoFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoFrequencyActionPerformed
        clear();
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_rdoFrequencyActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        ind = tblList.getSelectedRow();
        checkRdo();
        showData(ind);
    }//GEN-LAST:event_tblListMouseClicked

    private void btnLastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageActionPerformed
        // TODO add your handling code here:
        currentPage = totalPage - 1;
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnLastPageActionPerformed

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

    private void btnFirstPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPageActionPerformed
        // TODO add your handling code here:
        currentPage = 0;
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnFirstPageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirstPage;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPrevPage;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkActivated;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblPage;
    private javax.swing.JRadioButton rdoBrand;
    private javax.swing.JRadioButton rdoCate;
    private javax.swing.JRadioButton rdoColor;
    private javax.swing.JRadioButton rdoDesign;
    private javax.swing.JRadioButton rdoFrequency;
    private javax.swing.JRadioButton rdoPower;
    private javax.swing.JRadioButton rdoType;
    private javax.swing.JTable tblList;
    private javax.swing.JTextArea txtDes;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
