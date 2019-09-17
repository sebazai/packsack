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

    public Huffman() {
        this.tree = new HuffTree();
    }
    
    public void compress(String filePath) {
        FileInput stream = new FileInput(filePath);
        int[] occurrences = new int[256];

        int nextInt;
        while ((nextInt = stream.nextInt()) >= 0) {
          //Turn int to unsigned int
          occurrences[nextInt & 0xFF]++;
        }
//        for(int i = 0; i < occurrences.length; i++) {
//            System.out.println(i + " " + occurrences[i]);
//        }
        this.tree.buildHuffTree(occurrences);
        HuffNode rootnode = this.tree.getRoot();
        stream.close();
        
        String[] codeTable = new String[256];
        createEncodingTable(codeTable, rootnode, "");
//        for(int i = 0; i < codeTable.length; i++) {
//            if(codeTable[i] != null ) System.out.println(i + " " + codeTable[i]);
//        }
        
    }
    
    public void createEncodingTable(String[] table, HuffNode node, String code) {
        if (node.isLeaf()) {
            table[node.getCharacter() & 0xFF] = code;
            return;
        }
        createEncodingTable(table, node.getLeftNode(), code + "0");
        createEncodingTable(table, node.getRightNode(), code + "1");

    }
}
