/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packsack.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author sebserge
 */
public class MinHeapTest {
    
    @Test
    public void heapReturnsMinimum() {
        MinHeap heap = new MinHeap();
        boolean works = true;
        for (int i = 19; i >= 0; i--) {
            heap.add(new HuffNode('a', i, null, null));
        }
        for (int i = 0; i < 20; i++) {
            if (((HuffNode) heap.poll()).getOccurrence() != i) {
                works = false;
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void heapReturnsMinimum2() {
        MinHeap heap = new MinHeap();
        heap.add(new HuffNode('a', 4, null, null));
        heap.add(new HuffNode('a', 5, null, null));
        heap.add(new HuffNode('b', 4, null, null));
        assertEquals(((HuffNode) heap.poll()).getOccurrence(), 4);
        assertEquals(((HuffNode) heap.poll()).getOccurrence(), 4);
        assertEquals(((HuffNode) heap.poll()).getOccurrence(), 5);
     }
    
    @Test
    public void heapSizeCorrect() {
        MinHeap heap = new MinHeap();
        for (int i = 0; i < 100; i++) {
            heap.add(new HuffNode('a', i, null, null));
        }
        assertEquals(heap.size(), 100);
    }
}
