package packsack.util;

/**
 *
 * @author sebserge
 * @param <HuffNode> MinHeap for HuffNodes
 */
public class MinHeap<HuffNode extends Comparable<HuffNode>> {

    private final HuffNode[] heap;
    private int size;
    
    /**
     * If heap size 0, it is empty.
     */
    public MinHeap() {
        this.heap = (HuffNode[]) new Comparable[257];
        this.size = 0;
    }

    /**
     * Add an HuffNode to the next free slot of heap. Move the new node to the correct 
     * position by calling moveNewNodeToCorrectPosition() function.
     * 
     * @param node Node to be added to heap
     */
    public void add(HuffNode node) {
        size++;
        heap[size] = node;
        moveNewNodeToCorrectPosition();
    }

    /**
     * Returns the first node in heap and and removes it.
     * Make the last node first and call moveNewRoot()
     *
     * @return First object in heap array
     */
    public HuffNode poll() {
        HuffNode returnMin = heap[1];

        heap[1] = heap[size];
        heap[size] = null;
        size--;

        moveNewRoot();

        return returnMin;
    }
    
    /**
     * Called after poll() method to fix the minimum heap.
     * 
     * Compare node(i) left and right child which is bigger and check if parents occurrence is
     * bigger then the childs, if so swap them.
     */
    private void moveNewRoot() {
        int i = 1;
        if (size == 2 && heap[1].compareTo(heap[2]) > 0) {
            swap(1, 2);
        }
        while (2 * i + 1 <= size) {
            int nodeChild = 2 * i;
            if (heap[2 * i].compareTo(heap[2 * i + 1]) >= 0) {
                nodeChild++;
            }

            if (heap[i].compareTo(heap[nodeChild]) > 0) {
                swap(i, nodeChild);
                i = nodeChild;
            } else {
                break;
            }
        }
    }
    
    /**
     * Move the new node to correct position by comparing it with its parent. 
     * While the new nodes occurrence compared to its parent is smaller, swap the two nodes places.
     */
    private void moveNewNodeToCorrectPosition() {
        int i = size;
        while (i > 1 && heap[i].compareTo(heap[i / 2]) < 0) {
            swap(i, i / 2);
            i = i / 2;
        }
    }


    private void swap(int a, int b) {
        HuffNode temp = heap[b];
        heap[b] = heap[a];
        heap[a] = temp;
    }
    
    /**
     * If size = 0, the hufftree generation will end
     * @return the last object pointer of the array
     */
    public int size() {
        return size;
    }
}
