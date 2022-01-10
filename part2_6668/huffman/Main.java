package huffman;

public class Main {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        // 01111111111111111111111111111111
        int n = 1;
        Compress compress = new Compress();
        byte[] fb = compress.readingFile("/media/zayton/HDD-Data/desktop/eng/7thTerm/Alg/algorithms-final/part2_6668/file");
        Huffman huffman = new Huffman();
        var huffDict = huffman.huffman(fb, n);

        var writer = new HuffmanFileWriter(huffDict, fb, n);
        System.out.println("ok");
        // n = 1
        //
        // a = 000            1000 -> 8
        // b = 001            1001
        // c = 010            1010
        // d = 011            1011
        // e = 10             0110
        // f = 11             0111

        // 10001110010110101001010101111000010


        // [a:000, b:001, ]
        // 0000000000000 1011101
        // SIZE1 SIZE2 SIZE3 SIZE4
        // ASCII_A 3 0
        // ASCII_B 3 1
        // ASCII_C 3 2
        // ASCII_D 3 3
        // ASCII_E 2 2
        // ASCII_F 2 3

        // SIZE1 SIZE2 SIZE3 SIZE4
        // ASCII_A 8            000
        // ASCII_B 9            10
        // ASCII_C 10
        // ASCII_D 11
        // ASCII_E 6
        // ASCII_F 3

        // SIZE1 SIZE2 SIZE3 SIZE4
        // N NUMBER_OF_ELEMENTS_IN_DICTIONARY(S) SIZE_OF_ELEMENT_ENCODING_IN_BITS(ENC)
        // BYTE1_1 BYTE2_1 ... BYTE_N_1  (the bytes of first element in dictionary)
        // BYTE1_2 BYTE2_2 ... BYTE_N_2  (the bytes of second element in dictionary)
        // .....
        // BYTE1_S .............
        // THE ENCODINGS, EACH HAS ENC bit


        // a = 00001111
        // 0000111100010000 file
        //["00001111" : 0] ->
        // 0001
        //
        // bytes ->
        //2 - 0000111100010000  -> big
        // size
        //
    }
}
