/**
 * QueueCustomerService.java
 * 
 * Simulasi sistem antrean pelanggan menggunakan struktur data Queue berbasis Linked List.
 * 
 * Struktur data ini menggunakan prinsip FIFO (First-In, First-Out) untuk memproses pelanggan
 * sesuai urutan kedatangannya. Cocok digunakan untuk layanan customer service, rumah sakit,
 * tiket bioskop, dan sistem antrean lainnya.
 */

public class QueueCustomerService {

    /**
     * Inner class Node untuk menyimpan data pelanggan.
     * Setiap node menyimpan nama pelanggan dan referensi ke node berikutnya.
     */
    static class Node {
        String name; // Nama pelanggan
        Node next;   // Referensi ke node berikutnya

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    // Atribut front dan rear untuk menunjuk ke awal dan akhir antrean
    private Node front = null;
    private Node rear = null;

    /**
     * Menambahkan pelanggan baru ke antrean (enqueue).
     *
     * @param name Nama pelanggan yang ingin ditambahkan
     */
    public void enqueue(String name) {
        Node newNode = new Node(name);
        if (rear == null) {
            // Jika antrean kosong, front dan rear menunjuk ke node baru
            front = rear = newNode;
        } else {
            // Tambahkan node baru di belakang antrean
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Pelanggan \"" + name + "\" ditambahkan ke antrean.");
    }

    /**
     * Melayani pelanggan terdepan dalam antrean (dequeue).
     * Menghapus elemen dari depan antrean.
     */
    public void dequeue() {
        if (front == null) {
            System.out.println("Antrean kosong. Tidak ada pelanggan untuk dilayani.");
            return;
        }
        System.out.println("Melayani pelanggan: " + front.name);
        front = front.next;

        // Jika setelah dequeue antrean menjadi kosong, rear juga diset ke null
        if (front == null) {
            rear = null;
        }
    }

    /**
     * Menampilkan semua pelanggan dalam antrean saat ini.
     */
    public void printQueue() {
        if (front == null) {
            System.out.println("Antrean kosong.");
            return;
        }
        System.out.println("Pelanggan dalam antrean:");
        Node temp = front;
        int i = 1;
        while (temp != null) {
            System.out.println(i++ + ". " + temp.name);
            temp = temp.next;
        }
    }

    /**
     * Main method untuk menjalankan simulasi antrean.
     * Cocok digunakan saat menjalankan dari terminal atau IDE seperti VS Code.
     */
    public static void main(String[] args) {
        QueueCustomerService qcs = new QueueCustomerService();

        // Tambah pelanggan
        qcs.enqueue("Andi");
        qcs.enqueue("Budi");
        qcs.enqueue("Siti");

        // Tampilkan antrean
        qcs.printQueue();

        // Layani satu pelanggan
        qcs.dequeue();

        // Tampilkan antrean setelah dequeue
        qcs.printQueue();
    }
}