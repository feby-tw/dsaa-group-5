import java.util.Scanner;

/**
 * Program konversi dan perhitungan notasi infix ke postfix dan prefix
 * menggunakan struktur Stack berbasis Linked List.
 */
public class NotationConverterLL {

    // Node untuk stack karakter
    static class CharNode {
        char data;
        CharNode next;
        CharNode(char data) {
            this.data = data;
        }
    }

    // Stack karakter (untuk konversi notasi)
    static class CharStackLL {
        CharNode top;

        void push(char c) {
            CharNode node = new CharNode(c);
            node.next = top;
            top = node;
        }

        char pop() {
            char val = top.data;
            top = top.next;
            return val;
        }

        char peek() {
            return top.data;
        }

        boolean isEmpty() {
            return top == null;
        }
    }

    // Node untuk stack bilangan
    static class IntNode {
        int data;
        IntNode next;
        IntNode(int data) {
            this.data = data;
        }
    }

    // Stack bilangan (untuk evaluasi postfix/prefix)
    static class IntStackLL {
        IntNode top;

        void push(int value) {
            IntNode node = new IntNode(value);
            node.next = top;
            top = node;
        }

        int pop() {
            int value = top.data;
            top = top.next;
            return value;
        }

        boolean isEmpty() {
            return top == null;
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

    // Validasi struktur notasi infix
    public static boolean isValidInfix(String notation) {
        int operands = 0, operators = 0;
        char prev = ' ';
        for (int i = 0; i < notation.length(); i++) {
            char c = notation.charAt(i);
            if (c >= '0' && c <= '9') {
                if (prev >= '0' && prev <= '9') return false;
                operands++;
            } else if (isOperator(c)) {
                if (prev == ' ' || isOperator(prev)) return false;
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

    // Konversi infix ke postfix
    public static String infixToPostfixLL(String notation) {
        CharStackLL stack = new CharStackLL();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < notation.length(); i++) {
            char c = notation.charAt(i);
            if (Character.isDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop(); // pop '('
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString();
    }

    // Konversi infix ke prefix
    public static String infixToPrefixLL(String notation) {
        StringBuilder reversed = new StringBuilder();

        for (int i = notation.length() - 1; i >= 0; i--) {
            char c = notation.charAt(i);
            if (c == '(') reversed.append(')');
            else if (c == ')') reversed.append('(');
            else reversed.append(c);
        }

        String postfix = infixToPostfixLL(reversed.toString());

        return new StringBuilder(postfix).reverse().toString();
    }

    // Hitung notasi postfix
    public static int calculatePostfixLL(String notation) {
        IntStackLL stack = new IntStackLL();

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

    // Hitung notasi prefix
    public static int calculatePrefixLL(String notation) {
        IntStackLL stack = new IntStackLL();

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

    // Hitung hasil operasi dua operand
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

    // Program utama
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan notasi infix (angka satu digit): ");
        String notation = sc.nextLine().replace(" ", "");

        if (!isValidInfix(notation)) {
            System.out.println("Notasi tidak valid!");
            return;
        }

        String postfix = infixToPostfixLL(notation);
        String prefix = infixToPrefixLL(notation);
        int hasilPostfix = calculatePostfixLL(postfix);
        int hasilPrefix = calculatePrefixLL(prefix);

        System.out.println("Postfix: " + postfix);
        System.out.println("Prefix : " + prefix);
        System.out.println("Hasil perhitungan postfix: " + hasilPostfix);
        System.out.println("Hasil perhitungan prefix : " + hasilPrefix);
    }
}