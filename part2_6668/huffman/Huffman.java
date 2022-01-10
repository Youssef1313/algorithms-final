package huffman;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    public HashMap<BigInteger, String> huffman(byte[] file_b, int number_n) {

        PriorityQueue<Node> pQueue = calculate_freq(file_b, number_n);
        Node root = huffmanAlgorithm(pQueue);
        HashMap<BigInteger, String> huffmanDict = new HashMap<>();
        extractDict(root, huffmanDict, "");
        return huffmanDict;
    }

    private void extractDict(Node root, HashMap<BigInteger, String> huffmanDict, String string) {
        if (root.left == null && root.right == null) {
            assert root.bytes != null;
            huffmanDict.put(root.bytes, string);
            return;
        }

        if (root.left != null)
            extractDict(root.left, huffmanDict, string + "0");

        if (root.right != null)
            extractDict(root.right, huffmanDict, string + "1");

    }

    private PriorityQueue<Node> calculate_freq(byte[] file_b, int number_n) {
        HashMap<BigInteger, Integer> hashMap = new HashMap<>();
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        for (int i = 0; i < file_b.length; i += number_n) {
            byte[] nByte = Arrays.copyOfRange(file_b, i, i + number_n);
            BigInteger key = new BigInteger(nByte);
            int oldValue = hashMap.getOrDefault(key, 0);
            hashMap.put(key, oldValue + 1);
        }

        for (Map.Entry<BigInteger, Integer> e : hashMap.entrySet()) {
            Node node = new Node(e.getKey(), e.getValue());
            nodeQueue.add(node);
        }

        return nodeQueue;
    }

    private Node huffmanAlgorithm(PriorityQueue<Node> priorityQueue) {
        int n = priorityQueue.size();
        for (int i = 0; i < n - 1; i++) {
            var left = priorityQueue.poll();
            var right = priorityQueue.poll();
            assert left != null;
            assert right != null;
            Node node = new Node(left, right);

            priorityQueue.add(node);
        }
        return priorityQueue.poll();
    }

    private static class Node implements Comparable<Node> {
        private final int freq;
        private final Node left;
        private final Node right;
        private final BigInteger bytes;

        // This constructor is used for non-leaf (inner) nodes.
        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.freq = left.freq + right.freq;
            this.bytes = null;
        }

        // This constructor is used for leaf nodes.
        public Node(BigInteger bytes, int freq) {
            this.freq = freq;
            this.bytes = bytes;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(freq, o.freq);
        }
    }

}

