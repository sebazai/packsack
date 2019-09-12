/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author sebserge
 */
public class FileOutput {
    FileOutputStream stream;
    
    public FileOutput(String path) {
        try {
            stream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void write(int i) {
        try {
            stream.write(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void close() throws IOException {
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
