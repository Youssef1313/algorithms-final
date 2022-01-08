package huffman;

import java.math.BigInteger;
import java.util.*;


public class Huffman {
    private class Node implements Comparable<Node> {
        private final int freq;
        private final Node left;
        private final Node right;
        private final BigInteger bytes;

        // This constructor is used for non-leaf (inner) nodes.
        public Node (Node left, Node right) {
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
    public void huffman(byte[] file_b, int number_n){

        PriorityQueue<Node> pQueue = calculate_freq(file_b, number_n);
        Node root = huffmanAlgorithm(pQueue);
//        HashMap<BigInteger, Integer> huffmanDict = extractDict(root);

    }
    //TODO: Implement Extraction of F dict
//    private HashMap<BigInteger, Integer> extractDict(Node root) {
//
//    }

    private PriorityQueue<Node> calculate_freq(byte[] file_b, int number_n){
        HashMap<BigInteger,Integer> hashMap = new HashMap<>();
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        for (int i = 0; i < file_b.length; i+=number_n) {
            byte[] nByte = Arrays.copyOfRange(file_b, i,i+number_n);
            BigInteger key = new BigInteger(nByte);
            int oldValue = hashMap.getOrDefault(key, 0);
            hashMap.put(key, oldValue +1);
        }
//        if (file_b.length % number_n != 0) {
//            //Store the remaining parts of the file
//
//            byte[] nByte = Arrays.copyOfRange(file_b, file_b.length - file_b.length % number_n, file_b.length);
//            byte[] arr = new byte[number_n];
//            Arrays.fill(arr, (byte) 0);
//            System.arraycopy(nByte,0,arr,0,);
//
//            for (int i = 0; i < number_n; i++) {
//                if (i<nByte.length)
//                    arr[i] = nByte[i];
//                else
//                    arr[i] = 0;
//            }
//            BigInteger key = new BigInteger(arr);
//            int oldValue = hashMap.getOrDefault(key, 0);
//            hashMap.put(key, oldValue +1);
//        }

        for (Map.Entry<BigInteger, Integer> e :hashMap.entrySet()) {
            Node node = new Node(e.getKey(), e.getValue());
            nodeQueue.add(node);
        }

        return nodeQueue;
    }

    private Node huffmanAlgorithm(PriorityQueue priorityQueue){
        int n = priorityQueue.size();
        PriorityQueue<Node> q = priorityQueue;
        for (int i = 0; i < n - 1; i++) {
            var left = q.poll();
            var right = q.poll();
            Node node = new Node(left, right);

            q.add(node);
        }
        return q.poll();
    }

}

