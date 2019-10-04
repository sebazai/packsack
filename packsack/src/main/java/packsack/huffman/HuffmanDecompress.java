/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packsack.huffman;

import packsack.io.FileInput;
import packsack.io.FileOutput;
import packsack.util.HuffNode;
import packsack.util.ByteStringManipulator;

/**
 *
 * @author sebserge
 */
public class HuffmanDecompress {
    
    private String treeAsBinary;
    private String dataToDecode;
    private final ByteStringManipulator manipulator;
    public HuffNode rootnode;
        
    public HuffmanDecompress() {
        treeAsBinary = "";
        dataToDecode = "";
        manipulator = new ByteStringManipulator();
    }
        
    public void decompress(String inputFilePath, String outputFilePath) {
        FileInput inputstream = new FileInput(inputFilePath);
        FileOutput outputstream = new FileOutput(outputFilePath, true);
        
        // Make sure the Strings are empty.
        dataToDecode = "";
        treeAsBinary = "";
    
        int fileSize = inputstream.readFileSize();

        treeAsBinary = manipulator.byteToString(inputstream.nextInt());
        rootnode = this.decodeTree(inputstream);
        byte[] dataToWrite = new byte[fileSize];
        
        for (int i = 0; i < fileSize; i++) {
            dataToWrite[i] = decodeData(rootnode, inputstream);
        }
        
        outputstream.writeArray(dataToWrite);
        outputstream.close();
        inputstream.close();
    }
    
    
    /**
     * Decode the compressed data recursively
     * Using the generated tree from the decompressed file
     * @param node
     * @param stream
     * @return 
     */
    public byte decodeData(HuffNode node, FileInput stream) {
        if (node.getCharacter() != null) {
            return (byte) (char) node.getCharacter();
        }
        if (dataToDecode.isEmpty()) {
            dataToDecode += manipulator.byteToString(stream.nextInt());
        }
        
        if (dataToDecode.charAt(0) == '0') {
            dataToDecode = dataToDecode.substring(1);
            return decodeData(node.getLeftNode(), stream);
        }
        
        dataToDecode = dataToDecode.substring(1);
        return decodeData(node.getRightNode(), stream);
        
    }
    
    /**
     * Build the hufftree from compressed file header recursively
     * Reads the bytes from the compressed file to a string.
     * @param stream
     * @return root node
     */
    public HuffNode decodeTree(FileInput stream) {
        if (treeAsBinary.isEmpty()) {
            treeAsBinary += manipulator.byteToString(stream.nextInt());
        }
        
        if (treeAsBinary.charAt(0) == '1') {
            if (treeAsBinary.length() < 9) {
                treeAsBinary += manipulator.byteToString(stream.nextInt());
            }
            String character = treeAsBinary.substring(1, 9);
            treeAsBinary = treeAsBinary.substring(9);
            return new HuffNode((char) manipulator.stringToByte(character), 0, null, null);
        } 
        
        treeAsBinary = treeAsBinary.substring(1);
        return new HuffNode(0, decodeTree(stream), decodeTree(stream));   
    }
    
    public HuffNode getRootNode() {
        return this.rootnode;
    }
}
