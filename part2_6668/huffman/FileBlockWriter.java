package huffman;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileBlockWriter {
    private final FileOutputStream fileOutputStream;
    private final int blockSizeInBytes;
    private final byte[] blocks;
    private int counter = 0;

    public FileBlockWriter(FileOutputStream fileOutputStream, int blockSizeInBytes) {
        this.fileOutputStream = fileOutputStream;
        this.blockSizeInBytes = blockSizeInBytes;
        this.blocks = new byte[blockSizeInBytes];
    }

    public void write(int b) throws IOException {
        blocks[counter++] = (byte)b;

        if (counter == blockSizeInBytes) {
            fileOutputStream.write(blocks);
            counter = 0;
        }
    }

    public void commitRemaining() throws IOException {
        for (int i = 0; i < counter; i++) {
            fileOutputStream.write(blocks[i]);
        }

        counter = 0;
    }
}
