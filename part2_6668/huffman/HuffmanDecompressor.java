package huffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;

public final class HuffmanDecompressor {
    private static final int FIRST_DICTIONARY_ELEMENT_INDEX = 7;

    private HuffmanDecompressor() {
    }

    public static void decompress(byte[] compressedFile, String filePath) throws IOException {
        // SIZE_BYTE1 SIZE_BYTE2 SIZE_BYTE3 SIZE_BYTE4
        // n NUMBER_OF_ELEMENTS_IN_DICTIONARY(S) SIZE_OF_ELEMENT_ENCODING_IN_BITS(ENC)
        // BYTE1_1 BYTE2_1 ... BYTE_N_1  (the bytes of first element in dictionary)
        // BYTE1_2 BYTE2_2 ... BYTE_N_2  (the bytes of second element in dictionary)
        // .....
        // BYTE1_S .............
        // THE ENCODINGS, EACH HAS ENC bit
        int originalSize = ByteBuffer.wrap(new byte[] {compressedFile[0], compressedFile[1], compressedFile[2], compressedFile[3]}).getInt();
        int n = compressedFile[4];
        int numberOfElementsInDictionary = compressedFile[5];
        int sizeOfElementEncodingsInBits = compressedFile[6];
        var dictionary = new HashMap<String, BigInteger>(numberOfElementsInDictionary);
        var dictionary_element = new byte[n];
        int read_bytes = FIRST_DICTIONARY_ELEMENT_INDEX; // the number of already-read bytes. ie, the index to read next.
        for (int i = 0; i < numberOfElementsInDictionary; i++) {
            for (int j = 0; j < n; j++) {
                dictionary_element[j] = compressedFile[read_bytes++];
            }

            dictionary.put(readEncodingFromHeader(compressedFile, i, sizeOfElementEncodingsInBits, n, numberOfElementsInDictionary), new BigInteger(dictionary_element));
        }

        int startOfEncodings = FIRST_DICTIONARY_ELEMENT_INDEX + n * numberOfElementsInDictionary;
        int offsetBits = numberOfElementsInDictionary + sizeOfElementEncodingsInBits;
        var builder = new StringBuilder(numberOfElementsInDictionary);

        var file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        try (var writer = new FileOutputStream(file)) {
            var blockWriter = new FileBlockWriter(writer, 2048);
            while (startOfEncodings + offsetBits / 8 < compressedFile.length) {
                boolean bit = readBit(compressedFile, startOfEncodings, offsetBits);
                builder.append(bit ? '1' : '0');
                if (dictionary.containsKey(builder.toString())) {
                    var bytes = dictionary.get(builder.toString()).toByteArray();
                    for (var b : bytes) {
                        blockWriter.write(b);
                    }

                    builder.setLength(0);
                }
                offsetBits++;
            }

            blockWriter.commitRemaining();
        }
    }

    private static String readEncodingFromHeader(byte[] decompressedFile, int elementIndex, int sizeOfElementEncodingsInBits, int n, int numberOfElementsInDictionary) {
        int startOfEncodings = FIRST_DICTIONARY_ELEMENT_INDEX + n * numberOfElementsInDictionary;
        int bitOffset = elementIndex * sizeOfElementEncodingsInBits;
        var builder = new StringBuilder(sizeOfElementEncodingsInBits);
        boolean startReading = false;
        for (int i = 0; i < sizeOfElementEncodingsInBits; i++) {
            // Ignore any leading zeros and also the first one bit.
            // 0 0 0 1 0 1 1 0
            //         ^
            // Start reading from the arrow position above.
            if (readBit(decompressedFile, startOfEncodings, bitOffset + i)) {
                if (!startReading) {
                    startReading = true;
                    continue;
                }
                builder.append('1');
            } else {
                if (!startReading) {
                    continue;
                }
                builder.append('0');
            }
        }
        return builder.toString();
    }

    private static boolean readBit(byte[] decompressedFile, int offsetBytes, int offsetBits) {
        offsetBytes += offsetBits / 8;
        offsetBits = offsetBits % 8; // Offset is from most significant bit.
        var b = decompressedFile[offsetBytes];
        // For illustration:
        // Assume offsetBits is 6, we want the mask to be 00000010
        return ((1 << (8 - offsetBits - 1)) & b) != 0;
    }
}
