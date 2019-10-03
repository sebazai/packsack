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

/**
 *
 * @author sebserge
 */
public class HuffmanDecompressTest {
    
    @Test
    public void decompressFileTwoReturnsCorrectData() throws IOException {
        HuffmanDecompress huffman = new HuffmanDecompress();
        String inputPath = getClass().getClassLoader().getResource("compressedFile").getPath();
        String outputPath = inputPath.concat(".txt");
        
        huffman.decompress(inputPath, outputPath);
        
        byte[] bytes = Files.readAllBytes(Paths.get(outputPath));
        String data = new String(bytes);
        assertEquals("ABRACADABRA!", data);
    }
}
