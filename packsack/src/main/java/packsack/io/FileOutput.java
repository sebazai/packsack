package packsack.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import packsack.util.ByteStringManipulator;

/**
 *
 * @author sebserge
 */
public class FileOutput {
    FileOutputStream stream;
    ByteStringManipulator manipulator;
    
    /**
     * Output stream for file bytes.
     * @param path Path where to output
     * @param decompress if false, add .sebbe to file end
     */
    public FileOutput(String path, boolean decompress) {
        if (!decompress) {
            path += ".sebbe";
        }
        try {
            stream = new FileOutputStream(path);
            manipulator = new ByteStringManipulator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Write one byte.
     * @param i byte to write
     */
    public void write(int i) {
        try {
            stream.write(i);
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
    
    /**
     * Write an array of bytes.
     * @param byteArray aray of bytes to write
     */
    public void writeArray(byte[] byteArray) {
        try {
            stream.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Writes in 8 bit sequence from the encoded binary string.
     * @param toWrite Encoded binary string
     * @return The bits that left in the string
     */
    public String writeToOutputFile(String toWrite) {
        while (toWrite.length() >= 8) {
            this.write(manipulator.stringToByte(toWrite.substring(0, 8)));
            toWrite = toWrite.substring(8);
        }
        return toWrite;
    }
    
    /**
     * Close the stream.
     */
    public void close() {
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
