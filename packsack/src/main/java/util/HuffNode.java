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
    private Byte nodeByte;
    private Boolean isLeaf;
    
    public HuffNode(Byte byteNode, int byteOccurrence, HuffNode left, HuffNode right) {
        this.nodeByte = byteNode;
        this.occurrence = byteOccurrence;
        if (left == null && right == null) {
            isLeaf = true;
        } else {
            isLeaf = false;
            this.leftChild = left;
            this.rightChild = right;
        }
    }
    
    public int compareTo(HuffNode t) {
        return this.occurrence - t.occurrence;
    }
}
