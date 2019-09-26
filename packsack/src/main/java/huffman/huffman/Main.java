package huffman.huffman;

import huffman.io.FileInput;
import java.text.DecimalFormat;

/**
 * Main.java
 * @author sebserge
 */
public class Main {
    
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String todo = args[0];
            String filePath = args[1];
            if (todo.equals("-co")) {
                Huffman huff = new Huffman();
                
                long startTime = System.currentTimeMillis();
                double sizeAtStart = (double) huff.compress(filePath);
                long endTime = System.currentTimeMillis();
                
                // Read compressed file size
                FileInput file = new FileInput(filePath + ".sebbe");
                double sizeAtEnd = (double) file.size();
                file.close();
                
                System.out.println("Time to compress: "  + (endTime - startTime) + " ms");
                System.out.println("Size of original file: " + sizeAtStart + " Bytes");
                System.out.println("Size of compressed file: " + sizeAtEnd + " Bytes");
                System.out.println("Total compression ratio: " + df2.format(sizeAtStart / sizeAtEnd));
                System.out.println("Space saved: " + df2.format((1-(sizeAtEnd/sizeAtStart))*100) + " %");
            } else if (todo.equals("-de")) {
                String outputPath = args[2];
                
                Huffman huff = new Huffman();
                
                long startTime = System.currentTimeMillis();
                huff.decompress(filePath, outputPath);
                long endTime = System.currentTimeMillis();
                
                System.out.println("Time to decompress: " + (endTime - startTime));
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Invalid arguments or file paths");
            System.out.println("Valid arguments are:");
            System.out.println("-co <file> & -de <fileInput> <fileOutput>");
        }
    }

}