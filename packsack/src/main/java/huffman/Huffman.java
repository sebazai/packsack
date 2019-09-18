/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;
import io.FileInput;
import io.FileOutput;
import java.util.ArrayDeque;
import java.util.Deque;
import util.ByteStringManipulator;
import util.HuffNode;
import util.HuffTree;
/**
 *
 * @author sebserge
 */
public class Huffman {
    private final HuffTree tree;
    private String treeAsBinary;
    private String wholeBinaryString;
    private String dataToDecode;
    private ByteStringManipulator manipulator;
    public Huffman() {
        this.tree = new HuffTree();
        this.treeAsBinary = "";
        this.dataToDecode = "";
        this.wholeBinaryString = "";
        this.manipulator = new ByteStringManipulator();
    }
    
    /**
     * Huffman compress
     * @param filePath Uncompressed file path
     */
    public void compress(String filePath) {
        FileInput stream = new FileInput(filePath);
        int[] occurrences = new int[256];

        int nextInt;
        while ((nextInt = stream.nextInt()) >= 0) {
          occurrences[manipulator.toUnsignedInt(nextInt)]++;
        }

        this.tree.buildHuffTree(occurrences);
        HuffNode rootnode = this.tree.getRoot();
        stream.close();
        
        String[] codeTable = new String[256];
        this.createEncodingTable(codeTable, rootnode, "");

        this.writeTree(rootnode);
        for (int i = 0; i < treeAsBinary.length() % 8; i++) {
            treeAsBinary += "0";
        }
        System.out.println(treeAsBinary);

        System.out.println(treeAsBinary.length());
        byte[] treeAsBytes = this.convertTreeBinaryStringToBytes();
        
        FileInput inputStream = new FileInput(filePath);
        FileOutput outputStream = new FileOutput(filePath, false);
        
        byte[] fileSize = manipulator.fileSizeToBytes(inputStream.size());
        
        outputStream.write(fileSize);
        outputStream.write(treeAsBytes);
        writeEncodedData(inputStream, outputStream, codeTable);
        
        inputStream.close();
        outputStream.close();
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
     * @param node root node
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
     * Converts the treeAsBinary String to an array of bytes for writing to the output
     * @return array of bytes
     */
    public byte[] convertTreeBinaryStringToBytes() {
//        treeAsBinary = manipulator.padBinaryStringWithZerosAtEnd(treeAsBinary);
//        for (int i = 0; i < treeAsBinary.length() % 8; i++) {
//            treeAsBinary += "0";
//        }
        byte[] treeAsBytes = new byte[treeAsBinary.length() / 8];
        
        for (int i = 0; i < treeAsBinary.length(); i++) {
            treeAsBytes[i] = manipulator.stringToByte(treeAsBinary.substring(0, 8));
            treeAsBinary = treeAsBinary.substring(8);
        }
        return treeAsBytes;
    }
    
    /**
     * Reads file and writes the encoded data.
     * @param inputStream file input stream
     * @param outputStream file output stream
     * @param encodingTable Encoding table generated from hufftree
     */
    public void writeEncodedData(FileInput inputStream, FileOutput outputStream, String[] encodingTable) {
        String encodedString = "";
        for (int i = inputStream.nextInt(); i != -500; i = inputStream.nextInt()) {
            int unSigned = manipulator.toUnsignedInt(i);
            if(encodingTable[unSigned] == null) {
                break;
            } else {
                encodedString += encodingTable[unSigned];
            }
            if(encodedString.length() >= 8) {
                encodedString = writeToOutputFile(encodedString, outputStream);
            }
        }
        
        if (!encodedString.isEmpty()) {
            for(int i = encodedString.length(); i < 8; i++) {
                encodedString += "0";
            }
            encodedString = writeToOutputFile(encodedString, outputStream);
        }
        
        
    }
    
    /**
     * Writes in 8 bit sequence from the encoded binary string.
     * @param toWrite Encoded binary string
     * @param outputStream File output
     * @return The bits that left in the string
     */
    public String writeToOutputFile(String toWrite, FileOutput outputStream) {
        while(toWrite.length() >= 8) {
            outputStream.write(manipulator.stringToByte(toWrite.substring(0,8)));
            toWrite = toWrite.substring(8);
        }
        return toWrite;
    }
    
    /**
     * Decompress a Huffman compressed file by reading original file size, building the tree and finally writing out the data using this tree.
     * @param inputFilePath File to be decompressed
     * @param outputFilePath File to save
     */
    public void decompress(String inputFilePath, String outputFilePath) {
        FileInput inputstream = new FileInput(inputFilePath);
        FileOutput outputstream = new FileOutput(outputFilePath, true);
        dataToDecode = "";
        int fileSize = inputstream.readFileSize();
        treeAsBinary = manipulator.byteToString(inputstream.nextInt());
        HuffNode rootnode = this.decodeTree(inputstream);
        System.out.println(this.wholeBinaryString);
        System.out.println(this.wholeBinaryString.length());
        for(int i = 0; i < fileSize; i++) {
            byte dataToWrite = decodeData(rootnode, inputstream);
            outputstream.write(dataToWrite);
        }
    }
    
    
    /**
     * Decode the compressed data recursively
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
        } else {
            dataToDecode = dataToDecode.substring(1);
            return decodeData(node.getRightNode(), stream);
        }
    }
    
    /**
     * Build the tree from compressed file recursively
     * @param stream
     * @return root node
     */
    public HuffNode decodeTree(FileInput stream) {
        if (treeAsBinary.isEmpty()) {
            treeAsBinary += manipulator.byteToString(stream.nextInt());
        }
        
        if(treeAsBinary.charAt(0) == '1') {
            if (treeAsBinary.length() < 9) {
                treeAsBinary += manipulator.byteToString(stream.nextInt());
                
            }
            String character = treeAsBinary.substring(1, 9);
            this.wholeBinaryString += treeAsBinary.substring(0, 9);
//            System.out.println(character + " char");
            treeAsBinary = treeAsBinary.substring(9);
//            System.out.println((char) manipulator.stringToByte(character) + " manipulated char");
            return new HuffNode((char) manipulator.stringToByte(character), 0, null, null);
        } else {
            this.wholeBinaryString += treeAsBinary.substring(0, 1);
            treeAsBinary = treeAsBinary.substring(1);
            return new HuffNode(0, decodeTree(stream), decodeTree(stream));
        }
    }
    
}
