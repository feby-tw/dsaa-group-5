class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = this.right = null;
    }
}

public class BinarySearchTree {
    Node root;

    static class Node {
        int key;
        HuffmanNode data;
        Node left, right;

        Node(int key, HuffmanNode data) {
            this.key = key;
            this.data = data;
            this.left = this.right = null;
        }
    }

    BinarySearchTree() {
        root = null;
    }

    void insert(int key, HuffmanNode data) {
        root = insertRec(root, key, data);
    }

    Node insertRec(Node root, int key, HuffmanNode data) {
        if (root == null) {
            return new Node(key, data);
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key, data);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key, data);
        }

        return root;
    }

    public HuffmanNode getMaxValue() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }
}