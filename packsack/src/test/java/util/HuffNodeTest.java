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
}
