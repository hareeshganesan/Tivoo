/**
 * Implement two kinds of output files: a summary page that shows all the events
 * for a week and detail pages that show additional information for specific
 * events. The summary page should list all the events happening for a week, in
 * particular sections for each day of the week. You can either do a horizontal
 * table like a calendar or vertically with headings for each day. The summary
 * page need only list the event's title, start, and end time. Each event title
 * listed should be a link to to the event's details page.
 */
package writer;

import event.Event;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import event.Event;
import com.hp.gagawa.java.elements.*;

public class SummaryWriter extends Writer
{

    public void outputHTML (List<Event> events, String filename)
    {

        Html html = initializeHTMLDocument();

        Table table = new Table();
        /**
         * For each element in the events list, add a td to the table
         */
        for(Event event : events){
            Tr event_format = new Tr();
            event_format.appendChild((new Td()).appendChild(new Text(event.getMyTitle())));
            event_format.appendChild((new Td()).appendChild(new Text(event.getMySummary())));
            
            table.appendChild((event_format));
        }
        
        html.appendChild(table);
        
        write(html, filename);

    }

    private Html initializeHTMLDocument ()
    {
        Html html = new Html();
        Head head = new Head();

        html.appendChild(head);

        Title title = new Title();
        title.appendChild(new Text("Summary Page"));
        head.appendChild(title);

        Body body = new Body();

        html.appendChild(body);

        H1 h1 = new H1();
        h1.appendChild(new Text("Summary"));
        body.appendChild(h1);
        
        return html;
    }
    
    private void write(Html html, String filename){
        try
        {
            BufferedWriter bw =
                new BufferedWriter(new FileWriter("output.html", filename));
            bw.write(html.write());
            bw.flush();
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }
}
