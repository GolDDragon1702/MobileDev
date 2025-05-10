package example.qllop.models;

public class Lop {
    private String maLop;
    private String tenLop;
    private int siSo;

    public Lop(String maLop, String tenLop, int siSo) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.siSo = siSo;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    @Override
    public String toString() {
        return "Mã lớp: " + maLop + " - Tên lớp: " + tenLop + " - Sĩ số: " + siSo;
    }
}