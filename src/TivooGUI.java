import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


/*
 * This class demos both the file chooser and Java's ability to display HTML Use
 * the file chooser to open an an HTML object. The rendered version of the HTML
 * should appear in the window.
 */

@SuppressWarnings("serial")
public class TivooGUI extends JFrame
{

    JEditorPane pane;
    File file;
    TivooSystem model;
    JCheckBox[] filters;


    public TivooGUI (String url) throws IOException
    {
        model = new TivooSystem();

        JPanel panel = new JPanel(new GridLayout());
        pane = new JEditorPane();
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener()
        {

            public void actionPerformed (ActionEvent e)
            {
                JFileChooser fc = new JFileChooser();

                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    file = fc.getSelectedFile();
                    model.loadFile(file);
                }
            }
        });
        buttonPanel.add(loadButton);

        String[] filterStrings =
            { "Keyword", "KeywordList", "KeywordSort", "Time Frame" };

        filters = new JCheckBox[4];
        for (int i = 0; i < filterStrings.length; i++)
        {
            filters[i] = new JCheckBox(filterStrings[i]);
            buttonPanel.add(filters[i]);
        }
        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener()
        {

            public void actionPerformed (ActionEvent e)
            {
                if (filters[0].isSelected())
                {
                    String keyword = JOptionPane.showInputDialog("Input keyword");
                    model.addFilterByKeyword(keyword);
                }
                
                if (filters[1].isSelected())
                {
                    int numWords = Integer.parseInt(JOptionPane.showInputDialog("Input number of keywords in list"));
                    String[] keywordList = new String[numWords];
                    for(int i=0; i<numWords; i++)
                        keywordList[i] = JOptionPane.showInputDialog("Input the correct keyword for keyword #"+numWords);
                    model.addFilterByKeywordList(keywordList);
                }
                
                if (filters[2].isSelected())
                {
                    String keyword = JOptionPane.showInputDialog("Input keyword for sort");
                    model.addFilterByKeywordSorting(keyword);
                }
                
                if (filters[3].isSelected())
                {
                    String startTime = JOptionPane.showInputDialog("Input start time in format \"yyyy-MM-dd HH:mm:ss\"");
                    String endTime = JOptionPane.showInputDialog("Input end time in format \"yyyy-MM-dd HH:mm:ss\"");
                    model.addFilterByTimeFrame(startTime, endTime);
                }
                
                model.addListWriter("html/listview.html");
                model.perform();

                pane.addHyperlinkListener(new LinkFollower());
                try
                {
                    pane.setPage(new File("html/listview.html").toURL());
                }
                catch (MalformedURLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                catch (IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        buttonPanel.add(submitButton);
        panel.add(pane);

        

        panel.add(buttonPanel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        pane.setEditable(false);
        pane.setPreferredSize(new Dimension(400, 200));
        pack();
        setVisible(true);
    }

    private class LinkFollower implements HyperlinkListener
    {
        public void hyperlinkUpdate (HyperlinkEvent evt)
        {
            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
            {
                // user clicked a link, load it and show it
                try
                {
                    pane.setPage((evt.getURL().toString()));
                }
                catch (Exception e)
                {
                    String s = evt.getURL().toString();
                    JOptionPane.showMessageDialog(TivooGUI.this,
                                                  "loading problem for " + s +
                                                          " " + e,
                                                  "Load Problem",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    public static void main (String[] args) throws IOException
    {
        TivooGUI h = new TivooGUI("hello");

    }

}
