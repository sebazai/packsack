/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebserge
 */
public class ByteStringManipulatorTest {
    ByteStringManipulator manipulator;
    
    @Before
    public void setUp() {
        manipulator = new ByteStringManipulator();
    }
    
    @Test
    public void padStringWithZeros() {
        String toPad = "0101";
        assertEquals("01010000", manipulator.padBinaryStringWithZerosAtEnd(toPad));
    }
    
    @Test
    public void returnCorrectStringFromByte() {
        byte c = (byte) (char) 'c';
        assertEquals("01100011", manipulator.byteToString(c));
    }
    
    @Test
    public void testCharToUnsignedInt() {
        byte i = (byte) (char) 'a';
        assertEquals(97, manipulator.toUnsignedInt(i));
    }
    
    @Test
    public void convertBinaryStringToByte() {
        byte a = manipulator.stringToByte("10101010");
        assertEquals((byte) 0xAA, a);
    }
    
    @Test
    public void fileSizeToBytesIsCorrect() {
        byte[] bytes = manipulator.fileSizeToBytes(14);
        byte[] shouldbe = {0,0,0,14};
        assertEquals(shouldbe[2], bytes[2]);
        assertEquals(shouldbe[3], bytes[3]);
    }
}
