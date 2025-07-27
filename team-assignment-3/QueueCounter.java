/**
 * Program QueueCounter
 * --------------------
 * Program ini mensimulasikan struktur data antrian (queue) menggunakan array,
 * serta menghitung jumlah item dalam antrian secara manual.
 */

public class QueueCounter {

    // Kapasitas maksimum queue
    private static final int MAX = 100;

    // Array sebagai struktur penyimpanan queue
    private int[] data = new int[MAX];

    // Penanda indeks elemen pertama (front)
    private int front = 0;

    // Penanda indeks elemen terakhir (rear)
    private int rear = -1;

    // Variabel untuk menyimpan jumlah elemen dalam antrian
    private int size = 0;

    /**
     * Fungsi enqueue() untuk menambahkan item ke antrian.
     * Jika antrian penuh, akan ditampilkan pesan error.
     */
    public void enqueue(int item) {
        if (rear == MAX - 1) {
            System.out.println("Queue penuh!");
            return;
        }
        rear++;             // Geser posisi rear
        data[rear] = item;  // Simpan item ke array
        size++;             // Tambah ukuran antrian
    }

    /**
     * Fungsi dequeue() untuk menghapus item dari antrian.
     * Jika kosong, tampilkan pesan error.
     * @return nilai item yang dihapus
     */
    public int dequeue() {
        if (size == 0) {
            System.out.println("Queue kosong!");
            return -1;
        }
        int removed = data[front]; // Ambil item terdepan
        front++;                   // Geser posisi front
        size--;                    // Kurangi ukuran
        return removed;
    }

    /**
     * Fungsi countItems() untuk menghitung jumlah item dalam antrian.
     * @return nilai size (jumlah elemen)
     */
    public int countItems() {
        return size;
    }

    /**
     * Fungsi displayQueue() untuk menampilkan seluruh isi queue ke layar.
     */
    public void displayQueue() {
        if (size == 0) {
            System.out.println("Queue kosong!");
            return;
        }
        System.out.print("Isi queue: ");
        for (int i = front; i <= rear; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * Fungsi main() untuk menjalankan simulasi antrian.
     * Melakukan operasi enqueue, dequeue, dan menampilkan isi serta jumlah item.
     */
    public static void main(String[] args) {
        QueueCounter queue = new QueueCounter();

        // Tambah item ke queue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Tampilkan isi dan jumlah item
        queue.displayQueue();
        System.out.println("Jumlah item dalam queue: " + queue.countItems());

        // Hapus satu item dari depan
        queue.dequeue();

        // Tampilkan kembali isi dan jumlah item
        queue.displayQueue();
        System.out.println("Jumlah item setelah dequeue: " + queue.countItems());
    }
}