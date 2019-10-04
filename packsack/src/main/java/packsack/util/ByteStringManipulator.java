package packsack.util;

/**
 * Class used to convert strings, bytes and integers.
 * @author sebserge
 */
public class ByteStringManipulator {
    /**
     * Pad String with zeros so it is divisible with 8.
     * 
     * @param stringToPad Binary string to pad with zeros
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
     * Convert Java signed integer to unsigned, due to bytes are signed in Java
     * 
     * @param value the value to change to unsigned
     * @return unsigned integer
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
        return (byte) Short.parseShort(toByte, 2);
    }
    
    /**
     * Convert the file size from integer to an array of 4 bytes using bit shifting.
     * @param size the length of file
     * @return array of 4 bytes
     */
    public byte[] fileSizeToBytes(int size) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (size >>> 24);
        bytes[1] = (byte) (size >>> 16);
        bytes[2] = (byte) (size >>> 8);
        bytes[3] = (byte) (size);
        return bytes;
    }
    
    /**
     * Converts the treeAsBinary String to an array of bytes to write in the output stream.
     * Makes sure the string is divisible with 8 and with substring(0, 8) cut the string to 8-bits/byte.
     * @param treeAsBinary
     * @return array of bytes
     */
    public byte[] convertTreeBinaryStringToBytes(String treeAsBinary) {
        treeAsBinary = this.padBinaryStringWithZerosAtEnd(treeAsBinary);

        byte[] treeAsBytes = new byte[treeAsBinary.length() / 8];
        
        for (int i = 0; i < treeAsBytes.length; i++) {
            treeAsBytes[i] = this.stringToByte(treeAsBinary.substring(0, 8));
            treeAsBinary = treeAsBinary.substring(8);
        }
        return treeAsBytes;
    }
}
