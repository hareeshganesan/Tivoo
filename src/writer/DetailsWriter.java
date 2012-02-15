package writer;

/**
 * Implement two kinds of output files: a summary page that shows all the events
 * for a week and detail pages that show additional information for specific
 * events. The summary page should list all the events happening for a week, in
 * particular sections for each day of the week. You can either do a horizontal
 * table like a calendar or vertically with headings for each day. The summary
 * page need only list the event's title, start, and end time. Each event title
 * listed should be a link to to the event's details page.
 */

import event.Event;
import java.util.List;
import com.hp.gagawa.java.elements.*;


public class DetailsWriter extends Writer
{

  //  protected String myTitle;
    
    public DetailsWriter(){
        myTitle = "Details";
    }
    
    public void outputHTML (List<Event> events, String filename)
    {

        Html html = initializeHTMLDocument();

        Table table = new Table();
        /**
         * For each element in the events list, add a td to the table
         */
        for (Event event : events)
        {
            Tr event_format = new Tr();
            event_format.appendChild((new Td()).appendChild(new Text(event.getMyTitle())));
            event_format.appendChild((new Td()).appendChild(new Text(event.getMySummary())));

            table.appendChild((event_format));
        }

        html.appendChild(table);

        write(html, filename);

    }
}
