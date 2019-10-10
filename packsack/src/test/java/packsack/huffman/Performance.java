package packsack.huffman;

import org.junit.Test;
import packsack.ui.TextUI;

/**
 * Setup for each test and run each file separately.
 * 
 * @author sebserge
 */
public class Performance {
    
    public void run(String filePath, TextUI ui, TextUI ui2) {
        String testFile = getClass().getClassLoader().getResource(filePath).getPath();
        String[] args = new String[3];
        args[0] = "-co";
        args[1] = testFile;
        System.out.println("-------------------------");
        System.out.println(filePath);
        ui.startTextUI(args);
        
        // Decompress
        args[0] = "-de";
        args[1] = testFile.concat(".sebbe");
        args[2] = testFile;
        ui2.startTextUI(args);
    }
    
    
    @Test
    public void alice29() {
        run("alice29.txt", new TextUI(), new TextUI());
        
    }
    
    @Test
    public void asyoulik() {
        run("asyoulik.txt", new TextUI(), new TextUI());
    }
    
    @Test
    public void bible() {
        run("bible.txt", new TextUI(), new TextUI());
    }
    
    @Test
    public void cphtml() {
        run("cp.html", new TextUI(), new TextUI());
    }
    
    @Test
    public void ecoli() {
        run("E.coli", new TextUI(), new TextUI());
    }
    
    @Test
    public void fields() {
        run("fields.c", new TextUI(), new TextUI());
    }
    
    @Test
    public void grammar() {
        run("grammar.lsp", new TextUI(), new TextUI());
    }
    
    @Test
    public void kennedy() {
        run("kennedy.xls", new TextUI(), new TextUI());
    }
    
    @Test
    public void lcet10() {
        run("lcet10.txt", new TextUI(), new TextUI());
    }
    
    @Test
    public void plrabn12() {
        run("plrabn12.txt", new TextUI(), new TextUI());
    }
    
    @Test
    public void ptt5() {
        run("ptt5", new TextUI(), new TextUI());
    }
    
    @Test
    public void sum() {
        run("sum", new TextUI(), new TextUI());
    }
    
    @Test
    public void worl192() {
        run("world192.txt", new TextUI(), new TextUI());
    }
    
    @Test
    public void xargs() {
        run("xargs.1", new TextUI(), new TextUI());
    }
}
