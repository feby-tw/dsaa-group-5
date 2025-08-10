import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HuffmanCoding huffman = new HuffmanCoding();
        Scanner scanner = new Scanner(System.in);
        String lastEncodedText = "";
        String lastDecodedText = "";

        while (true) {
            System.out.println("\n===== Huffman Coding with Built-in Caching =====");
            System.out.println("1. Encode Teks");
            System.out.println("2. Decode Teks");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Masukkan teks yang ingin di-encode: ");
                    String textToEncode = scanner.nextLine();
                    if (textToEncode.isEmpty()) {
                        System.out.println("Teks tidak boleh kosong.");
                        break;
                    }
                    lastEncodedText = huffman.encode(textToEncode);
                    lastDecodedText = textToEncode;
                    System.out.println("Teks asli: " + textToEncode);
                    System.out.println("Teks ter-encode: " + lastEncodedText);
                    break;

                case "2":
                    if (lastEncodedText.isEmpty()) {
                        System.out.println("Anda harus meng-encode teks terlebih dahulu.");
                        break;
                    }
                    String decodedText = huffman.decode(lastEncodedText);
                    System.out.println("Teks ter-encode: " + lastEncodedText);
                    System.out.println("Teks ter-decode: " + decodedText);
                    break;
                
                case "3":
                    System.out.println("Terima kasih!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }
}