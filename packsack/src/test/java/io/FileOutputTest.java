/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebserge
 */
public class FileOutputTest {
    
    private FileOutput stream;
    String testfile = getClass().getClassLoader().getResource("streamoutputfile").getPath();
    
    @Test
    public void outputWritesCorrectChar() {
        FileOutput stream = new FileOutput(testfile, false);
        FileInput inputStream = new FileInput(testfile + ".sebbe");
        stream.write('a');
        stream.close();
        assertEquals('a', inputStream.nextInt());
        inputStream.close();
    }

    @Test
    public void outputStreamClosesCorrectly() {
        FileOutput stream = new FileOutput(testfile, false);
        FileInput inputStream = new FileInput(testfile + ".sebbe");
        
        stream.close();
        stream.write('a');
        assertEquals(-1, inputStream.nextInt());
        inputStream.close();
    }
    
}
