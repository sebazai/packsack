/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packsack.io;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Input stream for file bytes.
 * @author sebserge
 */
public class FileInput {
    FileInputStream stream;
    String path;
    public FileInput(String pathToFile) {
        try {
            this.path = pathToFile;
            this.stream = new FileInputStream(this.path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Read next byte from stream
     * @return as integer
     */
    public int nextInt() {
        try {
            return stream.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public byte[] readAllBytes() {
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(Paths.get(this.path));
        } catch (IOException e) {
            fileBytes = new byte[0];
            e.printStackTrace();
        }
        return fileBytes;
    }
    
    /**
     * Size of file
     * @return the length of files
     */
    public int size() {
        return (int) new File(path).length();
    }
    
    /**
     * Reads the file size of compressed file
     * @return the file size as integer
     */
    public int readFileSize() {
        try {
            byte[] size = new byte[4];
            size[0] = (byte) stream.read();
            size[1] = (byte) stream.read();
            size[2] = (byte) stream.read();
            size[3] = (byte) stream.read();
            return ByteBuffer.wrap(size).getInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Closes the input steam.
     */
    public void close() {
        try {
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
