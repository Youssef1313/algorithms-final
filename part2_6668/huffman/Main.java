package huffman;

import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean isCompressing;
        String filePath;
        int n = 1;
        if (args.length == 0) {
            isCompressing = false;
            //filePath = "/media/zayton/HDD-Data/desktop/eng/7thTerm/Alg/algorithms-final/part2_6668/file";
            filePath = "C:\\Users\\PC\\Desktop\\gbbct10.seq\\testcompressed.txt";
            //filePath = "C:\\Users\\PC\\Downloads\\Sheet 8.pdf";

            //filePath = "C:\\Users\\PC\\Desktop\\gbbct10.seq\\test";
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

        printMessageWithTime("Reading the file...");
        byte[] fb = FileReader.readFile(filePath);
        printMessageWithTime("Read " + fb.length + " bytes.");
        if (isCompressing) {
            printMessageWithTime("Constructing huffman tree...");
            Huffman huffman = new Huffman();
            var huffDict = huffman.huffman(fb, n);

            printMessageWithTime("Constructed huffman tree...");
            printMessageWithTime("Writing to the file...");
            var writer = new HuffmanFileWriter(huffDict, fb, n);
            writer.write("C:\\Users\\PC\\Desktop\\gbbct10.seq\\testcompressed.txt");
            printMessageWithTime("Wrote the file to disk.");
        } else {
            HuffmanDecompressor.decompress(fb, "C:\\Users\\PC\\Desktop\\gbbct10.seq\\testdecompressed.txt");
        }
    }

    public static void printMessageWithTime(String s) {
        var now = Calendar.getInstance();
        System.out.print("[" + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "]: ");
        System.out.println(s);
    }
}
