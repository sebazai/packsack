/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packsack.huffman;
import packsack.io.FileInput;
import packsack.io.FileOutput;
import packsack.util.ByteStringManipulator;
import packsack.util.HuffNode;
import packsack.util.HuffTree;

/**
 * Huffman compress and decompress class
 * @author sebserge
 */
public class Huffman {
    private final HuffTree tree;
    private String treeAsBinary;
    private String dataToDecode;
    private final ByteStringManipulator manipulator;
    public Huffman() {
        this.tree = new HuffTree();
        this.treeAsBinary = "";
        this.dataToDecode = "";
        this.manipulator = new ByteStringManipulator();
    }
    
    /**
     * Huffman compress
     * @param filePath Path to file to compress
     * @return size of file to be compressed, for performance testing
     */
    public int compress(String filePath) {
        FileInput stream = new FileInput(filePath);
        int sizeOfFile = stream.size();
        int[] occurrences = new int[256];

        this.calculateOccurrencesOfCharacters(occurrences, stream);

        this.tree.buildHuffTree(occurrences);
        HuffNode rootnode = this.tree.getRoot();
        stream.close();
        
        String[] codeTable = new String[256];
        this.createEncodingTable(codeTable, rootnode, "");

        this.writeTree(rootnode);
        byte[] treeAsBytes = this.convertTreeBinaryStringToBytes();
        
        FileInput inputStream = new FileInput(filePath);
        FileOutput outputStream = new FileOutput(filePath, false);
        
        byte[] fileSize = manipulator.fileSizeToBytes(inputStream.size());
        
        outputStream.writeArray(fileSize);
        outputStream.writeArray(treeAsBytes);
        writeEncodedData(inputStream, outputStream, codeTable);
        
        inputStream.close();
        outputStream.close();
        return sizeOfFile;
    }
    
    /**
     * Calculates the occurrences of characters in the input file to be compressed
     * @param occurrences Link to the occurrences array
     * @param stream Input file stream
     */
    public void calculateOccurrencesOfCharacters(int[] occurrences, FileInput stream) {
        int nextInt;
        while ((nextInt = stream.nextInt()) >= 0) {
            occurrences[manipulator.toUnsignedInt(nextInt)]++;
        }
    }
    
    /**
     * Create encoding table of tree recursively
     * @param table Encoding table for characters
     * @param node Node to traverse
     * @param code Binary string code for nodes created recursively
     */
    public void createEncodingTable(String[] table, HuffNode node, String code) {
        if (node.isLeaf()) {
            table[manipulator.toUnsignedInt(node.getCharacter())] = code;
            return;
        }
        createEncodingTable(table, node.getLeftNode(), code + "0");
        createEncodingTable(table, node.getRightNode(), code + "1");
    }
    
    /**
     * Write HuffTree recursively to binary string.
     * @param node start at root node
     */
    public void writeTree(HuffNode node) {
        if (node.isLeaf()) {
            treeAsBinary = treeAsBinary.concat("1");
            treeAsBinary = treeAsBinary.concat(manipulator.byteToString(node.getCharacter()));
            return;
        }
        treeAsBinary = treeAsBinary.concat("0");
        writeTree(node.getLeftNode());
        writeTree(node.getRightNode());
    }
    
    /**
     * Converts the treeAsBinary String to an array of bytes to write in the output stream.
     * Makes sure the string is divisible with 8 and with substring(0, 8) cut the string to 8-bits/byte.
     * @return array of bytes
     */
    public byte[] convertTreeBinaryStringToBytes() {
        treeAsBinary = manipulator.padBinaryStringWithZerosAtEnd(treeAsBinary);

        byte[] treeAsBytes = new byte[treeAsBinary.length() / 8];
        
        for (int i = 0; i < treeAsBytes.length; i++) {
            treeAsBytes[i] = manipulator.stringToByte(treeAsBinary.substring(0, 8));
            treeAsBinary = treeAsBinary.substring(8);
        }
        return treeAsBytes;
    }
    
    /**
     * Reads the original file to be compressed and writes the encoded data using the encoding table.
     * 
     * @param inputStream file input stream
     * @param outputStream file output stream
     * @param encodingTable Encoding table generated from hufftree
     */
    public void writeEncodedData(FileInput inputStream, FileOutput outputStream, String[] encodingTable) {
        String encodedString = "";
        for (int i = inputStream.nextInt(); i != -1; i = inputStream.nextInt()) {
            int unSigned = manipulator.toUnsignedInt(i);
            if (encodingTable[unSigned] == null) {
                break;
            } else {
                encodedString += encodingTable[unSigned];
            }
            if (encodedString.length() >= 8) {
                encodedString = outputStream.writeToOutputFile(encodedString, this.manipulator);
            }
        }
        
        if (!encodedString.isEmpty()) {
            for (int i = encodedString.length(); i < 8; i++) {
                encodedString += "0";
            }
            outputStream.writeToOutputFile(encodedString, this.manipulator);
        }
    }
    
    /**
     * Decompress a Huffman compressed file by reading original file size from first 4 bytes, building the tree from the header and finally writing out the data to original for using this generated tree.
     * 
     * @param inputFilePath File to be decompressed
     * @param outputFilePath File to save
     */
    public void decompress(String inputFilePath, String outputFilePath) {
        FileInput inputstream = new FileInput(inputFilePath);
        FileOutput outputstream = new FileOutput(outputFilePath, true);
        
        // Make sure the Strings are empty.
        dataToDecode = "";
        treeAsBinary = "";
        
        int fileSize = inputstream.readFileSize();
        
        treeAsBinary = manipulator.byteToString(inputstream.nextInt());
        HuffNode rootnode = this.decodeTree(inputstream);
        
        for (int i = 0; i < fileSize; i++) {
            byte dataToWrite = decodeData(rootnode, inputstream);
            outputstream.write(dataToWrite);
        }
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
    
}
