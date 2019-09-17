/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
public class HuffNodeTest {
    HuffNode nodeA;
    
    @Before
    public void setUp() {
        nodeA = new HuffNode('a', 10, null, null);
    }
    
    @Test
    public void compareReturnsPositiveValueWhenBigger() {
        HuffNode nodeB = new HuffNode('b', 5, null, null);
        assertTrue(nodeA.compareTo(nodeB) > 0);
    }
    
    @Test
    public void compareReturnsNegativeValueWhenSmaller() {
        HuffNode nodeB = new HuffNode('b', 15, null, null);
        assertTrue(nodeA.compareTo(nodeB) < 0);
    }
    
    @Test
    public void compareReturnsZeroIfOccurrencesIsSame() {
        HuffNode nodeB = new HuffNode('b', 10, null, null);
        assertEquals(0, nodeA.compareTo(nodeB));
    }
    
    @Test
    public void isLeafReturnsFalseIfNodeHasChildren() {
        HuffNode nodeB = new HuffNode('b', 12, null, null);
        HuffNode nodeC = new HuffNode('c', 16, nodeB, null);
        assertFalse(nodeC.isLeaf());
    }
    
    @Test
    public void isLeafReturnsFalseIfNodeHasChildren2() {
        HuffNode nodeB = new HuffNode('b', 12, null, null);
        HuffNode nodeC = new HuffNode(16, nodeB, nodeA);
        assertFalse(nodeC.isLeaf());
    }
    
    @Test
    public void getCharacterReturnsCorrectChar() {
        assertEquals('a', nodeA.getCharacter());
    }
    
    @Test
    public void getOccurrencesReturnsCorrectAmount() {
        assertEquals(10, nodeA.getOccurrence());
    }
}
