import javax.swing.JFrame;


public class MainGUI
{

    public static final String TITLE = "Tivoo";


    public static void main (String[] args)
    {
        // create program specific components
        TivooSystem model = new TivooSystem();
        TivooViewer display = new TivooViewer(model);
        // create container that will work with Window manager
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components to Frame and show it
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);
    }
}
