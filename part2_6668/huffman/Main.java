package huffman;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean isCompressing;
        String filePath;
        int n = 1;
        if (args.length == 0) {
            isCompressing = true;
            //filePath = "/media/zayton/HDD-Data/desktop/eng/7thTerm/Alg/algorithms-final/part2_6668/compressed.hc";

            filePath = "C:\\Users\\PC\\Desktop\\algorithms-final-mvn\\algorithms-final\\test_group_16.txt";
            //filePath = "C:\\Users\\PC\\Desktop\\algorithms-final-mvn\\algorithms-final\\6668.3.test_group_16.txt.hc";

            //filePath = "C:\\Users\\PC\\Desktop\\gbbct10.seq\\gbbct10.seq";
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
        var directory = Paths.get(filePath).getParent().toString();
        var oldFileName = Paths.get(filePath).getFileName().toString();

        if (isCompressing) {
            printMessageWithTime("Constructing huffman tree...");
            Huffman huffman = new Huffman();
            var huffDict = huffman.huffman(fb, n);

            printMessageWithTime("Constructed huffman tree...");

            var compressedFileName = Paths.get(directory, "6668." + n + "." + oldFileName + ".hc").toString();

            printMessageWithTime("Writing to the file: " + compressedFileName);
            var writer = new HuffmanFileWriter(huffDict, fb, n);

            writer.write(compressedFileName);
            var compressedSize = Files.size(Paths.get(compressedFileName));
            printMessageWithTime("Wrote the file (" + compressedSize + " bytes) to disk.");
            printMessageWithTime("Compression ratio: " + ((double) compressedSize / fb.length) * 100);
        } else {
            if (oldFileName.endsWith(".hc")) {
                oldFileName = oldFileName.substring(0, oldFileName.length() - 3);
            }
            var compressedFileName = Paths.get(directory, "extracted." + oldFileName).toString();
            System.out.println(compressedFileName);
            HuffmanDecompressor.decompress(fb, compressedFileName);
        }
    }

    public static void printMessageWithTime(String s) {
        var now = Calendar.getInstance();
        System.out.print("[" + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "]: ");
        System.out.println(s);
    }
}
