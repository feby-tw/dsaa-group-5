package queue;

public class Main {
    public static void main(String[] args) {
        Perpustakaan perpustakaan = new Perpustakaan(100);

        Admin admin = new Admin("A01", "alpha_admin", "pass123", "Alpha", LevelAkses.KEPALA_PERPUSTAKAAN);
        Member member = new Member("M01", "beta_member", "pass321", "Beta", "AGT001");

        System.out.println("\n*** UJI POLYMORPHISM ***");
        admin.interact();
        member.interact();

        System.out.println("\n*** TAMBAH BUKU ***");
        perpustakaan.tambahBuku(new Buku("B01", "Laskar Pelangi", "Andrea Hirata"), admin);
        perpustakaan.tambahBuku(new Buku("B02", "Bumi Manusia", "Pramoedya Ananta Toer"), admin);

        System.out.println("\n*** BUKU TERSEDIA SAAT INI ***");
        perpustakaan.tampilkanBukuTersedia();

        System.out.println("\n*** MEMBER MEMINJAM BUKU ***");
        perpustakaan.pinjamBuku("Laskar Pelangi", member);

        System.out.println("\n*** BUKU TERSEDIA SETELAH DIPINJAM ***");
        perpustakaan.tampilkanBukuTersedia();

        System.out.println("\n*** MEMBER MENGEMBALIKAN BUKU ***");
        perpustakaan.kembalikanBuku("Laskar Pelangi", member);

        System.out.println("\n*** CARI BUKU 'Bumi Manusia' ***");
        perpustakaan.cariBuku("Bumi Manusia");

        System.out.println("\n*** ADMIN MENGEDIT BUKU B01 ***");
        perpustakaan.editBuku("B01", "Laskar Pelangi - Edisi Revisi", null, admin);

        System.out.println("\n*** ADMIN MENGHAPUS BUKU B02 ***");
        perpustakaan.hapusBuku("B02", admin);

        System.out.println("\n*** BUKU TERSEDIA SETELAH PENGHAPUSAN ***");
        perpustakaan.tampilkanBukuTersedia();
    }
}