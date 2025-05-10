package example.qlsv;

public class SinhVien {
    int id;
    String ten, lop;

    public SinhVien(int id, String ten, String lop) {
        this.id = id;
        this.ten = ten;
        this.lop = lop;
    }

    public int getId() { return id; }
    public String getTen() { return ten; }
    public String getLop() { return lop; }

    public void setTen(String ten) { this.ten = ten; }
    public void setLop(String lop) { this.lop = lop; }
}

