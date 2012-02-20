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

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        HashMap<Integer, ArrayList<Event>> eventsByDate = groupByDate(events);
        
        for(int i=0; i<7; i++){
            ArrayList<Event> day = eventsByDate.get(i);
            Table table = new Table();
            for(Event event : day){
                Tr event_format = new Tr();
                A link = new A();
                link.setHref("event"+events.indexOf(event)+".html");
                link.appendChild(new Text(event.getMyTitle()));
                
                event_format.appendChild((new Td()).appendChild(link));
                event_format.appendChild((new Td()).appendChild(new Text(event.getMyStart().toString())));
                event_format.appendChild((new Td()).appendChild(new Text(event.getMyEnd().toString())));  
                
                table.appendChild(event_format);
            }
            Text dayOfTheWeek = new Text(days[i]);
            html.appendChild(new H2().appendChild(dayOfTheWeek));
            html.appendChild(table);
        }
                 
                
        int x = Calendar.SUNDAY;
        
        write(html, filename);

    }
    
    @SuppressWarnings("deprecation")
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