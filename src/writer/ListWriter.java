package writer;

import java.util.Collections;
import java.util.List;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import event.Event;

public class ListWriter extends Writer
{

    public ListWriter(String filename){
        setMyDirectory(filename);
        setMyTitle("Sorted List");
    }
    @SuppressWarnings("unchecked")
    @Override
    public void outputHTML (List<Event> events)
    {
        
        Collections.sort(events);
        Html html = initializeHTMLDocument();
        
        Table table = new Table();
        for(Event event : events){
            Tr event_format = new Tr();
        
            event_format.appendChild((new Td()).appendChild(new Text(event.get("title"))));
            event_format.appendChild((new Td()).appendChild(new Text(event.get("startTime")
                                                                          .toString())));
            event_format.appendChild((new Td()).appendChild(new Text(event.get("endTime")
                                                                          .toString())));
        
            table.appendChild(event_format);
        }
        write(html, getMyDirectory());
    }

}
