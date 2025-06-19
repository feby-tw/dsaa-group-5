public class Perpustakaan {
    private final Buku[] daftarBuku;
    private int jumlahBuku = 0;

    public Perpustakaan(int kapasitas) {
        if (kapasitas > 0) {
            this.daftarBuku = new Buku[kapasitas];
            System.out.println("Kapasitas perpustakaan diset ke: " + kapasitas + " buku.");
        } else {
            this.daftarBuku = new Buku[100];
            System.out.println("Kapasitas tidak valid. Kapasitas diset ke default 100 buku.");
        }
    }

    public void tambahBuku(Buku buku, Admin admin) {
        if (jumlahBuku < daftarBuku.length) {
            daftarBuku[jumlahBuku++] = buku;
            System.out.println("Buku \"" + buku.getJudul() + "\" berhasil ditambahkan oleh \"" + admin.getUsername() + "\".");
        } else {
            System.err.println("Kapasitas perpustakaan penuh.");
        }
    }

    public void hapusBuku(String bukuId, Admin admin) {
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getBukuId().equalsIgnoreCase(bukuId)) {
                String judul = daftarBuku[i].getJudul();
                for (int j = i; j < jumlahBuku - 1; j++) {
                    daftarBuku[j] = daftarBuku[j + 1];
                }
                daftarBuku[--jumlahBuku] = null;
                System.out.println("Buku \"" + judul + "\" berhasil dihapus oleh \"" + admin.getUsername() + "\".");
                return;
            }
        }
        System.out.println("Buku dengan ID \"" + bukuId + "\" tidak ditemukan.");
    }

    public void editBuku(String bukuId, String judulBaru, String pengarangBaru, Admin admin) {
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getBukuId().equalsIgnoreCase(bukuId)) {
                if (judulBaru != null && !judulBaru.isEmpty()) {
                    daftarBuku[i].setJudul(judulBaru);
                }
                if (pengarangBaru != null && !pengarangBaru.isEmpty()) {
                    daftarBuku[i].setPengarang(pengarangBaru);
                }
                System.out.println("Buku dengan ID \"" + bukuId + "\" berhasil diedit oleh \"" + admin.getUsername() + "\".");
                return;
            }
        }
        System.out.println("Buku dengan ID \"" + bukuId + "\" tidak ditemukan.");
    }

    public void cariBuku(String judul) {
        boolean ditemukan = false;
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getJudul().equalsIgnoreCase(judul)) {
                daftarBuku[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Buku \"" + judul + "\" tidak ditemukan.");
        }
    }

    public void tampilkanBukuTersedia() {
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getStatus() == StatusBuku.TERSEDIA) {
                daftarBuku[i].tampilkanInfo();
            }
        }
    }

    public void pinjamBuku(String judul, Member member) {
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getJudul().equalsIgnoreCase(judul) &&
                daftarBuku[i].getStatus() == StatusBuku.TERSEDIA) {
                daftarBuku[i].setStatus(StatusBuku.DIPINJAM);
                System.out.println("Buku \"" + judul + "\" berhasil dipinjam oleh \"" + member.getUsername() + "\".");
                return;
            }
        }
        System.out.println("Buku \"" + judul + "\" tidak tersedia untuk dipinjam.");
    }

    public void kembalikanBuku(String judul, Member member) {
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].getJudul().equalsIgnoreCase(judul) &&
                daftarBuku[i].getStatus() == StatusBuku.DIPINJAM) {
                daftarBuku[i].setStatus(StatusBuku.TERSEDIA);
                System.out.println("Buku \"" + judul + "\" berhasil dikembalikan oleh \"" + member.getUsername() + "\".");
                return;
            }
        }
        System.out.println("Buku \"" + judul + "\" tidak ditemukan atau tidak sedang dipinjam.");
    }
}