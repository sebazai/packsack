/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packsack.util;

import java.util.PriorityQueue;

/**
 *
 * @author sebserge
 */
public class HuffTree {
    private HuffNode root;
    
    /**
     * Function to build the Huffman tree
     * @param occurrence array containing the amount of occurrence of bytes.
     */
    public void buildHuffTree(int[] occurrence) {
        PriorityQueue<HuffNode> huffTree = new PriorityQueue<>();
        for (char i = 0; i < 256; i++) {
            // Convert unsigned int to signed'
            if (occurrence[i] != 0) { 
                huffTree.add(new HuffNode((char) (byte) i, occurrence[i], null, null));
            }
        }
        
        while (huffTree.size() > 1) {
            HuffNode leftNode = huffTree.poll();
            HuffNode rightNode = huffTree.poll();
            HuffNode parentNode = new HuffNode(leftNode.getOccurrence() + rightNode.getOccurrence(), leftNode, rightNode);
            huffTree.add(parentNode);
        }
        this.root = huffTree.poll();
    }
    
    public HuffNode getRoot() {
        return this.root;
    }
    
}
