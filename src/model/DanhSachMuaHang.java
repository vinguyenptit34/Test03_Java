package model;

import java.io.Serializable;

/**
 *
 * @author ViNguyen
 */
public class DanhSachMuaHang implements Serializable{
    private KhachHang khachHang;
    private MatHang matHang;
    private int soLuong;

    public DanhSachMuaHang(KhachHang khachHang, MatHang matHang, int soLuong) {
        this.khachHang = khachHang;
        this.matHang = matHang;
        this.soLuong = soLuong;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public MatHang getMatHang() {
        return matHang;
    }

    public void setMatHang(MatHang matHang) {
        this.matHang = matHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public int getMaMH() {
        return matHang.getMaMH();
    }
    
    public int getMaKh() {
        return khachHang.getMaKH();
    }
    
    public String getTenMH() {
        return matHang.getTenMH();
    }
    
    public String getTenKH() {
        return khachHang.getTenKH();
    }
    
    public Object[] toObject() {
        return new Object[] {getMaKh(),getTenKH(),getMaMH(),getTenMH(),soLuong};
    }
}
