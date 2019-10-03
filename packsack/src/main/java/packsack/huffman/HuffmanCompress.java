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
 * HuffmanCompress compress and decompress class
 * @author sebserge
 */
public class HuffmanCompress {
    private final HuffTree tree;
    private String treeAsBinary;
    private String dataToDecode;
    private final ByteStringManipulator manipulator;
    public HuffmanCompress() {
        this.tree = new HuffTree();
        this.treeAsBinary = "";
        this.dataToDecode = "";
        this.manipulator = new ByteStringManipulator();
    }
    
    /**
     * HuffmanCompress compress
     * @param filePath Path to file to compress
     * @return size of file to be compressed, for performance testing
     */
    public int compress(String filePath) {
        FileInput stream = new FileInput(filePath);
        int sizeOfFile = stream.size();
        
        int[] occurrences = new int[256];
        // Read all bytes to memory
        byte[] allFileBytes = stream.readAllBytes();
        stream.close();
        
        this.calculateOccurrencesOfCharacters(occurrences, allFileBytes);

        this.tree.buildHuffTree(occurrences);
        HuffNode rootnode = this.tree.getRoot();
        
        String[] codeTable = new String[256];
        this.createEncodingTable(codeTable, rootnode, "");
        
        String treeAsBinaryLocal = this.writeTree(rootnode, new StringBuilder());
        byte[] treeAsBytes = this.convertTreeBinaryStringToBytes(treeAsBinaryLocal);
        
        FileInput inputStream = new FileInput(filePath);
        FileOutput outputStream = new FileOutput(filePath, false);
        
        byte[] fileSize = manipulator.fileSizeToBytes(inputStream.size());
        
        outputStream.writeArray(fileSize);
        outputStream.writeArray(treeAsBytes);

        writeEncodedData(allFileBytes, outputStream, codeTable);

        inputStream.close();
        outputStream.close();
        return sizeOfFile;
    }
    
    /**
     * Calculates the occurrences of characters in the input file to be compressed
     * @param occurrences Link to the occurrences array
     * @param stream Input file stream
     */
    public void calculateOccurrencesOfCharacters(int[] occurrences, byte[] stream) {
        for (int i = 0; i < stream.length; i++) {
            occurrences[manipulator.toUnsignedInt(stream[i])]++;
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
     * @param builder
     * @return Binary of tree as String
     */
    public String writeTree(HuffNode node, StringBuilder builder) {
        if (node.isLeaf()) {
            builder.append("1");
            builder.append(manipulator.byteToString(node.getCharacter()));
            return builder.toString();
        }
        
        builder.append("0");
        
        writeTree(node.getLeftNode(), builder);
        writeTree(node.getRightNode(), builder);
        
        return builder.toString();
    }
    
    /**
     * Converts the treeAsBinary String to an array of bytes to write in the output stream.
     * Makes sure the string is divisible with 8 and with substring(0, 8) cut the string to 8-bits/byte.
     * @param treeAsBinary
     * @return array of bytes
     */
    public byte[] convertTreeBinaryStringToBytes(String treeAsBinary) {
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
     * @param fileBytes Original file data as array in memory
     * @param outputStream file output stream
     * @param encodingTable Encoding table generated from hufftree
     */
    public void writeEncodedData(byte[] fileBytes, FileOutput outputStream, String[] encodingTable) {
        String encodedString = "";
        byte[] arrayToWrite = new byte[fileBytes.length];
        int whereToWrite = 0;
        for (int i = 0; i < fileBytes.length; i++) {
            int unSigned = manipulator.toUnsignedInt(fileBytes[i]);
            if (encodingTable[unSigned] == null) {
                break;
            } else {
                encodedString += encodingTable[unSigned];
            }

            if (encodedString.length() >= 8) {
               arrayToWrite[whereToWrite] = this.manipulator.stringToByte(encodedString.substring(0, 8));
               encodedString = encodedString.substring(8);
               whereToWrite++;
            }
        }
        if (encodedString.length() % 8 != 0) {
            for (int i = encodedString.length() % 8; i <= 8; i++) {
                encodedString += "0";
            }
            arrayToWrite[whereToWrite] = this.manipulator.stringToByte(encodedString.substring(0, 8));
            whereToWrite++;
        }
        
        byte[] finalArray = new byte[whereToWrite];
        for (int i = 0; i < whereToWrite; i++) {
            finalArray[i] = arrayToWrite[i];
        }
        outputStream.writeArray(finalArray);
    }    
}
