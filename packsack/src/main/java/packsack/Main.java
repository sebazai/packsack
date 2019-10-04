package packsack;

import packsack.ui.TextUI;

/**
 * Main.java
 * @author sebserge
 */
public class Main {

    /**
     * Starts the UI
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TextUI ui = new TextUI();
        ui.startTextUI(args);
    }
}