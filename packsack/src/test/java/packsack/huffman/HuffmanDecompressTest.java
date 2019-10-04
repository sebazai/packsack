/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packsack.huffman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import packsack.io.FileInput;
import packsack.util.HuffNode;

/**
 *
 * @author sebserge
 */
public class HuffmanDecompressTest {
    
    HuffmanDecompress huffman;
    String inputPath = getClass().getClassLoader().getResource("compressedFile").getPath();
    String outputPath = inputPath.concat(".txt");
    
    @Before
    public void setUp() {
        huffman = new HuffmanDecompress();
        huffman.decompress(inputPath, outputPath);
    }
    
    @Test
    public void decompressFileTwoReturnsCorrectData() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(outputPath));
        String data = new String(bytes);
        assertEquals("ABRACADABRA!", data);
    }
    
    /**
     * Check if the whole tree matches tree in https://algs4.cs.princeton.edu/lectures/55DataCompression-2x2.pdf
     */
    @Test
    public void decompressBuildTreeCorrect() {
        HuffNode root = huffman.getRootNode();
        assertEquals('A', (char) root.getLeftNode().getCharacter());
        assertEquals('D', (char) root.getRightNode().getLeftNode().getLeftNode().getCharacter());
        assertEquals('!', (char) root.getRightNode().getLeftNode().getRightNode().getLeftNode().getCharacter());
        assertEquals('C', (char) root.getRightNode().getLeftNode().getRightNode().getRightNode().getCharacter());
        assertEquals('R', (char) root.getRightNode().getRightNode().getLeftNode().getCharacter());
        assertEquals('B', (char) root.getRightNode().getRightNode().getRightNode().getCharacter());
    }
}
