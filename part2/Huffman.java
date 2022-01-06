package part2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Arrays;


public class Huffman {
    private class Node{
        Node (){

        }
        Node(int f){
            this.freq = f;
        }
        int freq=0;
        Node left;
        Node right;
    }
    public void huffman(byte[] file_b, int number_n){

    }

    private PriorityQueue<Node> calculate_freq(byte[] file_b, int number_n){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        int prevIndex = 0;
        for (int i = 0; i < file_b.length; i+=number_n) {
            prevIndex = i;
            byte[] nByte = Arrays.copyOfRange(file_b, i,i+number_n);
            int key = new BigInteger(nByte).intValue();
            if (hashMap.containsValue(key)){
                int v = hashMap.get(key);
                hashMap.put(key, v+1);
            }else{
                hashMap.put(key, 1);
            }
        }
        if (prevIndex != file_b.length) {
            //Store the remaining parts of the file
        }

        for (Map.Entry<Integer, Integer> e :hashMap.entrySet()) {
            Node node = new Node(e.getValue());
            nodeQueue.add(node);
        }

        return nodeQueue;
    }

    private Node huffmanAlgorithm(PriorityQueue priorityQueue){
        int n = priorityQueue.size();
        PriorityQueue<Node> q = priorityQueue;

        for (int i = 0; i < n - 1; i++) {
            Node node = new Node();
            node.left = q.poll();
            node.right = q.poll();
            node.freq = node.left.freq + node.right.freq;
            q.add(node);
        }

        return q.poll();
    }
}

