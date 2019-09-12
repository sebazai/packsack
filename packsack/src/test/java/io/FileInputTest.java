/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.FileNotFoundException;
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
}
