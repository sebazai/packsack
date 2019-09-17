/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;
import io.FileInput;
import io.FileOutput;
import util.HuffNode;
import util.HuffTree;
/**
 *
 * @author sebserge
 */
public class Huffman {
    private final HuffTree tree;
    private String treeAsBinary;
    public Huffman() {
        this.tree = new HuffTree();
        this.treeAsBinary = "";
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
          occurrences[toUnsignedInt(nextInt)]++;
        }
//        for(int i = 0; i < occurrences.length; i++) {
//            System.out.println(i + " " + occurrences[i]);
//        }
        this.tree.buildHuffTree(occurrences);
        HuffNode rootnode = this.tree.getRoot();
        stream.close();
        
        String[] codeTable = new String[256];
        createEncodingTable(codeTable, rootnode, "");
////        System.out.println(Integer.toBinaryString(((char) 'a' & 0xFF) + 256) );
//        for(int i = 0; i < codeTable.length; i++) {
//            if(codeTable[i] != null ) System.out.println(i + " " + codeTable[i]);
//        }
//        System.out.println(byteToString('A'));

        this.writeTree(rootnode);
        treeAsBinary = this.padBinaryStringWithZerosAtEnd(treeAsBinary);
        byte[] treeAsBytes = new byte[treeAsBinary.length() / 8];
        for (int i = 0; i < treeAsBinary.length(); i++) {
            treeAsBytes[i] = this.stringToByte(treeAsBinary.substring(0, 8));
            System.out.println(treeAsBytes[i]);
            treeAsBinary = treeAsBinary.substring(8);
        }
        FileInput inputStream = new FileInput(filePath);
        FileOutput outputStream = new FileOutput(filePath);
        byte[] fileSize = this.fileSizeToBytes((int) inputStream.size());
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
            table[node.getCharacter() & 0xFF] = code;
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
            treeAsBinary = treeAsBinary.concat(byteToString(node.getCharacter()));
            return;
        }
        treeAsBinary = treeAsBinary.concat("0");
        writeTree(node.getLeftNode());
        writeTree(node.getRightNode());
    }
    
    public void writeEncodedData(FileInput inputStream, FileOutput outputStream, String[] encodingTable) {
        String encodedString = "";
        for (int i = inputStream.nextInt(); i != -500; i = inputStream.nextInt()) {
            int unSigned = this.toUnsignedInt(i);
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
    
    public String writeToOutputFile(String toWrite, FileOutput outputStream) {
        while(toWrite.length() >= 8) {
            outputStream.write(this.stringToByte(toWrite.substring(0,8)));
            toWrite = toWrite.substring(8);
        }
        return toWrite;
    }
    
    /**
     * Pad String with zeros so it is divisible with 8.
     * 
     * @return Padded string with zeros at end
     */
    public String padBinaryStringWithZerosAtEnd(String stringToPad) {
         for (int i = 0; i < stringToPad.length() % 8; i++) {
            stringToPad += "0";
        }
        return stringToPad;
    }
    
    /**
     * Convert a byte to a string of length 8. 
     * Add 256 to pad the start with zeros
     * 
     * @param c int/char to convert to a string
     * @return substring 1-9, 8 bit string for the int/char
     */
    public String byteToString(int c) {
        return Integer.toBinaryString((toUnsignedInt(c)) + 256).substring(1, 9);
    }
    
    /**
     * Convert Java signed int to unsigned, due to bytes are signed in Java
     * 
     * @param value the value to change to unsigned
     * @return unsigned int
     */
    public int toUnsignedInt(int value) {
        return (value & 0xFF);
    }
    
    /**
     * Convert a String with length of 8 to a byte.
     * @param toByte the string to convert
     * @return byte of the binary string
     */
    public byte stringToByte(String toByte) {
        return (byte) Short.parseShort(toByte,2);
    }
    
    public byte[] fileSizeToBytes(int size) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte)(size >>> (i * 8));
        }
        return bytes;
    }
}
