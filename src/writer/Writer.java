package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Tr;
import event.Event;
import exception.TivooInvalidOutputDirectory;


public abstract class Writer
{

    private String myTitle, myDirectory;


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
            throw new TivooInvalidOutputDirectory();
        }
    }


    protected Html initializeHTMLDocument ()
    {
        Html html = new Html();
        Head head = new Head();

        html.appendChild(head);

        Title title = new Title();
        title.appendChild(new Text(myTitle + " Page"));
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


    protected void generateDetail (Table details,
                                   String eventType,
                                   String detail)
    {
        String fullDetail = eventType + ": " + detail;
        details.appendChild(new Tr().appendChild(new Td().appendChild(new Text(fullDetail))));
    }


    protected void addEventToTable (List<Event> events, Table table, Event event)
    {
        Tr event_format = new Tr();
        A link = new A();

        String linkText = "event" + events.indexOf(event) + ".html";
        link.setHref(linkText);
        link.appendChild(new Text(event.get("title")));

        event_format.appendChild((new Td()).appendChild(link));
        event_format.appendChild((new Td()).appendChild(new Text(event.get("startTime")
                                                                      .toString())));
        event_format.appendChild((new Td()).appendChild(new Text(event.get("endTime")
                                                                      .toString())));

        table.appendChild(event_format);
    }

}
