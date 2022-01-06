package part2;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;
public class Compress {

    public byte[] readingFile(String filename){
        byte[] file_b = {};
        try (RandomAccessFile file = new RandomAccessFile(new File(filename), "r"))
        {
            //Get file channel in read-only mode
            FileChannel fileChannel = file.getChannel();

            //Get direct byte buffer access using channel.map() operation
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            file_b = new byte[(int) fileChannel.size()];
            // the buffer now reads the file as if it were loaded in memory.
            System.out.println(buffer.isLoaded());  //prints false
            System.out.println(buffer.capacity());  //Get the size based on content size of file

            buffer.get(file_b);
            //You can read the file from this buffer the way you like.
//            for (int i = 0; i < buffer.limit(); i++)
//            {
//                System.out.print((char) buffer.get()); //Print the content of file
//            }
        }catch (IOException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < 10; i+=1) {
//            System.out.println(Arrays.stream(findSlice(file_b, i, i)).toArray());
//        }
//        byte[] test = new byte[2];
//        test[0] = file_b[0];
//        test[1] = file_b[1];
//        for (int i = 0; i < 2; i++) {
//            System.out.println(test[i]);
//        }
//
//        for(final byte b : test) {
//            System.out.print(Integer.toString(b & 0xFF /* thx Jason Day */, 2));
//        }
//        System.out.println();

//        int intTest = new BigInteger(test).intValue();
//        System.out.println(intTest);
//        HashMap<Integer,Integer> hashMap = new HashMap<>();
//        hashMap.put(intTest,1);
//        byte[] slice = Arrays.copyOfRange(file_b,0,2);
//        int tint = new BigInteger(slice).intValue();
//        for (int i = 0; i < 2; i++) {
//            System.out.println(slice[i]);
//        }
//        System.out.println(hashMap.containsKey(tint));
//        System.out.println(slice.toString());
//        for (int i = 0; i < 2; i++) {
//            System.out.println(file_b[i]);
//        }
        return file_b;
    }

}
