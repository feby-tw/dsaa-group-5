package queue;

public class Member extends User {
    private final String nomorAnggota;

    public Member(String userId, String username, String password, String nama, String nomorAnggota) {
        super(userId, username, password, nama);
        this.nomorAnggota = nomorAnggota;
    }

    public String getNomorAnggota() {
        return nomorAnggota;
    }

    @Override
    public void interact() {
        System.out.println("Member " + nama + " dapat meminjam dan mengembalikan buku.");
    }
}