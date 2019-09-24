/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import io.FileInput;
import io.FileOutput;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.HuffNode;
import util.HuffTree;

/**
 *
 * @author sebserge
 */
public class HuffmanTest {
    String testfile = getClass().getClassLoader().getResource("hufftreetestfile").getPath();
    Huffman huffman; 
            
    @Before
    public void setUp() {
        Huffman huffman = new Huffman();
    }
    
    @Test
    public void HuffmanDecompress() {
//        String decompressFile = getClass().getClassLoader().getResource("testDecompression").getPath();
//        FileOutput output = new FileOutput(decompressFile, true);
//        huffman.decompress(decompressFile, decompressFile + ".txt");
//        FileInput input = new FileInput(decompressFile + ".txt");
//        assertEquals(0, input.nextInt());
    }
    
}
