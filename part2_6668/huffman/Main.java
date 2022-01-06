package huffman;

public class Main {

    public static void main(String[] args) {
        Compress compress = new Compress();
        byte[] fb = compress.readingFile("/media/zayton/HDD-Data/desktop/eng/7thTerm/Alg/algorithms-final/part2_6668/file");
        Huffman huffman = new Huffman();
        huffman.huffman(fb, 1);
        System.out.printf("ok");
    }
}
