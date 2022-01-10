package huffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;

public final class HuffmanFileWriter {
    private final HashMap<BigInteger, String> encodingDictionary;
    private final byte[] originalFile;
    private final int n;

    public HuffmanFileWriter(HashMap<BigInteger, String> encodingDictionary, byte[] originalFile, int n) {
        this.encodingDictionary = encodingDictionary;
        this.originalFile = originalFile;
        this.n = n;
    }

    public void write(String outputPath) throws IOException {
        // SIZE_BYTE1 SIZE_BYTE2 SIZE_BYTE3 SIZE_BYTE4
        // n NUMBER_OF_ELEMENTS_IN_DICTIONARY(S) SIZE_OF_ELEMENT_ENCODING_IN_BITS(ENC)
        // BYTE1_1 BYTE2_1 ... BYTE_N_1  (the bytes of first element in dictionary)
        // BYTE1_2 BYTE2_2 ... BYTE_N_2  (the bytes of second element in dictionary)
        // .....
        // BYTE1_S .............
        // THE ENCODINGS, EACH HAS ENC bit
        var file = new File(outputPath);
        if (file.exists()) {
            file.delete();
        }

        try (var writer = new FileOutputStream(file)) {
            // https://stackoverflow.com/a/6374970/5108631
            var size = ByteBuffer.allocate(4).putInt(originalFile.length).array();
            writer.write(size);

            // While this overload takes an integer, it only writes the least significant **byte**.
            // This is expected per the documentation, and it's the behavior we want.
            writer.write(n);
            writer.write(encodingDictionary.size());

            // The idea here is that we have a variable-length encoding.
            // We want to store it in the file in a consistent non-ambiguous format.
            // What we're doing here is that we append a "1" bit to the end and store it fixed-size.
            // Example:
            // | Actual encodings | How we write it in a fixed-size format |
            // |------------------|----------------------------------------|
            // | n_bytes1 = 000   | 1000                                   |
            // | n_bytes2 = 001   | 1001                                   |
            // | n_bytes3 = 010   | 1010                                   |
            // | n_bytes4 = 011   | 1011                                   |
            // | n_bytes5 = 10    | 0110                                   |
            // | n_bytes6 = 11    | 0111                                   |
            // Later on, the way to read it is as follows.
            // Read SIZE_OF_ELEMENT_ENCODING_IN_BITS bits, ignore bits up to the first "1" bit.
            // The rest is our encoding.
            // The size of element encoding is max_bits(actual_encodings) + 1
            var sizeOfElementEncodingInBits = getSizeOfElementEncodingInBits();
            writer.write(sizeOfElementEncodingInBits);

            var pairs = encodingDictionary.entrySet();
            for (var pair : pairs) {
                var bytes = pair.getKey().toByteArray();
                assert bytes.length <= n;
                for (int i = 0; i < (n - bytes.length); i++) {
                    writer.write(0);
                }

                writer.write(bytes);
            }
            updateDictionaryToFixedSizeEncoding(sizeOfElementEncodingInBits);
            writeEncodings(writer, originalFile, sizeOfElementEncodingInBits);
        }
    }

    private void writeEncodings(FileOutputStream writer, byte[] originalFile, int sizeOfElementEncodingInBits) throws IOException {
        var builder = new StringBuilder(originalFile.length * sizeOfElementEncodingInBits);
        for (var b : originalFile) {
            builder.append(encodingDictionary.get(b));
        }

        // A file can't contain a partial of byte.
        // Adding zeros to the end shouldn't be ambiguous since no encoding is a prefix of another.
        while (builder.length() % 8 != 0) {
            builder.append('0');
        }

        var encodedBitsAsString = builder.toString();
        for (int i = 0; i < encodedBitsAsString.length(); i += 8) {
            var encoded = Integer.parseInt(encodedBitsAsString, i, i + 7, 2);
            writer.write(encoded);
        }
    }

    private void updateDictionaryToFixedSizeEncoding(int sizeOfElementEncodingInBits) {
        for (var pair : encodingDictionary.entrySet()) {
            var value = pair.getValue();
            assert sizeOfElementEncodingInBits > value.length();
            var builder = new StringBuilder(sizeOfElementEncodingInBits);
            for (int i = 0; i < sizeOfElementEncodingInBits - value.length() - 1; i++) {
                builder.append('0');
            }
            builder.append('1');
            builder.append(value);
            var fixedSizeEncoding = builder.toString();
            assert fixedSizeEncoding.length() == sizeOfElementEncodingInBits;
            encodingDictionary.put(pair.getKey(), fixedSizeEncoding);
        }
    }

    private int getSizeOfElementEncodingInBits() {
        int maxLength = -1;
        for (var encoding : encodingDictionary.values()) {
            var encodingLength = encoding.length();
            if (encodingLength > maxLength) {
                maxLength = encodingLength;
            }
        }

        return maxLength + 1;
    }
}
