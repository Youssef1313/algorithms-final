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
        int prevIndex = 0;
        for (int i = 0; i < file_b.length; i+=number_n) {
            prevIndex = i;
            byte[] nByte = Arrays.copyOfRange(file_b, i,i+number_n);
            BigInteger key = new BigInteger(nByte);
            if (hashMap.containsKey(key)){
                int v = hashMap.get(key);
                hashMap.put(key, v+1);
            }else{
                hashMap.put(key, 1);
            }
        }
        if (prevIndex+number_n != file_b.length) {
            //Store the remaining parts of the file
            byte[] nByte = Arrays.copyOfRange(file_b, prevIndex, prevIndex+number_n);
            byte[] arr = new byte[number_n];

            for (int i = 0; i < number_n; i++) {
                if (i<nByte.length)
                    arr[i] = nByte[i];
                else
                    arr[i] = 0;
            }
            BigInteger key = new BigInteger(arr);
            if (hashMap.containsValue(key)){
                int v = hashMap.get(key);
                hashMap.put(key, v+1);
            }else{
                hashMap.put(key, 1);
            }
        }

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

