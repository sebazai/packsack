/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
        return (byte) Short.parseShort(toByte,2);
    }
    
    public byte[] fileSizeToBytes(int size) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (size >>> 24);
        bytes[1] = (byte) (size >>> 16);
        bytes[2] = (byte) (size >>> 8);
        bytes[3] = (byte) (size);
        return bytes;
    }
}
