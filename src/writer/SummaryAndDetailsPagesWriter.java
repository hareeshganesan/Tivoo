package writer;

import java.util.*;
import com.hp.gagawa.java.elements.*;
import event.Event;
import event.Event.Type;

public class SummaryAndDetailsPagesWriter extends Writer {

    public SummaryAndDetailsPagesWriter(String directory) {
        myTitle = "Summary and Details";
        myDirectory = directory;
    }

    public void outputHTML (List<Event> events) {
        outputSummaryPage(events, myDirectory);
        String s[] = myDirectory.split("/");
        String ss = myDirectory.substring(0, myDirectory.indexOf(s[s.length-1])); 
        outputDetailsPages(events, ss);
    }

    public void outputDetailsPages (List<Event> events, String directory) {
        for (Event event : events) {
            Html html = initializeHTMLDocument();

            html.appendChild(new H2().appendChild(new Text((String)event.get(Type.TITLE))));

            Table details = new Table();

            details.appendChild(new Tr().appendChild(new Td().appendChild(new Text("<b>Start Time:</b> "+event.get(Type.START).toString()))));
            details.appendChild(new Tr().appendChild(new Td().appendChild(new Text("<b>End Time:</b> "+event.get(Type.END).toString()))));
            details.appendChild(new Tr().appendChild(new Td().appendChild(new Text("<b>Summary:</b> "+event.get(Type.SUMMARY)))));

            html.appendChild(details);
            write(html, directory + "event" + events.indexOf(event)+".html");
        }
    }

    public void outputSummaryPage (List<Event> events, String filename) {
        Html html = initializeHTMLDocument();
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        HashMap<Integer, ArrayList<Event>> eventsByDate = groupByDate(events);
        for(int i=0; i<7; i++) {
            ArrayList<Event> day = eventsByDate.get(i);
            Table table = new Table();
            for(Event event : day){
                Tr event_format = new Tr();
                A link = new A();
                link.setHref("event"+events.indexOf(event)+".html");
                link.appendChild(new Text((String)event.get(Type.TITLE)));

                event_format.appendChild((new Td()).appendChild(link));
                event_format.appendChild((new Td()).appendChild(new Text(event.get(Type.START).toString())));
                event_format.appendChild((new Td()).appendChild(new Text(event.get(Type.END).toString())));  

                table.appendChild(event_format);
            }
            Text dayOfTheWeek = new Text(days[i]);
            html.appendChild(new H2().appendChild(dayOfTheWeek));
            html.appendChild(table);
        }
        write(html, filename);
    }

    @SuppressWarnings("deprecation")
    private HashMap<Integer, ArrayList<Event>> groupByDate(List<Event> events){
        HashMap<Integer, ArrayList<Event>> dateSet = new HashMap<Integer, ArrayList<Event>>();
        for(int i=0; i<7; i++)
            dateSet.put(i, new ArrayList<Event>());

        for(Event event : events){
            dateSet.get(((Date)event.get(Type.START)).getDay()).add(event);
        }
        return dateSet;
    }

}
