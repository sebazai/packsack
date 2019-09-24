/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sebserge
 */
public class HuffmanTest {
    String testfile = getClass().getClassLoader().getResource("hufftreetestfile").getPath();
    Huffman huffman; 
    byte[] compressedBytes;
            
    @Before
    public void setUp() throws IOException {
        Huffman huffman = new Huffman();
        huffman.compress(testfile);
        String compressed = testfile.concat(".sebbe");
        compressedBytes = Files.readAllBytes(Paths.get(compressed));
    }
    
    @Test
    public void FileSizeCorrectInCompressedFile() {
        byte[] sizeIsCorrect = {0,0,0,14};
        assertEquals(sizeIsCorrect[3], compressedBytes[3]);
    }
    
    @Test
    public void treeStartsCorrectlyInCompressedFile() {
        byte[] treeStartShouldBe = {80, 84, 36};
        boolean isTrue = true;
        for (int i = 0; i < 3; i++) {
            if(treeStartShouldBe[i] != compressedBytes[i+4]) {
                isTrue = false;
            }
        }
        assertTrue(isTrue);
    }
    
}
