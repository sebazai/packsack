/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
    
    public int nextInt() {
        try {
            return stream.read();
        } catch (IOException ex) {
            ex.printStackTrace();
            return -500;
        }
        
    }
    
    public long size() {
        return new File(path).length();
    }
    
    public void close() {
        try {
            stream.close();
        } catch (IOException ex) {
            Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
