/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author sebserge
 */
public class FileInput {
    FileInputStream stream;
    String path;
    public FileInput(String pathToFile) throws FileNotFoundException {
        try {
            this.path = pathToFile;
            this.stream = new FileInputStream(this.path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public int nextInt() throws IOException {
        return stream.read();
    }
    
    public void close() throws IOException {
        stream.close();
    }
}
