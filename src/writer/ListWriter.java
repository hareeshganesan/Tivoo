package writer;

import java.util.List;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import event.Event;


public class ListWriter extends Writer
{

    public ListWriter (String filename)
    {
        setMyDirectory(filename);
        setMyTitle("Sorted List");
    }


    @Override
    public void outputHTML (List<Event> events)
    {

        sortEvents(events);
        Html html = initializeHTMLDocument();

        Table table = new Table();
        for (Event event : events)
        {
            Tr event_format = new Tr();

            event_format.appendChild((new Td()).appendChild(new Text(event.get("title"))));
            event_format.appendChild((new Td()).appendChild(new Text(event.get("startTime")
                                                                          .toString())));
            event_format.appendChild((new Td()).appendChild(new Text(event.get("endTime")
                                                                          .toString())));

            table.appendChild(event_format);
        }
        html.appendChild(table);
        write(html, getMyDirectory());
    }


    private void sortEvents (List<Event> events)
    {
        Event temp;
        for (int i = 0; i < events.size(); i++)
        {
            for (int j = 1; j < (events.size() - i); j++)
            {
                if (events.get(j - 1)
                          .get("startTime")
                          .compareTo(events.get(j).get("startTime")) > 0)
                {
                    temp = events.get(j - 1);
                    events.set(j - 1, events.get(j));
                    events.set(j, temp);
                }
            }
        }
    }

}
