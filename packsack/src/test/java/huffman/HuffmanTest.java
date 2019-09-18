/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import io.FileInput;
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
    @Before
    public void setUp() {
        FileInput stream = new FileInput(testfile);
        
    }
    
    @Test
    public void HuffmanEncodingTest() {
        
    }          
}
