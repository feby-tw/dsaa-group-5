import java.util.Scanner;

/**
 * Program untuk mengonversi notasi infix menjadi postfix dan prefix,
 * serta menghitung hasil operasinya menggunakan konsep stack.
 */
public class NotationConverter {

    // Stack karakter untuk menyimpan operator dan tanda kurung
    static class CharStack {
        char[] data = new char[100];
        int top = -1;

        void push(char c) {
            data[++top] = c;
        }

        char pop() {
            return data[top--];
        }

        char peek() {
            return data[top];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    // Stack bilangan untuk menghitung hasil postfix/prefix
    static class IntStack {
        int[] data = new int[100];
        int top = -1;

        void push(int value) {
            data[++top] = value;
        }

        int pop() {
            return data[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    // Menentukan prioritas operator
    public static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        if (op == '^') return 3;
        return -1;
    }

    // Mengecek apakah karakter adalah operator
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Validasi struktur notasi infix agar sesuai aturan
    public static boolean isValidInfix(String notation) {
        int operands = 0, operators = 0;
        char prev = ' ';
        for (int i = 0; i < notation.length(); i++) {
            char c = notation.charAt(i);
            if (c >= '0' && c <= '9') {
                if (prev >= '0' && prev <= '9') return false; // tidak boleh dua digit berturut
                operands++;
            } else if (isOperator(c)) {
                if (prev == ' ' || isOperator(prev)) return false; // operator di awal atau ganda
                operators++;
            } else if (c == '(' || c == ')') {
                continue;
            } else {
                return false;
            }
            prev = c;
        }
        return operands == operators + 1;
    }

    // Konversi infix ke postfix menggunakan stack
    public static String infixToPostfix(String notation) {
        CharStack stack = new CharStack();
        char[] output = new char[100];
        int idx = 0;

        for (int i = 0; i < notation.length(); i++) {
            char c = notation.charAt(i);
            if (Character.isDigit(c)) {
                output[idx++] = c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output[idx++] = stack.pop();
                }
                stack.pop(); // hapus tanda '('
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    output[idx++] = stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            output[idx++] = stack.pop();
        }

        return new String(output, 0, idx);
    }

    // Konversi infix ke prefix dengan membalik, postfix, lalu membalik lagi
    public static String infixToPrefix(String notation) {
        char[] reversed = new char[notation.length()];
        for (int i = 0; i < notation.length(); i++) {
            char c = notation.charAt(notation.length() - 1 - i);
            switch (c) {
                case '(':
                    reversed[i] = ')';
                    break;
                case ')':
                    reversed[i] = '(';
                    break;
                default:
                    reversed[i] = c;
                    break;
            }
        }

        String postfix = infixToPostfix(new String(reversed));

        char[] prefix = new char[postfix.length()];
        for (int i = 0; i < postfix.length(); i++) {
            prefix[i] = postfix.charAt(postfix.length() - 1 - i);
        }

        return new String(prefix);
    }

    // Menghitung hasil dari notasi postfix
    public static int calculatePostfix(String notation) {
        IntStack stack = new IntStack();
        for (int i = 0; i < notation.length(); i++) {
            char c = notation.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOperator(a, b, c));
            }
        }
        return stack.pop();
    }

    // Menghitung hasil dari notasi prefix
    public static int calculatePrefix(String notation) {
        IntStack stack = new IntStack();
        for (int i = notation.length() - 1; i >= 0; i--) {
            char c = notation.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(applyOperator(a, b, c));
            }
        }
        return stack.pop();
    }

    // Operasi matematika dasar antara dua angka
    public static int applyOperator(int a, int b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        if (op == '*') return a * b;
        if (op == '/') return b != 0 ? a / b : 0;
        if (op == '^') {
            int res = 1;
            for (int i = 0; i < b; i++) res *= a;
            return res;
        }
        return 0;
    }

    // Program utama: menerima input, proses konversi, dan tampilkan hasil
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan notasi infix (angka satu digit): ");
        String notation = sc.nextLine().replace(" ", "");

        if (!isValidInfix(notation)) {
            System.out.println("Notasi tidak valid!");
            return;
        }

        // Konversi notasi
        String postfix = infixToPostfix(notation);
        String prefix = infixToPrefix(notation);

        // Perhitungan hasil
        int hasilPostfix = calculatePostfix(postfix);
        int hasilPrefix = calculatePrefix(prefix);

        // Tampilkan hasil ke layar
        System.out.println("Postfix: " + postfix);
        System.out.println("Prefix : " + prefix);
        System.out.println("Hasil perhitungan postfix: " + hasilPostfix);
        System.out.println("Hasil perhitungan prefix : " + hasilPrefix);
    }
}