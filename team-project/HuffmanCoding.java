class HuffmanCoding {
    private BinarySearchTree bst;
    private HashTable cache;
    private int cacheSize = 25;

    HuffmanCoding() {
        bst = new BinarySearchTree();
        cache = new HashTable(cacheSize);
    }

    public String encode(String text) {
        String cached = cache.get(text);

        if (cached != null) {
            System.out.println("Ditemukan di cache!");
            return cached;
        }

        String uniqueChars = CustomUtils.uniqueChar(text);
        Queue<HuffmanNode> queue = new Queue<>();

        for (char c : uniqueChars.toCharArray()) {
            int freq = CustomUtils.countChar(text, c);
            queue.enqueue(new HuffmanNode(c, freq));
        }

        while (queue.size() > 1) {
            HuffmanNode left = extractMin(queue);
            HuffmanNode right = extractMin(queue);

            HuffmanNode merged = new HuffmanNode('\0', left.freq + right.freq);
            merged.left = left;
            merged.right = right;

            queue.enqueue(merged);
        }

        HuffmanNode root = queue.dequeue();
        bst.insert(root.freq, root);

        StringBuilder encoded = new StringBuilder();
        java.util.HashMap<Character, String> huffMap = new java.util.HashMap<>();
        buildCodeMap(root, "", huffMap);

        for (char c : text.toCharArray()) {
            encoded.append(huffMap.get(c));
        }

        cache.put(text, encoded.toString());

        return encoded.toString();
    }

    public String decode(String encoded) {
        HuffmanNode root = bst.getMaxValue();
        if (root == null) return "";

        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) {
                decoded.append(current.ch);
                current = root;
            }
        }

        return decoded.toString();
    }

    private HuffmanNode extractMin(Queue<HuffmanNode> queue) {
        if (queue.isEmpty()) return null;

        HuffmanNode minNode = queue.dequeue();
        Queue<HuffmanNode> temp = new Queue<>();

        while (!queue.isEmpty()) {
            HuffmanNode node = queue.dequeue();
            if (node.freq < minNode.freq) {
                temp.enqueue(minNode);
                minNode = node;
            } else {
                temp.enqueue(node);
            }
        }

        while (!temp.isEmpty()) {
            queue.enqueue(temp.dequeue());
        }

        return minNode;
    }

    private void buildCodeMap(HuffmanNode node, String code, java.util.HashMap<Character, String> map) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            map.put(node.ch, code);
        }
        buildCodeMap(node.left, code + "0", map);
        buildCodeMap(node.right, code + "1", map);
    }
}