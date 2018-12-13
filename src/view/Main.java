package view;

import controller.IOFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DanhSachMuaHang;
import model.KhachHang;
import model.MatHang;

/**
 *
 * @author ViNguyen
 */
public class Main extends javax.swing.JFrame {
    enum State{
        normal,
        themMatHang,
        suaMatHang,
        themKhachHang,
        suaKhachHang,
        themDS,
        suaDS
    }
    private State state;
    private ArrayList<MatHang> listMatHang = new ArrayList<>();
    private ArrayList<KhachHang> listKhachHang = new ArrayList<>();
    private ArrayList<DanhSachMuaHang> listDanhSach = new ArrayList<>();
    DefaultTableModel tm1,tm2,tm3;
    
    public Main() {
        initComponents();
        tm1 = (DefaultTableModel)jTable1.getModel();
        tm2 = (DefaultTableModel)jTable2.getModel();
        tm3 = (DefaultTableModel)jTable3.getModel();
        changeState(state.normal);
        IOFile.readFile(listMatHang,"MatHang.dat");
        IOFile.readFile(listKhachHang,"KhachHang.dat");
        IOFile.readFile(listDanhSach,"DanhSach.dat");
        hienthiMatHang();
        hienthiKhachHang();
        hienthiDS();
        refresh2Ma();
    }
    
    private void changeState(State state) {
        this.state = state;
        if(state == State.normal) {
            btcn1.setEnabled(false);
            btbq1.setEnabled(false);
            btcn2.setEnabled(false);
            btbq2.setEnabled(false);
            btcn3.setEnabled(false);
            btbq3.setEnabled(false);
            bttm1.setEnabled(true);
            btsua1.setEnabled(true);
            btxoa1.setEnabled(true);
            btluu1.setEnabled(true);
            bttm2.setEnabled(true);
            btsua2.setEnabled(true);
            btxoa2.setEnabled(true);
            btluu2.setEnabled(true);
            bttm3.setEnabled(true);
            btsua3.setEnabled(true);
            btxoa3.setEnabled(true);
            btluu3.setEnabled(true);
        }
        else if((state == State.themMatHang) || (state == State.suaMatHang)) {
            bttm1.setEnabled(false);
            btsua1.setEnabled(false);
            btxoa1.setEnabled(false);
            btluu1.setEnabled(false);
            btcn1.setEnabled(true);
            btbq1.setEnabled(true);
        }
        else if((state == State.themKhachHang) || (state == State.suaKhachHang)) {
            bttm2.setEnabled(false);
            btsua2.setEnabled(false);
            btxoa2.setEnabled(false);
            btluu2.setEnabled(false);
            btcn2.setEnabled(true);
            btbq2.setEnabled(true);
        }
        else if((state == State.themDS) || (state == State.suaDS)) {
            bttm3.setEnabled(false);
            btsua3.setEnabled(false);
            btxoa3.setEnabled(false);
            btluu3.setEnabled(false);
            btcn3.setEnabled(true);
            btbq3.setEnabled(true);
        }
    }
    
    private MatHang newMatHang() {
        MatHang mh = null;
        try{
            String tenMH = jTextField2.getText(),
                   nhomMH = jComboBox1.getSelectedItem().toString();
            Double giaB = Double.parseDouble(jTextField3.getText());
            if(tenMH.equals("")){
                JOptionPane.showMessageDialog(this, "Khong dc bo trong");
            }
            else if(giaB<0) {
                JOptionPane.showMessageDialog(this, "Gia ban phai la so");
            }
            else {
                int maMH = Integer.parseInt(jTextField1.getText());
                mh = new MatHang(maMH,tenMH,nhomMH,giaB);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Vui long nhap dung!");
        }
        return mh;
    }
    
    private void themMatHang() {
        MatHang mh = newMatHang();
        if(mh!=null) {
            listMatHang.add(mh);
            hienthiMatHang();
        }
    }
    
    private void hienthiMatHang() {
        tm1.setRowCount(0);
        for(MatHang mh : listMatHang) {
            tm1.addRow(mh.toObject());
        }
    }
    
    private void suaMatHang() {
        int check = jTable1.getSelectedRow();
        MatHang mh = newMatHang();
        listMatHang.set(check, mh);
        hienthiMatHang();
    }
    
    private void xoaMatHang() {
        int check = jTable1.getSelectedRow();
        if(check < 0 || check > jTable1.getRowCount() || jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chon mat hang de xoa");
        }
        else {
            listMatHang.remove(check);
            hienthiMatHang();
        }
    }
    
    private MatHang timMatHang(int maMH) {
        for(int i=0;i<listMatHang.size();i++){
            if(listMatHang.get(i).getMaMH() == maMH)
               return listMatHang.get(i);
        }
        return null;
    }
    
    private KhachHang newKhachHang() {
        KhachHang kh = null;
        try{
            String tenKH = jTextField5.getText(),
                   diaChi = jTextField6.getText(),
                   soDT = jTextField7.getText();
            if(tenKH.equals("") || diaChi.equals("") || soDT.equals("")) {
                JOptionPane.showMessageDialog(this, "Khong duoc de trong");
            }
            else {
                int maKH = Integer.parseInt(jTextField4.getText());
                kh = new KhachHang(maKH,tenKH,diaChi,soDT);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Yeu cau nhap chinh xac");
        }
        return kh;
    }
    
    private void hienthiKhachHang() {
        tm2.setRowCount(0);
        for(KhachHang kh : listKhachHang) {
            tm2.addRow(kh.toObject());
        }
    }
    
    private void themKhachHang() {
        KhachHang kh = newKhachHang();
        if(kh!=null) {
            listKhachHang.add(kh);
            hienthiKhachHang();
        }
    }
    
    private void suaKhachHang() {
        int check = jTable2.getSelectedRow();
        KhachHang kh = newKhachHang();
        listKhachHang.set(check,kh);
        hienthiKhachHang();
    }
    
    private void xoaKhachHang() {
        int check = jTable2.getSelectedRow();
        if(check<0 || check >jTable2.getRowCount() || jTable2.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Chon khach hang de xoa");
        }else {
            listKhachHang.remove(check);
            hienthiKhachHang();
        }
    }
    
    private KhachHang timKhachHang(int maKH) {
        for(int i=0;i<listKhachHang.size();i++) {
            if(listKhachHang.get(i).getMaKH()==maKH)
                return listKhachHang.get(i);
        }
        return null;
    }
    
    private boolean isDS(int makh,int mamh){
        for(DanhSachMuaHang ds : listDanhSach) {
            if(ds.getMaKh() == makh && ds.getMaMH() == mamh){
                return true;
            }
        }
        return false;
    }
    
    private DanhSachMuaHang newDanhSach() {
        DanhSachMuaHang ds = null;
        int makh=0,mamh=0;
        try{
            makh = Integer.parseInt(jComboBox2.getSelectedItem().toString());
            mamh = Integer.parseInt(jComboBox3.getSelectedItem().toString());
            if(isDS(makh,mamh)){
                JOptionPane.showMessageDialog(this, "Da ton tai 2 ma");
                return null;
            }
            int soL = Integer.parseInt(jTextField8.getText());
            if(soL<0) {
                JOptionPane.showMessageDialog(this, "So luong phai > 0");
            }else if(soL>10) {
                JOptionPane.showMessageDialog(this, "So luong phai < 10");
            }else {
                ds = new DanhSachMuaHang(timKhachHang(makh),timMatHang(mamh),soL);
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Nhap lai du lieu!");
        }
        return ds;
    }
    
    private void hienthiDS() {
        tm3.setRowCount(0);
        for(DanhSachMuaHang ds : listDanhSach) {
            tm3.addRow(ds.toObject());
        }
    }
    
    private void themDS(){
        DanhSachMuaHang ds = newDanhSach();
        if(ds!=null){
            listDanhSach.add(ds);
            hienthiDS();
        }
    }
    
    private void suaDS() {
        int check = jTable3.getSelectedRow();
        DanhSachMuaHang ds = newDanhSach();
        if(ds == null) {
            return;
        }
        else{
            listDanhSach.set(check, ds);
            hienthiDS();
        }
    }
    
    private void xoaDS() {
        int check = jTable3.getSelectedRow();
        if(check< 0 || check > jTable3.getRowCount() || jTable3.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Chon DS de xoa");
        }else {
            listDanhSach.remove(check);
            hienthiDS();
        }
    }

    private void refresh2Ma() {
        jComboBox2.removeAllItems();
        jComboBox3.removeAllItems();
        jComboBox4.removeAllItems();
        for(KhachHang kh : listKhachHang){
            jComboBox2.addItem(Integer.toString(kh.getMaKH()));
            jComboBox4.addItem(Integer.toString(kh.getMaKH()));
        }
        for(MatHang mh : listMatHang) {
            jComboBox3.addItem(Integer.toString(mh.getMaMH()));
        }
    }
    
    public int getMaHoaDon(){
        int mahd = Integer.parseInt(jComboBox4.getSelectedItem().toString());
        return mahd;
    }
    
    public String getTenHoaDon(int mahd){
        String re="";
        for(int i=0;i<listDanhSach.size();i++) {
            if(listDanhSach.get(i).getMaKh()==mahd) {
                re= listDanhSach.get(i).getTenKH();
            }
        }
        return re;
    }
    
    private HashSet<Integer> hoadonKH() {
        HashSet<Integer> hd = new HashSet<>();
        for(int i=0;i<listDanhSach.size();i++){
            hd.add(listDanhSach.get(i).getMaKh());
        }
        return hd;
    }
    
    private String HoaDonBanHang(int mahd) {
        String re="";
        int sum=0;
        for(int i=0;i<listDanhSach.size();i++) {
            if(listDanhSach.get(i).getMaKh()==mahd){
                sum+=listDanhSach.get(i).getMatHang().getGiaB()*listDanhSach.get(i).getSoLuong();
                re+="\n\t"+listDanhSach.get(i).getMatHang().getTenMH()+"\t\t"+listDanhSach.get(i).getMatHang().getGiaB()+"\t\t"+listDanhSach.get(i).getSoLuong()+"\n";
            }
        }
        re+="\n\t=====================Tong tien: "+sum+" VND==============================\n";
        re+="\t================================================================";
        return re;
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
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bttm1 = new javax.swing.JButton();
        btsua1 = new javax.swing.JButton();
        btxoa1 = new javax.swing.JButton();
        btluu1 = new javax.swing.JButton();
        btcn1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        btbq1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        bttm2 = new javax.swing.JButton();
        btsua2 = new javax.swing.JButton();
        btxoa2 = new javax.swing.JButton();
        btluu2 = new javax.swing.JButton();
        btcn2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        btbq2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        bttm3 = new javax.swing.JButton();
        btsua3 = new javax.swing.JButton();
        btxoa3 = new javax.swing.JButton();
        btluu3 = new javax.swing.JButton();
        btcn3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton16 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField8 = new javax.swing.JTextField();
        btbq3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton17 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng", "Tên hàng", "Nhóm hàng", "Giá bán"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        bttm1.setText("Thêm mới");
        bttm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttm1ActionPerformed(evt);
            }
        });

        btsua1.setText("Sửa");
        btsua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsua1ActionPerformed(evt);
            }
        });

        btxoa1.setText("Xoá");
        btxoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoa1ActionPerformed(evt);
            }
        });

        btluu1.setText("Lưu");
        btluu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluu1ActionPerformed(evt);
            }
        });

        btcn1.setText("Cập nhật");
        btcn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcn1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã hàng");

        jLabel2.setText("Tên hàng");

        jLabel3.setText("Nhóm hàng");

        jLabel4.setText("Giá bán");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hàng thời trang", "Hàng tiêu dùng", "Hàng điện máy", "Hàng gia dụng" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btbq1.setText("Bỏ qua");
        btbq1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbq1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bttm1)
                        .addGap(58, 58, 58)
                        .addComponent(btsua1)
                        .addGap(60, 60, 60)
                        .addComponent(btxoa1)
                        .addGap(59, 59, 59)
                        .addComponent(btluu1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jComboBox1, 0, 245, Short.MAX_VALUE)))
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btcn1)
                    .addComponent(btbq1))
                .addGap(87, 87, 87))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttm1)
                    .addComponent(btsua1)
                    .addComponent(btxoa1)
                    .addComponent(btluu1))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btcn1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btbq1)))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mặt hàng", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Họ tên", "Địa chỉ", "Số điện thoại"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        bttm2.setText("Thêm mới");
        bttm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttm2ActionPerformed(evt);
            }
        });

        btsua2.setText("Sửa");
        btsua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsua2ActionPerformed(evt);
            }
        });

        btxoa2.setText("Xoá");
        btxoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoa2ActionPerformed(evt);
            }
        });

        btluu2.setText("Lưu");
        btluu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluu2ActionPerformed(evt);
            }
        });

        btcn2.setText("Cập nhật");
        btcn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcn2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Mã khách hàng");

        jLabel6.setText("Họ tên");

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Số điện thoại");

        btbq2.setText("Bỏ qua");
        btbq2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbq2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btcn2)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(bttm2)
                                .addGap(71, 71, 71)
                                .addComponent(btsua2)
                                .addGap(63, 63, 63)
                                .addComponent(btxoa2)
                                .addGap(74, 74, 74)
                                .addComponent(btluu2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField6)
                                    .addComponent(jTextField7))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(btbq2)
                        .addGap(121, 121, 121))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttm2)
                    .addComponent(btsua2)
                    .addComponent(btxoa2)
                    .addComponent(btluu2))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btcn2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btbq2)))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khách hàng", jPanel2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Mã mặt hàng", "Tên mặt hàng", "Số lượng"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        bttm3.setText("Thêm mới");
        bttm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttm3ActionPerformed(evt);
            }
        });

        btsua3.setText("Sửa");
        btsua3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsua3ActionPerformed(evt);
            }
        });

        btxoa3.setText("Xoá");
        btxoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoa3ActionPerformed(evt);
            }
        });

        btluu3.setText("Lưu");

        btcn3.setText("Cập nhật");
        btcn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcn3ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sắp xếp"));

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("Theo tên khách hàng");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Theo tên mặt hàng");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton16.setText("Sắp xếp");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton16)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel9.setText("Tên khách hàng");

        jLabel10.setText("Tên mặt hàng");

        jLabel11.setText("Số lượng");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btbq3.setText("Bỏ qua");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bttm3)
                        .addGap(62, 62, 62)
                        .addComponent(btsua3)
                        .addGap(68, 68, 68)
                        .addComponent(btxoa3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField8)
                                .addComponent(jComboBox2, 0, 145, Short.MAX_VALUE))))
                    .addComponent(jLabel9))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btluu3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btbq3)
                            .addComponent(btcn3))
                        .addGap(175, 175, 175))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttm3)
                    .addComponent(btsua3)
                    .addComponent(btxoa3)
                    .addComponent(btluu3))
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcn3))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btbq3)
                        .addGap(1, 1, 1)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("Danh sách mua hàng", jPanel3);

        jLabel12.setText("Mã khách hàng");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton17.setText("Hiển thị hoá đơn");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel13.setText("Tên khách hàng");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(43, 43, 43)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(jButton17))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addGap(116, 116, 116)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void bttm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttm1ActionPerformed
        changeState(State.themMatHang);
        int maMH = listMatHang.size()+1000;
        while(true){
            if(timMatHang(maMH)==null){
                jTextField1.setText(maMH+"");
                break;
            }
            maMH++;
        }
    }//GEN-LAST:event_bttm1ActionPerformed

    private void btsua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsua1ActionPerformed
        if(jTable1.getSelectedRow()>=0) {
            changeState(State.suaMatHang);
        }else {
            JOptionPane.showMessageDialog(this, "Chon mat hang de sua");
        }
    }//GEN-LAST:event_btsua1ActionPerformed

    private void btxoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoa1ActionPerformed
        xoaMatHang();
        refresh2Ma();
    }//GEN-LAST:event_btxoa1ActionPerformed

    private void btcn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcn1ActionPerformed
        if(state==State.themMatHang){
            themMatHang();
        }else if(state==State.suaMatHang){
            suaMatHang();
        }
        refresh2Ma();
        changeState(State.normal);
    }//GEN-LAST:event_btcn1ActionPerformed

    private void btbq1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbq1ActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btbq1ActionPerformed

    private void btluu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluu1ActionPerformed
        IOFile.writeFile(listMatHang, "MatHang.dat");
    }//GEN-LAST:event_btluu1ActionPerformed

    private void bttm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttm2ActionPerformed
        changeState(State.themKhachHang);
        int maKH = listKhachHang.size()+10000;
        while(true){
            if(timKhachHang(maKH) == null){
                jTextField4.setText(maKH +"");
                break;
            }
            maKH++;
        }
    }//GEN-LAST:event_bttm2ActionPerformed

    private void btsua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsua2ActionPerformed
        if(jTable2.getSelectedRow()>=0){
            changeState(State.suaKhachHang);
        }else {
            JOptionPane.showMessageDialog(this, "Chon khach hang de sua");
        }
    }//GEN-LAST:event_btsua2ActionPerformed

    private void btxoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoa2ActionPerformed
        xoaKhachHang();
        refresh2Ma();
    }//GEN-LAST:event_btxoa2ActionPerformed

    private void btcn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcn2ActionPerformed
        if(state ==State.themKhachHang)
            themKhachHang();
        else if(state == State.suaKhachHang)
            suaKhachHang();
        changeState(State.normal);
        refresh2Ma();
    }//GEN-LAST:event_btcn2ActionPerformed

    private void btbq2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbq2ActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btbq2ActionPerformed

    private void btluu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluu2ActionPerformed
        IOFile.writeFile(listKhachHang, "KhachHang.dat");
    }//GEN-LAST:event_btluu2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int check = jTable1.getSelectedRow();
        jTextField1.setText(tm1.getValueAt(check, 0).toString());
        jTextField2.setText(tm1.getValueAt(check, 1).toString());
        jComboBox1.setSelectedItem(tm1.getValueAt(check, 2).toString());
        jTextField3.setText(tm1.getValueAt(check, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int check = jTable2.getSelectedRow();
        jTextField4.setText(tm2.getValueAt(check, 0).toString());
        jTextField5.setText(tm2.getValueAt(check, 1).toString());
        jTextField6.setText(tm2.getValueAt(check, 2).toString());
        jTextField7.setText(tm2.getValueAt(check, 3).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void bttm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttm3ActionPerformed
        changeState(State.themDS);
    }//GEN-LAST:event_bttm3ActionPerformed

    private void btsua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsua3ActionPerformed
        if(jTable3.getSelectedRow()>=0){
            changeState(State.suaDS);
        }
        else {
            JOptionPane.showMessageDialog(this, "Chon dong de sua");
        }
    }//GEN-LAST:event_btsua3ActionPerformed

    private void btxoa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoa3ActionPerformed
        xoaDS();
    }//GEN-LAST:event_btxoa3ActionPerformed

    private void btcn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcn3ActionPerformed
        if(state==State.themDS)
            themDS();
        else if(state == State.suaDS)
            suaDS();
        changeState(State.normal);
    }//GEN-LAST:event_btcn3ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int check = jTable3.getSelectedRow();
        jComboBox2.setSelectedItem(tm3.getValueAt(check, 0).toString());
        jComboBox3.setSelectedItem(tm3.getValueAt(check, 2).toString());
        jTextField8.setText(tm3.getValueAt(check, 4).toString());
    }//GEN-LAST:event_jTable3MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if(jRadioButton1.isSelected()){
            Collections.sort(listDanhSach,new Comparator<DanhSachMuaHang>() {
                @Override
                public int compare(DanhSachMuaHang o1, DanhSachMuaHang o2) {
                    return o1.getTenKH().compareToIgnoreCase(o2.getTenKH());
                }
            });
        }else{
            Collections.sort(listDanhSach,new Comparator<DanhSachMuaHang>() {
                @Override
                public int compare(DanhSachMuaHang o1, DanhSachMuaHang o2) {
                    return o1.getTenMH().compareToIgnoreCase(o2.getTenMH());
                }
            });
        }
        tm3.setRowCount(0);
        for(DanhSachMuaHang ds:listDanhSach)
            tm3.addRow(ds.toObject());
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jTextField9.setText(getTenHoaDon(getMaHoaDon()));
        jTextArea2.setText("\tTên mặt hàng\t\tGiá\t\tSố lượng\n\t________________________________________________________________\n");
        jTextArea2.append(HoaDonBanHang(getMaHoaDon()));
    }//GEN-LAST:event_jButton17ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbq1;
    private javax.swing.JButton btbq2;
    private javax.swing.JButton btbq3;
    private javax.swing.JButton btcn1;
    private javax.swing.JButton btcn2;
    private javax.swing.JButton btcn3;
    private javax.swing.JButton btluu1;
    private javax.swing.JButton btluu2;
    private javax.swing.JButton btluu3;
    private javax.swing.JButton btsua1;
    private javax.swing.JButton btsua2;
    private javax.swing.JButton btsua3;
    private javax.swing.JButton bttm1;
    private javax.swing.JButton bttm2;
    private javax.swing.JButton bttm3;
    private javax.swing.JButton btxoa1;
    private javax.swing.JButton btxoa2;
    private javax.swing.JButton btxoa3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
