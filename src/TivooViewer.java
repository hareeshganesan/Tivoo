import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import exception.TivooInvalidFeed;


@SuppressWarnings("serial")
public class TivooViewer extends JPanel
{
    public static final Dimension SIZE = new Dimension(800, 600);
    private TivooSystem myModel;
    private JEditorPane myPage;
    private JButton myInput;
    private JButton myOutput;
    private JComboBox myFilters;
    private JTextField myTf;
    private String myText;


    public TivooViewer (TivooSystem model)
    {
        myModel = model;
        setLayout(new BorderLayout());
        add(makePageDisplay(), BorderLayout.CENTER);
        add(makeInputPanel(), BorderLayout.NORTH);
        myTf = new JTextField();
        myTf.setText("Do not use until you pick a filter. Also please load a file first then add filters and then finally pick view.");
        add(myTf, BorderLayout.SOUTH);

    }


    //TODO add a file chooser

    private Component makeInputPanel ()
    {

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(makeNavigationPanel(), BorderLayout.NORTH);
        return panel;

    }


    private Component makeNavigationPanel ()
    {
        JPanel panel = new JPanel();
        myInput = new JButton("Input File");
        myInput.addActionListener(new InputFileAction());
        panel.add(myInput);

        String[] filterStrings = { "Keyword", "List", "Time", "Sort" };

        myFilters = new JComboBox(filterStrings);
        myFilters.setSelectedIndex(0);
        myFilters.addActionListener(new FilterFileAction());
        panel.add(myFilters);

        myOutput = new JButton("Preview Webpages");
        myOutput.addActionListener(new OutputAction());
        panel.add(myOutput);

        return panel;
    }

    private class InputFileAction implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            load();
        }

    }

    private class FilterFileAction implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if (myFilters.getSelectedItem().equals("Keyword"))
            {

                myTf.setText("Enter a Keyword");
                ActionListener al = new TextListener();
                myTf.addActionListener(al);

            }
            if (myFilters.getSelectedItem().equals("List"))
            {
                myTf.setText("Enter a list of keywords split by spaces");
                ActionListener al = new TextListener1();
                myTf.addActionListener(al);

            }
            if (myFilters.getSelectedItem().equals("Time"))
            {
                myTf.setText("Enter a Start Date and End Date split by spaces and of the form yyyy-MM-dd HH:mm:ss");
                ActionListener al = new TextListener2();
                myTf.addActionListener(al);

            }
            if (myFilters.getSelectedItem().equals("Sort"))
            {
                myTf.setText("Enter a keyword to sort by");
                ActionListener al = new TextListener3();
                myTf.addActionListener(al);

            }
        }
    }

    private class TextListener implements ActionListener
    {

        public void actionPerformed (ActionEvent arg0)
        {

            myText = myTf.getText();
            myModel.addFilterByKeyword(myText);
            myTf.removeActionListener(this);
        }

    }

    private class TextListener1 implements ActionListener
    {

        public void actionPerformed (ActionEvent arg0)
        {

            myText = myTf.getText();
            String[] myKeys = myText.split("\\s+");
            myModel.addFilterByKeywordList(myKeys);
            myTf.removeActionListener(this);
        }

    }

    private class TextListener2 implements ActionListener
    {

        public void actionPerformed (ActionEvent arg0)
        {
            myText = myTf.getText();
            String[] myKeys = myText.split("\\s+");
            myModel.addFilterByTimeFrame(myKeys[0], myKeys[1]);
            myTf.removeActionListener(this);
        }

    }

    private class TextListener3 implements ActionListener
    {

        public void actionPerformed (ActionEvent arg0)
        {
            myText = myTf.getText();
            myModel.addFilterByKeywordSorting(myText);
            myTf.removeActionListener(this);
        }

    }

    private class OutputAction implements ActionListener
    {

        public void actionPerformed (ActionEvent e)
        {
            myModel.addSummaryAndDetailPagesWriter("./html/summary.html");
            myModel.perform();
            try
            {
                
                myPage.setPage("/html/summary.html");
            }
            catch (IOException e1)
            {
                myTf.setText("You tried to view the file and it broke");
            }

        }

    }


    private void load ()
    {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                myModel.loadFile(chooser.getSelectedFile());

            }
            catch (TivooInvalidFeed e)
            {
                JOptionPane.showMessageDialog(this,
                                              "Invalid File",
                                              "Error",
                                              JOptionPane.ERROR_MESSAGE);

            }
        }
    }


    private Component makePageDisplay ()
    {
        // displays the web page
        myPage = new JEditorPane();
        myPage.setPreferredSize(SIZE);
        // allow editor to respond to link-clicks/mouse-overs
        myPage.setEditable(false);
        myPage.addHyperlinkListener(new LinkFollower());
        return new JScrollPane(myPage);
    }

    /**
     * Inner class to deal with link-clicks and mouse-overs
     */
    private class LinkFollower implements HyperlinkListener
    {
        public void hyperlinkUpdate (HyperlinkEvent evt)
        {
            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
            {
                // user clicked a link, load it and show it
                try
                {
                    myPage.setPage((evt.getURL().toString()));
                }
                catch (Exception e)
                {
                    String s = evt.getURL().toString();
                    JOptionPane.showMessageDialog(TivooViewer.this,
                                                  "loading problem for " + s +
                                                          " " + e,
                                                  "Load Problem",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
}
