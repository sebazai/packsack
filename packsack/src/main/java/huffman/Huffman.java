/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;
import io.FileInput;
import java.io.FileNotFoundException;
import java.io.IOException;
import util.HuffNode;
import util.HuffTree;
/**
 *
 * @author sebserge
 */
public class Huffman {
    private HuffTree tree;
    private String treeAsBinary;
    public Huffman() {
        this.tree = new HuffTree();
        this.treeAsBinary = "";
    }
    
    public void compress(String filePath) {
        FileInput stream = new FileInput(filePath);
        int[] occurrences = new int[256];

        int nextInt;
        while ((nextInt = stream.nextInt()) >= 0) {
          occurrences[toUnsignedInt(nextInt)]++;
        }
//        for(int i = 0; i < occurrences.length; i++) {
//            System.out.println(i + " " + occurrences[i]);
//        }
        this.tree.buildHuffTree(occurrences);
        HuffNode rootnode = this.tree.getRoot();
        stream.close();
        
        String[] codeTable = new String[256];
        createEncodingTable(codeTable, rootnode, "");
//        System.out.println(Integer.toBinaryString(((char) 'a' & 0xFF) + 256) );
//        for(int i = 0; i < codeTable.length; i++) {
//            if(codeTable[i] != null ) System.out.println(i + " " + codeTable[i]);
//        }
        writeTree(rootnode);
        
    }
    
    public void createEncodingTable(String[] table, HuffNode node, String code) {
        if (node.isLeaf()) {
            table[node.getCharacter() & 0xFF] = code;
            return;
        }
        createEncodingTable(table, node.getLeftNode(), code + "0");
        createEncodingTable(table, node.getRightNode(), code + "1");
    }
    /**
     * Write HuffTree recursively to binary string.
     * @param node start from root
     */
    public void writeTree(HuffNode node) {
        if (node.isLeaf()) {
            treeAsBinary = treeAsBinary.concat("1");
            treeAsBinary = treeAsBinary.concat(byteToString(node.getCharacter()));
            return;
        }
        treeAsBinary = treeAsBinary.concat("0");
        writeTree(node.getLeftNode());
        writeTree(node.getRightNode());
    }
    
    /**
     * Convert a byte to a string of length 8. 
     * Add 256 to pad the start with zeros
     * 
     * @param c int/char to convert to a string
     * @return substring 1-9, 8 bit string for the int/char
     */
    public String byteToString(int c) {
        return Integer.toBinaryString((toUnsignedInt(c)) + 256).substring(1, 9);
    }
    
    /**
     * Convert Java signed int to unsigned, due to bytes are signed in Java
     * 
     * @param value the value to change to unsigned
     * @return unsigned int
     */
    public int toUnsignedInt(int value) {
        return (value & 0xFF);
    }
}
