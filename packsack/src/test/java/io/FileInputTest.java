/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebserge
 */
public class FileInputTest {
    
    
    private FileInput stream;
    
    
    @Before
    public void setUp() {
        String testfile = getClass().getClassLoader().getResource("streamtestfile").getPath();
        stream = new FileInput(testfile);
    }
    
    @Test
    public void nextIntReturnsCorrectCharacters() {
        String result = "" + (char) stream.nextInt() + (char) stream.nextInt() + (char) stream.nextInt();
        assertEquals("wub", result);
    }
    
    @Test
    public void streamClosesCorrectly() {
        stream.close();
        assertEquals(-500, stream.nextInt());
    }
    
    @Test
    public void fileSizeCorrect() {
        assertEquals(19, stream.size());
    }
    
    @Test
    public void readFileSizeReturnsCorrectValue() {
        assertEquals(98, stream.readFileSize() & 0xFF);
    }
    
}
