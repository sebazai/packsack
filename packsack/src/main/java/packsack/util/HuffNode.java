package packsack.util;

/**
 * Node for the Huffman Tree.
 * @author sebserge
 */
public class HuffNode implements Comparable<HuffNode> {
    private final int occurrence;
    private HuffNode leftChild, rightChild;
    private final Character character;
    private final Boolean isLeaf;
    
    /**
     * Constructor for Huffman tree node leaves.
     * @param character The character
     * @param charOccurrence The occurrence of the character
     * @param left The left child
     * @param right The right child
     */
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
    
    /**
     * Constructor for Huffman tree non-leaf nodes.
     * @param weight Combined weight of left and right child
     * @param left Nodes left child
     * @param right Nodes right child
     */
    public HuffNode(int weight, HuffNode left, HuffNode right) {
        this.occurrence = weight;
        this.isLeaf = false;
        this.leftChild = left;
        this.rightChild = right;
        this.character = null;
    }
    
    /**
     * Check if node is a leaf
     * @return true if no child nodes
     */
    public boolean isLeaf() {
        return isLeaf;
    }
    
    public Character getCharacter() {
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
    
    @Override
    public int compareTo(HuffNode t) {
        return this.occurrence - t.occurrence;
    }
}
