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
        /**
         * For each element in the events list, add a td to the table
         */
        for (Event event : events)
        {
            Html html = initializeHTMLDocument();
            
            html.appendChild(new H2().appendChild(new Text(event.getMyTitle())));
            
            Table details = new Table();
            
            
            details.appendChild(new Tr().appendChild(new Td().appendChild(new Text("<b>Start Time:</b> "+event.getMyStart().toString()))));
            details.appendChild(new Tr().appendChild(new Td().appendChild(new Text("<b>End Time:</b> "+event.getMyEnd().toString()))));
            details.appendChild(new Tr().appendChild(new Td().appendChild(new Text("<b>Summary:</b> "+event.getMySummary()))));
            
            html.appendChild(details);
            write(html, filename + events.indexOf(event)+".html");

        }



    }
}