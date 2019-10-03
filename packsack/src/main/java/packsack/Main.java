package packsack;

import packsack.io.FileInput;
import java.text.DecimalFormat;
import packsack.huffman.HuffmanCompress;
import packsack.huffman.HuffmanDecompress;

/**
 * Main.java
 * @author sebserge
 */
public class Main {
    
    private static final DecimalFormat DFORMAT = new DecimalFormat("#.##");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String todo = args[0];
            String filePath = args[1];
            HuffmanCompress huff = new HuffmanCompress();
            if (todo.equals("-co")) {

                long startTime = System.currentTimeMillis();
                double sizeAtStart = (double) huff.compress(filePath);
                long endTime = System.currentTimeMillis();
                
                // Read compressed file size
                FileInput file = new FileInput(filePath + ".sebbe");
                double sizeAtEnd = (double) file.size();
                file.close();
                printStatistics(endTime, startTime, sizeAtStart, sizeAtEnd);
                
            } else if (todo.equals("-de")) {
                String outputPath = args[2];
                
                HuffmanDecompress huffdecomp = new HuffmanDecompress();
                
                long startTime = System.currentTimeMillis();
                huffdecomp.decompress(filePath, outputPath);
                long endTime = System.currentTimeMillis();
                
                System.out.println("Time to decompress: " + (endTime - startTime) + " ms");
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Invalid arguments or file paths");
            System.out.println("Valid arguments are:");
            System.out.println("-co <file> & -de <fileInput> <fileOutput>");
        }
    }
    
    public static void printStatistics(long endTime, long startTime, double sizeAtStart, double sizeAtEnd) {
        System.out.println("Time to compress: "  + (endTime - startTime) + " ms");
        System.out.println("Size of original file: " + sizeAtStart + " Bytes");
        System.out.println("Size of compressed file: " + sizeAtEnd + " Bytes");
        System.out.println("Total compression ratio: " + DFORMAT.format(sizeAtStart / sizeAtEnd));
        System.out.println("Space saved: " + DFORMAT.format((1 - (sizeAtEnd / sizeAtStart)) * 100) + " %");
    }

}