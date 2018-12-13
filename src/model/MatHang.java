package model;

import java.io.Serializable;

/**
 *
 * @author ViNguyen
 */

public class MatHang implements Serializable{
    private int maMH;
    private String tenMH,nhomMH;
    private double giaB;

    public MatHang(int maMH, String tenMH, String nhomMH, double giaB) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.nhomMH = nhomMH;
        this.giaB = giaB;
    }

    public int getMaMH() {
        return maMH;
    }

    public void setMaMH(int maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getNhomMH() {
        return nhomMH;
    }

    public void setNhomMH(String nhomMH) {
        this.nhomMH = nhomMH;
    }

    public double getGiaB() {
        return giaB;
    }

    public void setGiaB(double giaB) {
        this.giaB = giaB;
    }

    public Object[] toObject() {
        return new Object[] {maMH,tenMH,nhomMH,giaB};
    }
}
