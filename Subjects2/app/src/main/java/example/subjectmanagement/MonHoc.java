package example.subjectmanagement;

public class MonHoc {
    private int maMH;
    private String tenMH;
    private int soTiet;

    public MonHoc(int maMH, String tenMH, int soTiet) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTiet = soTiet;
    }

    // Getters and Setters
    public int getMaMH() { return maMH; }
    public void setMaMH(int maMH) { this.maMH = maMH; }

    public String getTenMH() { return tenMH; }
    public void setTenMH(String tenMH) { this.tenMH = tenMH; }

    public int getSoTiet() { return soTiet; }
    public void setSoTiet(int soTiet) { this.soTiet = soTiet; }
}
