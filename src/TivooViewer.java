import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.util.TimerTask;
import java.util.Timer;


public class TivooViewer extends JFrame {

    private static String myTitle = "Tivoo";
    private static JFileChooser myFileChooser = new JFileChooser();

    private TivooSystem myModel;
    private JTextField myMessage;



    public TivooViewer(TivooSystem model) {

        myModel = model;
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(makeMessage(), BorderLayout.SOUTH);
        makeMenuBar();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(myTitle);
        pack();
        setVisible(true);
    }

    private JPanel makeMessage() {
        JPanel p = new JPanel(new BorderLayout());
        myMessage = new JTextField(30);
        p.setBorder(BorderFactory.createTitledBorder("Message"));
        p.add(myMessage, BorderLayout.CENTER);
        return p;
    }

    private void makeMenuBar() {
        JMenuBar bar = new JMenuBar();
        bar.add(makeFileMenu());
        bar.add(makeFilterMenu());
        bar.add(makeOutputMenu());
        setJMenuBar(bar);
    }

    private JMenu makeFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem itemOpen = new JMenuItem("Open File");
        itemOpen.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(itemOpen);

        fileMenu.addSeparator();

        JMenuItem itemQuit = new JMenuItem("Quit");
        itemQuit.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(itemQuit);

        return fileMenu;
    }

    private JMenu makeFilterMenu() {
        JMenu filterMenu = new JMenu("Filter");

        JMenuItem itemFilterByKeyWord = new JMenuItem("Filter by Keyword:");
        itemFilterByKeyWord.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                filterByKeyWord();
            }
        });
        filterMenu.add(itemFilterByKeyWord);

        return filterMenu;
    }

    private JMenu makeOutputMenu() {
        JMenu outputMenu = new JMenu("Output");

        JMenuItem itemOutputSummary = new JMenuItem("Output Summary");
        itemOutputSummary.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                outputSummary();
            }
        });
        outputMenu.add(itemOutputSummary);

        return outputMenu;
    }

    private void openFile() {
        int returnVal = myFileChooser.showOpenDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }
        showMessage("loading..");
        File file = myFileChooser.getSelectedFile();
        myModel.loadFile(file);
        showMessage("file loaded");
    }

    private void filterByKeyWord() {
        String keyword = JOptionPane.showInputDialog("Please enter keyword:");
        showMessage("parsing..");
        myModel.filterByKeyword(keyword);
        showMessage("file parsed");
    }

    private void outputSummary() {
        int returnval = myFileChooser.showSaveDialog(null);
        if (returnval != JFileChooser.APPROVE_OPTION) {
            return;
        }
        showMessage("outputing..");
        File file = myFileChooser.getSelectedFile();
        myModel.outputSummary(file.getPath());
        showMessage("file outputed");
    }

    public void showMessage(String s) {
        myMessage.setText(s);
    }

}
