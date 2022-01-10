package huffman;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Compress {
    public byte[] readingFile(String filename) {
        try (var file = new RandomAccessFile(new File(filename), "r")) {
            //Get file channel in read-only mode
            FileChannel fileChannel = file.getChannel();

            //Get direct byte buffer access using channel.map() operation
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            var file_b = new byte[(int) fileChannel.size()];
            // the buffer now reads the file as if it were loaded in memory.
            assert buffer.isLoaded() == false;
            buffer.get(file_b);
            return file_b;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
