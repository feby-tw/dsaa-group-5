public class Admin extends User {
    private LevelAkses akses;

    public Admin(String userId, String username, String password, String nama, LevelAkses akses) {
        super(userId, username, password, nama);
        this.akses = akses;
    }

    public LevelAkses getAkses() {
        return akses;
    }

    public void setAkses(LevelAkses akses) {
        this.akses = akses;
    }

    @Override
    public void interact() {
        System.out.println("Admin " + this.nama + " dapat menambah, mengedit, dan menghapus data buku.");
    }
}