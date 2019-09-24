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
import util.ByteStringManipulator;

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
    
    @Test
    public void writeToOutputAString() {
        FileOutput stream = new FileOutput(testfile, false);
        FileInput inputStream = new FileInput(testfile + ".sebbe");
        
        stream.writeToOutputFile("01100010", new ByteStringManipulator());
        assertEquals((byte) (char) 'b', inputStream.nextInt());
        stream.close();
        inputStream.close();
    }
    
    @Test
    public void writeByteArrayWorks() {
        FileOutput stream = new FileOutput(testfile, false);
        FileInput inputStream = new FileInput(testfile + ".sebbe");
        
        byte[] arrayToWrite = {0, (byte) (char) 'a', 0, 0};
        stream.writeArray(arrayToWrite);
        //First is empty
        int shoulNotbe = inputStream.nextInt();
        int shouldBe = inputStream.nextInt();
        assertEquals((byte) (char) 'a', shouldBe);
    
    }
}