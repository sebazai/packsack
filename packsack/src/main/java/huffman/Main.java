package huffman;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sebserge
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Huffman huff = new Huffman();
        huff.compress("/home/sebserge/Downloads/readfilesizetest");
        
//        huff.decompress("/home/sebserge/Downloads/readfiletest.sebbe", "/home/sebserge/Downloads/testausta.txt");
    }

}