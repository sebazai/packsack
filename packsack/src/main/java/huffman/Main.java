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
     * @throws java.io.IOException
     */
    public static void main(String[] args) {
        Huffman huff = new Huffman();
        huff.compress("/home/sebserge/Downloads/alice29.txt");
        
        huff.decompress("/home/sebserge/Downloads/alice29.txt.sebbe", "/home/sebserge/Downloads/testausta.txt");
    }

}