package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;

import event.Event;

public abstract class Writer
{

    protected String myTitle;
    
    protected void write (Html html, String filename)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(html.write());
            bw.flush();
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
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
    abstract public void outputHTML (List<Event> events, String filename);
    
}
