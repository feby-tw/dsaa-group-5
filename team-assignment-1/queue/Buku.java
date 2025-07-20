package queue;

public class Buku {
    private final String bukuId;
    private String judul;
    private String pengarang;
    private StatusBuku status;

    public Buku(String bukuId, String judul, String pengarang) {
        this.bukuId = bukuId;
        this.judul = judul;
        this.pengarang = pengarang;
        this.status = StatusBuku.TERSEDIA;
    }

    public String getBukuId() {
        return bukuId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public StatusBuku getStatus() {
        return status;
    }

    public void setStatus(StatusBuku status) {
        this.status = status;
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + bukuId);
        System.out.println("Judul: " + judul);
        System.out.println("Pengarang: " + pengarang);
        System.out.println("Status: " + status);
    }
}