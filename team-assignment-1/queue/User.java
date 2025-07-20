package queue;

public abstract class User {
    protected final String userId;
    protected String username;
    protected String password;
    protected String nama;

    public User(String userId, String username, String password, String nama) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public abstract void interact();
}