/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebserge
 */
public class HuffTreeTest {
    HuffTree tree;
    int[] occurrences;
    
    @Before
    public void setUp() {
        tree = new HuffTree();
        occurrences = new int[256];
    }
    
    @Test
    public void testTreeGeneration() {
        for (int i = 0; i <= 255; i++) {
            occurrences[i] = 1;
        }
        occurrences[255] = 1337;
        tree.buildHuffTree(occurrences);
        HuffNode root = tree.getRoot();
        assertEquals(1337, root.getRightNode().getOccurrence());
    }
    
    @Test
    public void testSmallTree() {
        occurrences[3] = 10;
        occurrences[5] = 11;
        tree.buildHuffTree(occurrences);
        HuffNode root = tree.getRoot();
        assertTrue(root.getLeftNode().isLeaf());
    }
    
    @Test
    public void rootIsNotLeaf() {
        occurrences[0] = 1;
        occurrences[1] = 2;
        tree.buildHuffTree(occurrences);
        HuffNode root = tree.getRoot();
        assertFalse(root.isLeaf());
    }
    
    @Test
    public void oneOccurrenceTreeTest() {
        occurrences[200] = 100;
        tree.buildHuffTree(occurrences);
        HuffNode root = tree.getRoot();
        assertTrue(root.isLeaf());
    }
}
