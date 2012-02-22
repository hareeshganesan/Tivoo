package writer;

import java.io.*;
import java.util.*;

import com.hp.gagawa.java.elements.*;

import event.Event;
import exception.*;

public abstract class Writer
{

    /**
     * TODO: make it private with a get or set
     */
    private String myTitle, myDirectory;

    protected void write (Html html, String filename)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(html.write());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new TivooInvalidOutputDirectory();
        }
    }

    protected Html initializeHTMLDocument ()
    {
        Html html = new Html();
        Head head = new Head();

        html.appendChild(head);

        Title title = new Title();
        title.appendChild(new Text(myTitle+" Page"));
        head.appendChild(title);

        Body body = new Body();

        html.appendChild(body);

        H1 h1 = new H1();
        h1.appendChild(new Text(myTitle));
        body.appendChild(h1);

        return html;
    }
    
    abstract public void outputHTML (List<Event> events);

    protected String getMyTitle ()
    {
        return myTitle;
    }

    protected void setMyTitle (String myTitle)
    {
        this.myTitle = myTitle;
    }

    protected String getMyDirectory ()
    {
        return myDirectory;
    }

    protected void setMyDirectory (String myDirectory)
    {
        this.myDirectory = myDirectory;
    }

}
