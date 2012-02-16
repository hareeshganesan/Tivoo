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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.hp.gagawa.java.elements.*;

public class SummaryWriter extends Writer
{


  //  protected String myTitle;
    
    public SummaryWriter(){
        myTitle = "Summary";
    }
    
	public void outputHTML (List<Event> events, String filename)
    {

        Html html = initializeHTMLDocument();

        HashMap<Integer, ArrayList<Event>> eventsByDate = groupByDate(events);
        /**
         * For each element in the events list, add a td to the table
         */
        
        for(int i=0; i<7; i++){
            ArrayList<Event> day = eventsByDate.get(i);
            Table table = new Table();
            for(Event event : day){
                Tr event_format = new Tr();
                event_format.appendChild((new Td()).appendChild(new Text(event.getMyTitle())));
                event_format.appendChild((new Td()).appendChild(new Text(event.getMyStart().toString())));
                event_format.appendChild((new Td()).appendChild(new Text(event.getMyEnd().toString())));  
                
                table.appendChild(event_format);
            }
            html.appendChild(new H1().appendChild(new Text(new Integer(i).toString())));
            html.appendChild(table);
        }
                 
                
        
        
        write(html, filename);

    }
    
    private HashMap<Integer, ArrayList<Event>> groupByDate(List<Event> events){
        HashMap<Integer, ArrayList<Event>> dateSet = new HashMap<Integer, ArrayList<Event>>();
        for(int i=0; i<7; i++)
            dateSet.put(i, new ArrayList<Event>());
        
        for(Event event : events){
            dateSet.get(event.getMyStart().getDay()).add(event);
        }
        
        return dateSet;
            
    }
}
