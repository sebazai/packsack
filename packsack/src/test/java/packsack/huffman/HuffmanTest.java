/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packsack.huffman;

import packsack.huffman.Huffman;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    String testfileTwo = getClass().getClassLoader().getResource("abracadabratest.txt").getPath();
    Huffman huffman; 
    Huffman huffmanTwo;
    byte[] compressedBytes;
    byte[] compressedBytesTwo;
            
    @Before
    public void setUp() throws IOException {
        huffman = new Huffman();
        huffman.compress(testfile);
        String compressed = testfile.concat(".sebbe");
        compressedBytes = Files.readAllBytes(Paths.get(compressed));
        
        huffmanTwo = new Huffman();
        huffmanTwo.compress(testfileTwo);
        String compressedTwo = testfileTwo.concat(".sebbe");
        compressedBytesTwo = Files.readAllBytes(Paths.get(compressedTwo));
    }
    
    @Test
    public void fileSizeCorrectInCompressedFile() {
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
    
    @Test
    public void testFileTwoHeaderSizeIsCorrect() {
        byte[] sizeIsCorrect = {0,0,0,12};
        boolean isTrue = true;
        for (int i = 0; i < 3; i++) {
            if (sizeIsCorrect[i] != compressedBytesTwo[i]) {
                isTrue = false;
            }
        }
        assertTrue(isTrue);
    }
    
    @Test
    public void testFileTwoTreeIsCorrect() {
        byte[] treeShouldBe = {80, 74, 34, 67, 67, 80, -86, 64};
        boolean isTrue = true;
        for (int i = 0; i < treeShouldBe.length; i++) {
            if (treeShouldBe[i] != compressedBytesTwo[i+4]) {
                isTrue = false;
            }
        }
        assertTrue(isTrue);
    }
    
    @Test
    public void testFileTwoCorrectDataAfterTree() {
        byte[] dataShouldBe = {110, -76, 110, -96};
        boolean isTrue = true;
        for (int i = 0; i < dataShouldBe.length; i++) {
            if (dataShouldBe[i] != compressedBytesTwo[i+12]) {
                
                isTrue = false;
            }
        }
        assertTrue(isTrue);
    }
    
}
