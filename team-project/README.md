# Huffman Coding with Built-in Caching

Sistem ini mengimplementasikan algoritma **Huffman Coding** untuk kompresi data dengan memanfaatkan kombinasi tiga struktur data buatan sendiri: **HashTable**, **Binary Search Tree (BST)**, dan **Queue**. Arsitektur ini dirancang untuk mengoptimalkan performa dengan mengadopsi mekanisme caching.

---

## Struktur Data

- **HashTable**  
  Digunakan sebagai **cache** untuk menyimpan hasil kompresi. Dengan menggunakan string input sebagai *key*, sistem dapat secara cepat mencari data terkompresi yang sudah pernah diproses. HashTable juga digunakan untuk menyimpan frekuensi karakter unik dari string input.

- **Binary Search Tree (BST)**  
  Digunakan untuk merepresentasikan **Huffman Tree**. Setiap node dalam pohon ini merepresentasikan karakter atau gabungan dari karakter, dengan frekuensi kemunculan sebagai nilai prioritas.

- **Queue**  
  Digunakan sebagai antrian prioritas untuk membangun Huffman Tree. Node-node dengan frekuensi terendah akan selalu berada di depan antrian.

---

## Alur Kerja Sistem

1. **Input dan Pemeriksaan Cache**  
   - Sistem memeriksa **HashTable** untuk melihat apakah string input sudah pernah dikompresi.  
   - **Jika ditemukan** → langsung mengembalikan hasil kompresi dari cache.  
   - **Jika tidak ditemukan** → lanjut ke tahap kompresi.

2. **Proses Kompresi Huffman**
   - **Penghitungan Frekuensi** → Menghitung frekuensi setiap karakter unik dan menyimpannya di HashTable.  
   - **Pembentukan Huffman Tree** →  
     - Membuat node berdasarkan frekuensi karakter.  
     - Memasukkan node ke dalam **Queue**.  
     - Menggabungkan dua node dengan frekuensi terendah menjadi parent node baru, sampai terbentuk satu akar Huffman Tree.  
   - **Penyandian (Encoding)** → Menelusuri Huffman Tree untuk menghasilkan kode biner tiap karakter.  
   - **Penyimpanan Cache** → Menyimpan hasil kompresi ke **HashTable** dengan format:  
     ```
     <original_string> : <compressed_binary_string>
     ```

3. **Output**  
   - Mengembalikan string hasil kompresi.  
   - Mekanisme caching memastikan proses kompresi yang berat hanya dilakukan sekali per input unik, meningkatkan efisiensi pemrosesan data berulang.
