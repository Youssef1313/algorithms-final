package huffman;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean isCompressing;
        String filePath;
        int n = 1;
        if (args.length == 0) {
            isCompressing = true;
            //filePath = "/media/zayton/HDD-Data/desktop/eng/7thTerm/Alg/algorithms-final/part2_6668/file";
            filePath = "C:\\Users\\PC\\Desktop\\gbbct10.seq\\gbbct10.seq";
        } else {
            if (args[0] != "c" && args[0] != "d") {
                throw new Exception("The first argument should either be 'c' or 'd'.");
            }

            isCompressing = args[0] == "c";
            filePath = args[1];
            if (isCompressing) {
                n = Integer.parseInt(args[2]);
            }
        }

        if (isCompressing) {
            System.out.println("Reading the file...");
            var compress = new Compress();
            byte[] fb = compress.readingFile(filePath);
            System.out.println("Read " + String.valueOf(fb.length) + " bytes.");
            System.out.println("Constructing huffman tree...");
            Huffman huffman = new Huffman();
            var huffDict = huffman.huffman(fb, n);
            System.out.println("Constructed huffman tree...");
            System.out.println("Writing to the file...");
            var writer = new HuffmanFileWriter(huffDict, fb, n);
            writer.write("C:\\Users\\PC\\Desktop\\gbbct10.seq\\compressed.hc");
            System.out.println("Wrote the file to disk.");
        }
    }
}
