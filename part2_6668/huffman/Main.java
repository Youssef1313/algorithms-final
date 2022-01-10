package huffman;

import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean isCompressing;
        String filePath;
        int n = 1;
        if (args.length == 0) {
            isCompressing = true;
            //filePath = "/media/zayton/HDD-Data/desktop/eng/7thTerm/Alg/algorithms-final/part2_6668/file";
            filePath = "C:\\Users\\PC\\Desktop\\gbbct10.seq\\gbbct10.seq";
            //filePath = "C:\\Users\\PC\\Downloads\\Sheet 8.pdf";
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
            printTime();
            System.out.println("Reading the file...");
            var compress = new Compress();
            byte[] fb = compress.readingFile(filePath);
            printTime();
            System.out.println("Read " + fb.length + " bytes.");
            printTime();
            System.out.println("Constructing huffman tree...");
            Huffman huffman = new Huffman();
            var huffDict = huffman.huffman(fb, n);
            printTime();
            System.out.println("Constructed huffman tree...");
            printTime();
            System.out.println("Writing to the file...");
            var writer = new HuffmanFileWriter(huffDict, fb, n);
            writer.write("C:\\Users\\PC\\Desktop\\gbbct10.seq\\compressed.hc");
            printTime();
            System.out.println("Wrote the file to disk.");
        }
    }

    private static void printTime() {
        var now = Calendar.getInstance();
        System.out.print("[" + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "]: ");

    }
}
