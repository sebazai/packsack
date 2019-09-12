/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author sebserge
 */
public class HuffNode implements Comparable<HuffNode> {
    private int occurrence;
    private HuffNode leftChild, rightChild;
    private char character;
    private Boolean isLeaf;
    
    public HuffNode(char character, int charOccurrence, HuffNode left, HuffNode right) {
        this.character = character;
        this.occurrence = charOccurrence;
        if (left == null && right == null) {
            isLeaf = true;
        } else {
            isLeaf = false;
            this.leftChild = left;
            this.rightChild = right;
        }
    }
    
    public HuffNode(int charOccurrence, HuffNode left, HuffNode right) {
        this.occurrence = charOccurrence;
        this.isLeaf = false;
        this.leftChild = left;
        this.rightChild = right;
    }
    
    public boolean getLeaf() {
        return isLeaf;
    }
    
    public char getCharacter() {
        return this.character;
    }
    
    public int getOccurrence() {
        return this.occurrence;
    }
    
    public HuffNode getLeftNode() {
        return this.leftChild;
    }
    
    public HuffNode getRightNode() {
        return this.rightChild;
    }
    
    public int compareTo(HuffNode t) {
        return this.occurrence - t.occurrence;
    }
}
