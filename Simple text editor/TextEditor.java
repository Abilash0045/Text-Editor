import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    JMenuBar jMenuBar;
    TextEditor(){
        // Creating the frame
        frame = new JFrame("Text Editor");
        frame.setSize(800,800);
        // Crating text area
        textArea = new JTextArea();
        frame.add(textArea);
        // Creating menu bar
        jMenuBar = new JMenuBar();
        // Creating menus for the menu bar
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        // Creating the menu items for Files
        JMenuItem newFile = new JMenuItem("New file");
        JMenuItem openFile = new JMenuItem("Open file");
        JMenuItem saveFile = new JMenuItem("Save file");
        JMenuItem printFile = new JMenuItem("Print file");
        // Creating actionlisteners
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        // Adding all the items to the File drop menu items
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        // Creating the menu items for Edit
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem close = new JMenuItem("Close");
        // Creating actionlisteners for edit drop menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);
        // Adding all the items to the Edit drop menu items
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);
        // Adding the menu to the frame
        jMenuBar.add(file);
        jMenuBar.add(edit);
        // Setting the menu bar
        frame.setJMenuBar(jMenuBar);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // To get the input
        String call = e.getActionCommand();
        // Performing all the functionalities
        if (call == "New file"){
            textArea.setText("");
        }else if (call == "Cut"){
            textArea.cut();
        }else if (call == "Copy"){
            textArea.copy();
        }else if (call == "Paste"){
            textArea.paste();
        }else if (call == "Close"){
            frame.setVisible(false);
        }else if (call == "Save file"){
            JFileChooser jFileChooser = new JFileChooser("c:");
            int ans = jFileChooser.showOpenDialog(null);
            if (ans == jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if (call == "Open file"){
            JFileChooser jFileChooser = new JFileChooser();
            int ans = jFileChooser.showOpenDialog(null);
            if (ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    String s1 = "",s2 = "";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1 = bufferedReader.readLine()) != null){
                        s2 += s1 + "\n";
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if (call == "Print file"){
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
