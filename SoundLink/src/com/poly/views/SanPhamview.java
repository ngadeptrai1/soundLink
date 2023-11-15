/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.poly.views;

import com.poly.model.Brand;
import com.poly.model.Category;
import com.poly.model.Color;
import com.poly.model.Design;
import com.poly.model.FrequencyRange;
import com.poly.model.Product;
import com.poly.model.ProductDetails;
import com.poly.model.TotalPower;
import com.poly.model.TypeProduct;
import com.poly.ultis.XImage;
import com.poy.service.CRUDService;
import com.poy.service.Impl.BrandServiceImpl;
import com.poy.service.Impl.CategoryServiceImpl;
import com.poy.service.Impl.ColorServiceImpl;
import com.poy.service.Impl.DesignServiceImpl;
import com.poy.service.Impl.FrequencyRangeImpl;
import com.poy.service.Impl.ProductDetailImpl;
import com.poy.service.Impl.ProductServiceImpl;
import com.poy.service.Impl.TotalPowerImpl;
import com.poy.service.Impl.TypeProductServiceImpl;
import com.poy.service.ProductDeatailsService;
import com.poy.service.ProductService;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class SanPhamview extends javax.swing.JInternalFrame {
    
    private int ind = -1;
    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    private CRUDService crud;
    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private String curenttAttr;
    private List<Object[]> currentList = null;
    private int totalPage;
    private int currentPage = 0;
    ProductService prDao = new ProductServiceImpl();
    JFileChooser js = new JFileChooser();
    List<Object[]> lit;
    private int SelectedProduct;
  
    ProductDeatailsService productDeatailDao = new ProductDetailImpl();

    /**
     * Creates new form test
     */
    public SanPhamview() {
        initComponents();
        setResizable(false);
        changePanelProduct();
        jTabbedPane3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jTabbedPane3.getSelectedIndex() == 0) {
                    changePanelProduct();
                } else if (jTabbedPane3.getSelectedIndex() == 1) {
                    changePanelProductDetail();
                } else if (jTabbedPane3.getSelectedIndex() == 2) {
                    changePanelAttribute();
                }
            }
        });
    }
    
    void changePanelProduct() {
        currentPage = 0;
        setcbo1();
        setTotalPageProduct();
        setTextProduct();
        fillToTableProduct();
    }
    
    void changePanelAttribute() {
        currentPage = 0;
        rdoBrand.setSelected(true);
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }
    
    void changePanelProductDetail() {
        
        setLabelProductDetail();
        currentPage = 0;
        setTotalPageProductDetail();
        setTextProductDetail();
        fillTotableProductDetail();
        System.out.println(SelectedProduct);
        System.out.println(getDataFindProductDetail().toString());
        
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
            lblImageProduct.setIcon(XImage.read(pr.getThumnail()));
            System.out.println(pr.getThumnail());
        } else {
            lblImageProduct.setToolTipText(null);
            lblImageProduct.setIcon(null);
            lblImageProduct.setText("trống");
            
        }
    }
    
    void clear() {
        tblListProduct.clearSelection();
        tblList.clearSelection();
        txtDes.setText("");
        txtDesProduct.setText("");
        txtFindProduct.setText("");
        txtName.setText("");
        txtSearch.setText("");
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
        chkActivated.setSelected(false);
        chkActi.setSelected(false);
        cboColor.setSelectedIndex(0);
        txtPriceProductDetail.setText("");
        txtQuantityProductDetail.setText("");
        chkActivatedProductDeatil.setSelected(false);
        lblImageProductDetail.setToolTipText(null);
        lblImageProduct.setToolTipText(null);
        lblImageProduct.setIcon(null);
        lblImageProductDetail.setIcon(null);
        rdoActivatedProductDetail.setSelected(true);
    }
    
    void removeProductDetail() {
        
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
    
    void setText() {
        if (crud.getTotalPage(txtSearch.getText()) == 0) {
            lblPage.setText("Hiện không có sản phẩm nào ");
        } else {
            lblPage.setText("Trang số " + (currentPage + 1) + " Trên " + totalPage);
        }
    }
    
    void setTextProduct() {
        if (prDao.getTotalPage(getDataFind()) == 0) {
            lblPageProduct.setText("Hiện không có sản phẩm nào ");
        } else {
            lblPageProduct.setText("Trang số " + (currentPage + 1) + " Trên " + totalPage);
        }
    }
    
    void setLabelProductDetail() {
        
        if (SelectedProduct > 0) {
            Product pr = prDao.findById(SelectedProduct);
            jLabel4.setText("Bạn đang chọn : " + pr.getName());
        } else {
            jLabel4.setText("Bạn chưa chọn sản phẩm nào");
        }
        
    }
    
    Product getDataFind() {
        Product pr = new Product();
        pr.setName(txtFindProduct.getText());
        pr.setActivated(true);
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
            lblImageProduct.setIcon(icon);
            lblImageProduct.setToolTipText(file.getName());
        }
        
    }
    
    void setImageProductDetail() {
        JFileChooser filechooser = new JFileChooser();
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblImageProductDetail.setIcon(icon);
            lblImageProductDetail.setToolTipText(file.getName());
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
        model1 = (DefaultTableModel) tblList.getModel();
        model1.setRowCount(0);
        for (Object[] p : currentList) {
            model1.addRow(new Object[]{
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
    
    void setcbo1() {
        crud = new DesignServiceImpl();
        lit = crud.findAllActivate();
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
        cboBrandSearch.addItem("");
        for (Object[] oj : lit) {
            cboBrand.addItem((String) oj[1]);
            cboBrandSearch.addItem((String) oj[1]);
        }
        setCob7();
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
    
    void fillTotableProductDetail() {
        DefaultTableModel modell = (DefaultTableModel) tblListProductDetail.getModel();
        modell.setRowCount(0);
        for (ProductDetails prdt : productDeatailDao.getALlByProduct(SelectedProduct, getDataFindProductDetail(), currentPage)) {
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
        prdt.setProductId(SelectedProduct);
        prdt.setThumnail(lblImageProductDetail.getToolTipText());
        prdt.setQuantity(Integer.parseInt(txtQuantityProductDetail.getText()));
        return prdt;
    }
    
    void setTextProductDetail() {
        
        if (SelectedProduct < 0) {
            lblProductDeatil.setText("Hiện không có sản phẩm nào");
        } else {
            if (productDeatailDao.getALlByProduct(SelectedProduct, getDataFindProductDetail(), currentPage).size() == 0) {
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
        
        if (productDeatailDao.getTotalPage(SelectedProduct, getDataFindProductDetail()) > 5) {
            totalPage = productDeatailDao.getTotalPage(SelectedProduct, getDataFindProductDetail()) / 5;
            if (productDeatailDao.getTotalPage(SelectedProduct, getDataFindProductDetail()) % 5 != 0) {
                totalPage++;
            }
        } else {
            totalPage = 1;
        }
    }
    
    void addProductDetail() {
        if (SelectedProduct <= 0) {
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
        if (SelectedProduct <= 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào !");
        } else {
            if (ind < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào ");
            } else {
                if (validateProductDetail()) {
                    int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn câp nhật sản phẩm không ? ");
                    if (flag == 0) {
                        ProductDetails pr = getDataForm();
                        pr.setId(productDeatailDao.getALlByProduct(SelectedProduct, getDataFindProductDetail(), currentPage).get(ind).getId());
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
        ProductDetails pr = productDeatailDao.getALlByProduct(SelectedProduct, getDataFindProductDetail(), currentPage).get(index);
        txtPriceProductDetail.setText(pr.getPrice().toString());
        txtQuantityProductDetail.setText(pr.getQuantity().toString());
        cboColor.setSelectedItem(pr.getColorName());
        chkActivatedProductDeatil.setSelected(pr.getActivated());
       if (pr.getThumnail() != null) {
            lblImageProductDetail.setToolTipText(pr.getThumnail());
            lblImageProductDetail.setIcon(XImage.read(pr.getThumnail()));
            System.out.println(pr.getThumnail());
        } else {
            lblImageProductDetail.setToolTipText(null);
            lblImageProductDetail.setIcon(null);
            lblImageProductDetail.setText("trống");
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbllistsp = new javax.swing.JTable();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
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
        btnChiTietSP = new javax.swing.JButton();
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
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtFindProduct = new javax.swing.JTextField();
        btnFindProduct = new javax.swing.JButton();
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
        btnLastPageProduct = new javax.swing.JButton();
        btnNextPageProduct = new javax.swing.JButton();
        lblPageProduct = new javax.swing.JLabel();
        btnPrevPageProduct = new javax.swing.JButton();
        btnFirstPageProduct = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
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
        btnFirstProductDetail = new javax.swing.JButton();
        btnPrevProductDetail = new javax.swing.JButton();
        btnNextProductDetail = new javax.swing.JButton();
        btnLastProductDetal = new javax.swing.JButton();
        lblProductDeatil = new javax.swing.JLabel();
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

        tbllistsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên", "Mô tả", "Trạng thái", "Số lượng"
            }
        ));
        jScrollPane4.setViewportView(tbllistsp);

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane3.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

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

        btnChiTietSP.setBackground(new java.awt.Color(255, 204, 0));
        btnChiTietSP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnChiTietSP.setForeground(new java.awt.Color(51, 51, 51));
        btnChiTietSP.setText("Chi tiết sản phẩm");
        btnChiTietSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSPActionPerformed(evt);
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
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                .addGap(102, 102, 102)
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(cboDesign, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKieuDang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(cboCate, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChiTietSP)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnLamMoi)
                                .addGap(12, 12, 12)
                                .addComponent(btnAddProduct)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                                .addComponent(btnChiTietSP)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Tìm kiếm sản phẩm :");

        txtFindProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindProductKeyReleased(evt);
            }
        });

        btnFindProduct.setText("Tìm");
        btnFindProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindProduct))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Danh mục :");

        cboCateSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCateSearchItemStateChanged(evt);
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

        buttonGroup2.add(rdoProductTrue);
        rdoProductTrue.setText("Đang bán");

        buttonGroup2.add(rdoProductFalse);
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
        tblListProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListProduct);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboDesignSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel40)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboBrandSearch, 0, 134, Short.MAX_VALUE)
                    .addComponent(cboTotalSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboFreqSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoProductTrue)
                        .addGap(18, 18, 18)
                        .addComponent(rdoProductFalse)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(cboFreqSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDesignSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel44)
                            .addComponent(rdoProductTrue)
                            .addComponent(rdoProductFalse)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cboCateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboBrandSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(cboTotalSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        btnLastPageProduct.setText(">>");
        btnLastPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageProductActionPerformed(evt);
            }
        });

        btnNextPageProduct.setText(">");
        btnNextPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageProductActionPerformed(evt);
            }
        });

        lblPageProduct.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPageProduct.setForeground(new java.awt.Color(255, 0, 0));
        lblPageProduct.setText("jLabel12");

        btnPrevPageProduct.setText("<");
        btnPrevPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageProductActionPerformed(evt);
            }
        });

        btnFirstPageProduct.setText("<<");
        btnFirstPageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstPageProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFirstPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrevPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(lblPageProduct)
                .addGap(85, 85, 85)
                .addComponent(btnNextPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLastPageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(315, 315, 315))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextPageProduct)
                    .addComponent(btnLastPageProduct)
                    .addComponent(lblPageProduct)
                    .addComponent(btnPrevPageProduct)
                    .addComponent(btnFirstPageProduct))
                .addGap(323, 323, 323))
        );

        jTabbedPane3.addTab("Sản phẩm", jPanel21);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(27, 356, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkActivatedProductDeatil, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAddProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(btnUpdateProductDetail)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
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

        buttonGroup3.add(rdoActivatedProductDetail);
        rdoActivatedProductDetail.setSelected(true);
        rdoActivatedProductDetail.setText("Đang bán ");

        buttonGroup3.add(rdoProductDetail);
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
                .addContainerGap(195, Short.MAX_VALUE))
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
        tblListProductDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListProductDetailMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListProductDetail);

        btnFirstProductDetail.setText("<<");
        btnFirstProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstProductDetailActionPerformed(evt);
            }
        });

        btnPrevProductDetail.setText("<");
        btnPrevProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevProductDetailActionPerformed(evt);
            }
        });

        btnNextProductDetail.setText(">");
        btnNextProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextProductDetailActionPerformed(evt);
            }
        });

        btnLastProductDetal.setText(">>");
        btnLastProductDetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastProductDetalActionPerformed(evt);
            }
        });

        lblProductDeatil.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblProductDeatil.setForeground(new java.awt.Color(255, 51, 51));
        lblProductDeatil.setText("jLabel8");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(btnFirstProductDetail)
                .addGap(42, 42, 42)
                .addComponent(btnPrevProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(lblProductDeatil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNextProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLastProductDetal)
                .addGap(123, 123, 123))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirstProductDetail)
                    .addComponent(btnPrevProductDetail)
                    .addComponent(btnNextProductDetail)
                    .addComponent(btnLastProductDetal)
                    .addComponent(lblProductDeatil))
                .addContainerGap(70, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirstPage)
                    .addComponent(btnPrevPage)
                    .addComponent(btnNextPage)
                    .addComponent(btnLastPage)
                    .addComponent(lblPage))
                .addGap(60, 60, 60))
        );

        jTabbedPane3.addTab("Thuộc tính", jPanel27);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
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

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        ind = tblList.getSelectedRow();
        checkRdo();
        showData(ind);
    }//GEN-LAST:event_tblListMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        checkRdo();
        setTotalPage();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnFirstPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPageActionPerformed
        // TODO add your handling code here:
        currentPage = 0;
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnFirstPageActionPerformed

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

    private void btnLastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageActionPerformed
        // TODO add your handling code here:
        currentPage = totalPage - 1;
        checkRdo();
        setText();
        fillToTable();
    }//GEN-LAST:event_btnLastPageActionPerformed

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        

    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void btnChiTietSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSPActionPerformed
        jTabbedPane3.setSelectedIndex(1);
        setLabelProductDetail();
    }//GEN-LAST:event_btnChiTietSPActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        if (checkProduct()) {
            int flag = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm ?");
            if (flag == 0) {
                int count = prDao.create(getDataProduct());
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    setTotalPageProduct();
                    currentPage = 0;
                    setTextProduct();
                    ind = -1;
                    clear();
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

    private void cboCateSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboCateSearchItemStateChanged

    }//GEN-LAST:event_cboCateSearchItemStateChanged

    private void cboTypeSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTypeSearchItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_cboTypeSearchItemStateChanged

    private void txtFindProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindProductKeyReleased

    }//GEN-LAST:event_txtFindProductKeyReleased

    private void cboFreqSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFreqSearchItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_cboFreqSearchItemStateChanged

    private void cboTypeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTypeSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTypeSearchActionPerformed

    private void cboDesignSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDesignSearchItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDesignSearchItemStateChanged

    private void cboBrandSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboBrandSearchItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboBrandSearchItemStateChanged

    private void cboTotalSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTotalSearchItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTotalSearchItemStateChanged

    private void chkActiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkActiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkActiActionPerformed

    private void lblImageProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageProductMouseClicked
        // TODO add your handling code here:
        setImageProduct();
    }//GEN-LAST:event_lblImageProductMouseClicked

    private void tblListProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListProductMouseClicked
        // TODO add your handling code here:
        ind = tblListProduct.getSelectedRow();
        showDataProduct(ind);
        SelectedProduct = prDao.findAll(getDataFind(), currentPage).get(ind).getId();
        setLabelProductDetail();
    }//GEN-LAST:event_tblListProductMouseClicked

    private void btnFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProductActionPerformed
        // TODO add your handling code here:

        ind = 0;
        setTotalPageProduct();
        setTextProduct();
        currentPage = 0;
        fillToTableProduct();
        tblListProduct.clearSelection();

    }//GEN-LAST:event_btnFindProductActionPerformed

    private void btnLastPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageProductActionPerformed
        // TODO add your handling code here:
        currentPage = totalPage - 1;
        fillToTableProduct();
        setTextProduct();
    }//GEN-LAST:event_btnLastPageProductActionPerformed

    private void btnNextPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageProductActionPerformed
        // TODO add your handling code here:
        currentPage = currentPage + 1;
        if (currentPage > totalPage - 1) {
            currentPage = 0;
        }
        fillToTableProduct();
        setTextProduct();
    }//GEN-LAST:event_btnNextPageProductActionPerformed

    private void btnPrevPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageProductActionPerformed
        // TODO add your handling code here:

        currentPage = currentPage - 1;
        if (currentPage < 0) {
            currentPage = totalPage - 1;
        }
        fillToTableProduct();
        setTextProduct();

    }//GEN-LAST:event_btnPrevPageProductActionPerformed

    private void btnFirstPageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPageProductActionPerformed
        // TODO add your handling code here:
        currentPage = 0;
        setTextProduct();
        fillToTableProduct();
    }//GEN-LAST:event_btnFirstPageProductActionPerformed

    private void btnLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiActionPerformed
        // TODO add your handling code here:
        TypeProductsView tp = new TypeProductsView();
        tp.setVisible(true);
    }//GEN-LAST:event_btnLoaiActionPerformed

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
        DesignView designView = new DesignView();
        designView.setVisible(true);
    }//GEN-LAST:event_btnDanhMucActionPerformed

    private void btnKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKieuDangActionPerformed
        // TODO add your handling code here:
        TypeProductsView typeProductsView = new TypeProductsView();
        typeProductsView.setVisible(true);
    }//GEN-LAST:event_btnKieuDangActionPerformed

    private void btnRemoveProductDeatilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProductDeatilActionPerformed

    }//GEN-LAST:event_btnRemoveProductDeatilActionPerformed

    private void btnLamMoiSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPCTActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiSPCTActionPerformed

    private void btnUpdateProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductDetailActionPerformed
        updateProductDetail();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateProductDetailActionPerformed

    private void btnAddProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductDetailActionPerformed
        addProductDetail();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddProductDetailActionPerformed

    private void lblImageProductDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageProductDetailMouseClicked
        // TODO add your handling code here:
        setImageProductDetail();
    }//GEN-LAST:event_lblImageProductDetailMouseClicked

    private void tblListProductDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListProductDetailMouseClicked
        // TODO add your handling code here:
        ind = tblListProductDetail.getSelectedRow();
        showProductDetail(ind);
    }//GEN-LAST:event_tblListProductDetailMouseClicked

    private void btnFirstProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstProductDetailActionPerformed
        // TODO add your handling code here:
        currentPage = 0;
        setTextProductDetail();
        fillTotableProductDetail();
    }//GEN-LAST:event_btnFirstProductDetailActionPerformed

    private void btnPrevProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevProductDetailActionPerformed
        // TODO add your handling code here:

        currentPage = currentPage - 1;
        if (currentPage < 0) {
            currentPage = totalPage - 1;
        }
        fillTotableProductDetail();
        setTextProductDetail();
    }//GEN-LAST:event_btnPrevProductDetailActionPerformed

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

    private void btnLastProductDetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastProductDetalActionPerformed
        // TODO add your handling code here:
        currentPage = totalPage - 1;
        fillTotableProductDetail();
        setTextProductDetail();
    }//GEN-LAST:event_btnLastProductDetalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        fillTotableProductDetail();
        setTextProductDetail();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAddProductDetail;
    private javax.swing.JButton btnChiTietSP;
    private javax.swing.JButton btnDaiTan;
    private javax.swing.JButton btnDanhMuc;
    private javax.swing.JButton btnFindProduct;
    private javax.swing.JButton btnFirstPage;
    private javax.swing.JButton btnFirstPageProduct;
    private javax.swing.JButton btnFirstProductDetail;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnKieuDang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiSPCT;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnLastPageProduct;
    private javax.swing.JButton btnLastProductDetal;
    private javax.swing.JButton btnLoai;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnNextPageProduct;
    private javax.swing.JButton btnNextProductDetail;
    private javax.swing.JButton btnPrevPage;
    private javax.swing.JButton btnPrevPageProduct;
    private javax.swing.JButton btnPrevProductDetail;
    private javax.swing.JButton btnRemoveProductDeatil;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTongCongSuat;
    private javax.swing.JButton btnUpdateProductDetail;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboBrand;
    private javax.swing.JComboBox<String> cboBrandSearch;
    private javax.swing.JComboBox<String> cboCate;
    private javax.swing.JComboBox<String> cboCateSearch;
    private javax.swing.JComboBox<String> cboColor;
    private javax.swing.JComboBox<String> cboColorSearch;
    private javax.swing.JComboBox<String> cboDesign;
    private javax.swing.JComboBox<String> cboDesignSearch;
    private javax.swing.JComboBox<String> cboFreq;
    private javax.swing.JComboBox<String> cboFreqSearch;
    private javax.swing.JComboBox<String> cboKhoangGia;
    private javax.swing.JComboBox<String> cboTotal;
    private javax.swing.JComboBox<String> cboTotalSearch;
    private javax.swing.JComboBox<String> cboType;
    private javax.swing.JComboBox<String> cboTypeSearch;
    private javax.swing.JCheckBox chkActi;
    private javax.swing.JCheckBox chkActivated;
    private javax.swing.JCheckBox chkActivatedProductDeatil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblImageProduct;
    private javax.swing.JLabel lblImageProductDetail;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblPageProduct;
    private javax.swing.JLabel lblProductDeatil;
    private javax.swing.JRadioButton rdoActivatedProductDetail;
    private javax.swing.JRadioButton rdoBrand;
    private javax.swing.JRadioButton rdoCate;
    private javax.swing.JRadioButton rdoColor;
    private javax.swing.JRadioButton rdoDesign;
    private javax.swing.JRadioButton rdoFrequency;
    private javax.swing.JRadioButton rdoPower;
    private javax.swing.JRadioButton rdoProductDetail;
    private javax.swing.JRadioButton rdoProductFalse;
    private javax.swing.JRadioButton rdoProductTrue;
    private javax.swing.JRadioButton rdoType;
    private javax.swing.JTable tblList;
    private javax.swing.JTable tblListProduct;
    private javax.swing.JTable tblListProductDetail;
    private javax.swing.JTable tbllistsp;
    private javax.swing.JTextArea txtDes;
    private javax.swing.JTextArea txtDesProduct;
    private javax.swing.JTextField txtFindProduct;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNameProduct;
    private javax.swing.JTextField txtPriceProductDetail;
    private javax.swing.JTextField txtQuantityProductDetail;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
